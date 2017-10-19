package com.shimoon.webcrawler.crawler.config;

import lombok.Getter;
import lombok.Setter;

public class ForumSelector {
	@Getter @Setter private String titleEl;
	@Getter @Setter private String postEl;
	
	@Getter @Setter private String userNameEl;
	@Getter @Setter private String userTypeEl;
	@Getter @Setter private String commentEl;
	@Getter @Setter private String postedAtEl;
	

}
