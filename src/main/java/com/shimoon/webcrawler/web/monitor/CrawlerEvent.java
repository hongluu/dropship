package com.shimoon.webcrawler.web.monitor;

import com.shimoon.webcrawler.crawler.controller.CrawlerControllerFactory;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

public class CrawlerEvent extends ApplicationEvent {
    private String eventType;
    @Setter
    @Getter
    private CrawlController crawlController;
    @Setter @Getter
    private ForumConfVO forumConfVO;
    @Setter @Getter
    private CrawlerControllerFactory factory;

    public CrawlerEvent(Object source, CrawlController crawlController, ForumConfVO forumConfVO, CrawlerControllerFactory factory) {
        super(source);
        this.crawlController = crawlController;
        this.forumConfVO = forumConfVO;
        this.factory = factory;
    }
}
