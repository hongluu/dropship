package com.shimoon.dropship.web.controller;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContextBean {
    private Map<String,CrawlController> mapForumCrawlerRunning;

    @PostConstruct
    public void init(){
        mapForumCrawlerRunning = new HashMap<>();
    }


    public CrawlController getCrawlerRunning(String source) {
        return mapForumCrawlerRunning.get(source);
    }

    public void addToMapCrawlerRunning(String source, CrawlController controller) {
        mapForumCrawlerRunning.put(source,controller);
    }
    public void removeToMapCrawlerRunning(String source){
        mapForumCrawlerRunning.remove(source);
    }
}
