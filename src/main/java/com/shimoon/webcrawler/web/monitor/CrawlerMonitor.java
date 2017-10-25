package com.shimoon.webcrawler.web.monitor;

import com.shimoon.webcrawler.crawler.controller.CrawlerControllerFactory;
import com.shimoon.webcrawler.persitent.repository.ForumPostRepository;
import com.shimoon.webcrawler.web.controller.ContextBean;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class CrawlerMonitor implements ApplicationListener<CrawlerEvent> {

    @Autowired
    private ContextBean contextBean;
    @Override
    @Async
    public void onApplicationEvent(CrawlerEvent crawlerEvent) {
        ForumConfVO forumConfig = crawlerEvent.getForumConfVO();
        ForumPostRepository forumPostRepository = crawlerEvent.getForumPostRepository();
        String source = forumConfig.getSource();
        String crawlStorageFolder = "/data/crawl/root/"+source;


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
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.addSeed(forumConfig.getOrigin_link());

        contextBean.addToMapCrawlerRunning(source,controller);
        controller.start(factory,forumConfig.getNum_thread());
    }
}
