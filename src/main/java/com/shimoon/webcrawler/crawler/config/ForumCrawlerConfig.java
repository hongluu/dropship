package com.shimoon.webcrawler.crawler.config;

import java.util.List;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class ForumCrawlerConfig {
	@Getter(AccessLevel.PUBLIC) @Setter private Pattern fillterShouldVisit;

	@Getter(AccessLevel.PUBLIC) @Setter private String outputCsvPath;

	@Getter(AccessLevel.PUBLIC) @Setter private FilterPage fillterPage;

	@Getter(AccessLevel.PUBLIC) @Setter private ForumSelector forumSelector;

	@Getter(AccessLevel.PUBLIC) @Setter private int numThread;

	@Getter(AccessLevel.PUBLIC) @Setter private List<String> filterUrlShouldVisitPrefixList;

	@Getter(AccessLevel.PUBLIC) @Setter private List<String> filterEndUrls;

	@Getter(AccessLevel.PUBLIC) @Setter private List<String> originUrls;

	@Getter(AccessLevel.PUBLIC) @Setter private String source;

}
