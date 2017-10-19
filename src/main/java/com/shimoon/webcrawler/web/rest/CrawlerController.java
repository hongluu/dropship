package com.shimoon.webcrawler.web.rest;

import com.shimoon.webcrawler.business.impl.services.CrawlerService;
import com.shimoon.webcrawler.crawler.config.ForumCrawlerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HongLM on 10/19/17.
 */
@RestController
public class CrawlerController {

  @Autowired
  private CrawlerService crawlerService;

  @GetMapping("crawl/test")
  public String testCrawl(){
    ForumCrawlerConfig fcConfig = new ForumCrawlerConfig();
    crawlerService.crawl(fcConfig);
    return "";
  }

}
