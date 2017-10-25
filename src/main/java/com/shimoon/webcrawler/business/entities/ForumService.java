package com.shimoon.webcrawler.business.entities;

import com.shimoon.webcrawler.persitent.entities.ForumConfig;
import com.shimoon.webcrawler.persitent.entities.ForumPost;
import com.shimoon.webcrawler.web.entities.ForumConfVO;

import java.util.List;

public interface ForumService {
    List<ForumConfVO> getAllForumConfigDateDesc();

    void save(ForumConfig forumConfig);

    ForumConfVO getForumBy(int id);

    ForumConfVO getForumByIdAndUpdateStatus(int id, int running);

    ForumConfig getForumDtoBy(int id);

    List<ForumPost> getForumpostBy(String source);

    int getNumOfAllPost(String source);
}
