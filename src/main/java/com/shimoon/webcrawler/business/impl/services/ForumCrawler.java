package com.shimoon.webcrawler.business.impl.services;

import com.shimoon.webcrawler.common.StringUtils;
import com.shimoon.webcrawler.crawler.config.ForumCrawlerConfig;
import com.shimoon.webcrawler.crawler.config.ForumSelector;
import com.shimoon.webcrawler.persitent.entities.ForumConfig;
import com.shimoon.webcrawler.persitent.entities.ForumPost;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by HongLM on 10/19/17.
 */
public class ForumCrawler extends WebCrawler {

  private static final Logger LOGGER = Logger.getLogger(ForumCrawler.class);
  private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
      + "|png|mp3|mp4|zip|gz))$");
  private ForumConfVO fcConfig;
  private MongoRepository repository;


  public ForumCrawler(ForumConfVO fcConfig, MongoRepository repository) {
    this.fcConfig =fcConfig;
    this.repository = repository;
  }

  @Override
  public boolean shouldVisit(Page referringPage, WebURL url) {
    String href = url.getURL().toLowerCase();

    return !FILTERS.matcher(href).matches() && urlsMatch(
            Arrays.asList(fcConfig.getShouldVisitPrefixLinks()), href);
  }

  private boolean urlsMatch(List<String> urls, String url) {
    for (String _url : urls) {
      if (url.startsWith(_url)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void visit(Page page){
    String url = page.getWebURL().getURL();
    System.out.println("URL: " + url);
    if (urlsMatch(Arrays.asList(fcConfig.getShouldVisitPrefixLinks()),url)) {
      if (page.getParseData() instanceof HtmlParseData) {

        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        Document doc = Jsoup.parse(htmlParseData.getHtml());
        String title = doc.select(fcConfig.getTitle_selector()).text();

        Elements postEls = doc.select(fcConfig.getPost_selector());
        for(Element el: postEls){
          ForumPost forumPost = new ForumPost();
          forumPost.setUrl(url);
          forumPost.setSource(fcConfig.getSource());

          String commentEl =fcConfig.getComment_selector();
          forumPost.setUserName(el.select(fcConfig.getUsername_selector()).text());
          forumPost.setUserType(el.select(fcConfig.getUsertype_selector()).text());
          forumPost.setTitle(title);
          forumPost.setContent(el.select(commentEl).text());
          forumPost.setStrPostedAt(el.select(fcConfig.getPostedat_selector()).text());
          if (title != null && isValid(title,forumPost.getContent(),fcConfig.getIgnoreKeywords())) {
            this.repository.save(forumPost);
          }
        }
      }
    }

  }

  private boolean isValid(String title, String content, String[] ignoreKeywords) {
    for(int i =0; i < ignoreKeywords.length; i++){
      String ignore = ignoreKeywords[i];
      if(title.contains(ignore)|| content.contains(ignore)){
        return false;
      }
    }
    return  true;
  }

}
