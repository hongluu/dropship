package com.shimoon.webcrawler.crawler.controller;

import com.shimoon.webcrawler.business.impl.services.ForumCrawler;
import com.shimoon.webcrawler.crawler.config.ForumCrawlerConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by HongLM on 10/19/17.
 */
public class CrawlerControllerFactory implements CrawlController.WebCrawlerFactory {

  private ForumCrawlerConfig fcConfig;
  private JpaRepository repository;

  public CrawlerControllerFactory(ForumCrawlerConfig fcConfig,JpaRepository repository) {
    this.fcConfig = fcConfig;
    this.repository = repository;
  }

  @Override
  public WebCrawler newInstance() {
    return new ForumCrawler(fcConfig,repository);
  }
}