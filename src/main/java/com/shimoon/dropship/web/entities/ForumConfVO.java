package com.shimoon.dropship.web.entities;

import com.shimoon.dropship.persitent.entities.ForumConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class ForumConfVO {
    public static final int NOT_RUNNING = 0;
    public static final int RUNNING = 1;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String forum_name;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private int num_thread;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String prefix_url_has_data;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String[] prefixUrlsHasData;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String origin_link;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String should_visit_prefix_links;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String[] shouldVisitPrefixLinks;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String ignore_links;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String[] ignoreLinks;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String ignore_keywords;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String[] ignoreKeywords;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private int total_post;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private int completed_post;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String title_selector;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private int status;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String strStatus;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String post_selector;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String postedat_selector;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String comment_selector;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String username_selector;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String usertype_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String source;

    public ForumConfVO() {
    }

    public ForumConfVO(ForumConfig forumConfig) {
        if (forumConfig != null) {
            try {
                BeanUtils.copyProperties(this, forumConfig);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            this.setShouldVisitPrefixLinks(this.should_visit_prefix_links.split(","));
            this.setIgnoreKeywords(this.ignore_keywords.split(","));
            this.setIgnoreLinks(this.ignore_links.split(","));
            this.setPrefixUrlsHasData(this.ignore_links.split(","));

        }
    }

    public ForumConfig toForumConfig()  {
        ForumConfig output = new ForumConfig();
        try {
            BeanUtils.copyProperties(output, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return output;
    }
}
