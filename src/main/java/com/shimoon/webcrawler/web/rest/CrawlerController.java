package com.shimoon.webcrawler.web.rest;

import com.shimoon.webcrawler.business.impl.services.CrawlerService;
import com.shimoon.webcrawler.crawler.config.ForumCrawlerConfig;
import com.shimoon.webcrawler.crawler.config.ForumSelector;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HongLM on 10/19/17.
 */
@RestController
public class CrawlerController {

  @Autowired
  private CrawlerService crawlerService;

//  @GetMapping("crawl/test")
//  public String testCrawl() {
//    ForumCrawlerConfig fcConfig = new ForumCrawlerConfig();
//    ForumSelector forumSelector = new ForumSelector();
//    forumSelector.setCommentEl(".messageContent article");
//    forumSelector.setPostedAtEl(".privateControls span.DateTime");
//    forumSelector.setPostEl("#messageList li");
//    forumSelector.setTitleEl(".titleBar>h1>a");
//    forumSelector.setUserNameEl(".userText span");
//    forumSelector.setUserTypeEl(".userTitle");
//
//    fcConfig.setForumSelector(forumSelector);
//    fcConfig.setSource("kenh_sinh_vien");
//    fcConfig.setNumThread(5);
//    fcConfig.setOriginUrls(new ArrayList<>(Arrays.asList("http://kenhsinhvien.vn/forum")));
//    fcConfig.setFilterUrlShouldVisitPrefixList(new ArrayList<>(
//        Arrays.asList("http://kenhsinhvien.vn/forum", "http://kenhsinhvien.vn/topic/")));
//    fcConfig.setFilterEndUrls(new ArrayList<>(
//        Arrays.asList("http://kenhsinhvien.vn/topic/")));
//
//    try {
//      crawlerService.crawl(fcConfig);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return "";
//  }


}
