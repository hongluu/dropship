package com.shimoon.webcrawler.crawler.controller;

import com.shimoon.webcrawler.business.impl.services.ForumCrawler;
import com.shimoon.webcrawler.persitent.entities.ForumConfig;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by HongLM on 10/19/17.
 */
public class CrawlerControllerFactory implements CrawlController.WebCrawlerFactory {

  private ForumConfVO fcConfig;
  private MongoRepository repository;

  public CrawlerControllerFactory(ForumConfVO fcConfig, MongoRepository repository) {
    this.fcConfig = fcConfig;
    this.repository = repository;
  }

  @Override
  public WebCrawler newInstance() {
    return new ForumCrawler(fcConfig,repository);
  }
}