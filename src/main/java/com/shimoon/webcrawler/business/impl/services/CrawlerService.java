package com.shimoon.webcrawler.business.impl.services;

import com.shimoon.webcrawler.crawler.config.ForumCrawlerConfig;
import com.shimoon.webcrawler.crawler.controller.CrawlerControllerFactory;
import com.shimoon.webcrawler.persitent.repository.ForumPostRepository;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HongLM on 10/19/17.
 */
@Service
public class CrawlerService {
  @Autowired
  private ForumPostRepository forumPostRepository;

  public void crawl(ForumCrawlerConfig fcConfig) throws Exception {
    String crawlStorageFolder = "/data/crawl/root";

    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlerControllerFactory factory = new CrawlerControllerFactory(fcConfig, forumPostRepository);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
    for (String url : fcConfig.getOriginUrls()) {
      controller.addSeed(url);
    }



        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
    controller.start(factory, fcConfig.getNumThread());
  }
}
