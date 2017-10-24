package com.shimoon.webcrawler.business.impl.services;

import com.shimoon.webcrawler.business.entities.ServiceResult;
import com.shimoon.webcrawler.common.StringUtils;
import com.shimoon.webcrawler.crawler.controller.CrawlerControllerFactory;
import com.shimoon.webcrawler.persitent.repository.ForumPostRepository;
import com.shimoon.webcrawler.web.controller.ContextBean;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import com.shimoon.webcrawler.web.monitor.CrawlerEvent;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Created by HongLM on 10/19/17.
 */
@Service
public class CrawlerService {
    @Autowired
    private ForumPostRepository forumPostRepository;
    @Autowired
    private ContextBean contextBean;
    @Autowired
    private ApplicationEventPublisher publisher;

    public ServiceResult crawl(ForumConfVO forumConfig) throws Exception {
        ServiceResult serviceResult = new ServiceResult();
        String source = forumConfig.getSource();
        if(contextBean.getCrawlerRunning(source) != null){
            String [] mess = {"Forum này đang được chạy Crawl ! Vui lòng kiểm tra lại"};
            serviceResult.setSuccess(false);
            serviceResult.setMessages(mess);
        }

        String crawlStorageFolder = "/data/crawl/root";

        CrawlConfig config = new CrawlConfig();
        config.setResumableCrawling(true);
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlerControllerFactory factory = new CrawlerControllerFactory(forumConfig, forumPostRepository);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */

        controller.addSeed(forumConfig.getOrigin_link());




        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */

        publisher.publishEvent(new CrawlerEvent(this,controller,forumConfig,factory));
        contextBean.addToMapCrawlerRunning(source,controller);
        return serviceResult;

    }

    public void stopCrawler(String source){
        CrawlController crawlController = contextBean.getCrawlerRunning(source);
        crawlController.shutdown();
        crawlController.waitUntilFinish();
        contextBean.removeToMapCrawlerRunning(source);
        forumPostRepository.removeAllBySource(source);
    }
    public void stopCrawlerAndClearData(String source){

    }

    public void pauseCrawler(String source) {
        CrawlController crawlController = contextBean.getCrawlerRunning(source);
        crawlController.shutdown();
        crawlController.waitUntilFinish();
        contextBean.removeToMapCrawlerRunning(source);
    }
}
