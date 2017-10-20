package com.shimoon.webcrawler.business.impl.services;

import com.shimoon.webcrawler.crawler.config.ForumCrawlerConfig;
import com.shimoon.webcrawler.crawler.config.ForumSelector;
import com.shimoon.webcrawler.persitent.entities.ForumPost;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by HongLM on 10/19/17.
 */
public class ForumCrawler extends WebCrawler {

  private static final Logger LOGGER = Logger.getLogger(ForumCrawler.class);
  private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
      + "|png|mp3|mp4|zip|gz))$");
  private ForumCrawlerConfig fcConfig;
  private JpaRepository repository;

  public ForumCrawler(ForumCrawlerConfig fcConfig,
                      JpaRepository repository) {
    this.fcConfig =fcConfig;
    this.repository = repository;
  }

  @Override
  public boolean shouldVisit(Page referringPage, WebURL url) {
    String href = url.getURL().toLowerCase();
    if (fcConfig != null && fcConfig.getFillterShouldVisit() != null) {
      return fcConfig.getFillterShouldVisit().matcher(href).matches() && urlsMatch(
          fcConfig.getFilterUrlShouldVisitPrefixList(), href);
    }

    return !FILTERS.matcher(href).matches() && urlsMatch(
        fcConfig.getFilterUrlShouldVisitPrefixList(), href);
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
  public void visit(Page page) {
    String url = page.getWebURL().getURL();
    System.out.println("URL: " + url);
    if (urlsMatch(fcConfig.getFilterEndUrls(),url)) {
      if (page.getParseData() instanceof HtmlParseData) {
        ForumSelector forumSelector = fcConfig.getForumSelector();
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        Document doc = Jsoup.parse(htmlParseData.getHtml());
        String title = doc.select(forumSelector.getTitleEl()).text();

        Elements postEls = doc.select(forumSelector.getPostEl());
        postEls.forEach(el -> {
          ForumPost forumPost = new ForumPost();
          forumPost.setUrl(url);
          forumPost.setSource(fcConfig.getSource());
          forumPost.setUserName(el.select(forumSelector.getUserNameEl()).text());
          forumPost.setUserType(el.select(forumSelector.getUserTypeEl()).text());
          forumPost.setTitle(title);
          forumPost.setContent(el.select(forumSelector.getCommentEl()).text());
          forumPost.setStrPostedAt(el.select(forumSelector.getPostedAtEl()).text());
          if (forumPost.getStrPostedAt().isEmpty()
              && forumPost.getStrPostedAt() != "") {
            this.repository.save(forumPost);
          }

        });
      }
    }

  }

}
