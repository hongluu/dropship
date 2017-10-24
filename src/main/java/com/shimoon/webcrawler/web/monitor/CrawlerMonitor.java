package com.shimoon.webcrawler.web.monitor;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class CrawlerMonitor implements ApplicationListener<CrawlerEvent> {
    @Override
    @Async
    public void onApplicationEvent(CrawlerEvent crawlerEvent) {
        crawlerEvent.getCrawlController().start(crawlerEvent.getFactory(),crawlerEvent.getForumConfVO().getNum_thread());
    }
}
