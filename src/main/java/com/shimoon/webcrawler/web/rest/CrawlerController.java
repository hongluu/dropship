package com.shimoon.webcrawler.web.rest;

import com.shimoon.webcrawler.business.entities.ForumService;
import com.shimoon.webcrawler.business.impl.services.CrawlerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HongLM on 10/19/17.
 */
@RestController
public class CrawlerController {


  private final CrawlerService crawlerService;
  private final ForumService forumService;


  @Autowired
  public CrawlerController(CrawlerService crawlerService, ForumService forumService) {
    this.crawlerService = crawlerService;
    this.forumService = forumService;
  }


  @GetMapping("/forum/num-of-completed-post")
  public int getNumOfCompletedPost(@RequestParam("source")String source){
    return forumService.getNumOfAllPost(source);
  }

}
