package com.shimoon.dropship.web.entities;

import com.shimoon.dropship.persitent.entities.ForumPost;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ForumVO {

    @Setter @Getter
    private int totalPosts;

    @Setter @Getter
    private int numOfCompletedPost;

    @Setter @Getter
    private List<ForumPost> forumPosts;

    @Setter @Getter
    private String forumUrl;

    @Setter @Getter
    private String forumName;

    @Setter @Getter
    private String postTitle;

}
