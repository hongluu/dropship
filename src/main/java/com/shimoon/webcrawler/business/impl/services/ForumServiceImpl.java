package com.shimoon.webcrawler.business.impl.services;

import com.shimoon.webcrawler.business.entities.ForumService;
import com.shimoon.webcrawler.persitent.entities.ForumConfig;
import com.shimoon.webcrawler.persitent.entities.ForumPost;
import com.shimoon.webcrawler.persitent.repository.ForumConfigRepository;
import com.shimoon.webcrawler.persitent.repository.ForumPostRepository;
import com.shimoon.webcrawler.web.controller.ContextBean;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ContextBean contextBean;
    @Autowired
    private ForumConfigRepository forumConfigRepository;

    @Autowired
    private ForumPostRepository forumpostRepository;

    @Override
    public List<ForumConfVO> getAllForumConfigDateDesc() {
        List<ForumConfig> forumConfigList = forumConfigRepository.findAll();
        List<ForumConfVO> forumConfVOList = new ArrayList<>();

        forumConfigList.forEach(x -> {
            ForumConfVO forumConfVO = new ForumConfVO(x);
            forumConfVO.setStatus(getStatus(forumConfVO.getSource()));
            forumConfVOList.add(forumConfVO);
        });
        return forumConfVOList;
    }

    private int getStatus(String source) {
        return contextBean.getCrawlerRunning(source) != null ? ForumConfVO.RUNNING : ForumConfVO.NOT_RUNNING;
    }

    @Override
    public void save(ForumConfig forumConfig) {
        forumConfigRepository.saveAndFlush(forumConfig);
    }

    @Override
    public ForumConfVO getForumBy(int id) {
        return new ForumConfVO(forumConfigRepository.findOne(id));
    }

    @Override
    public ForumConfVO getForumByIdAndUpdateStatus(int id, int running) {
        ForumConfig forumConfig = forumConfigRepository.findOne(id);
        return new ForumConfVO(forumConfig);
    }

    @Override
    public ForumConfig getForumDtoBy(int id) {
        return forumConfigRepository.findOne(id);
    }

    @Override
    public List<ForumPost> getForumpostBy(String source) {
        return forumpostRepository.findTop30BySourceOrderByUpdatedAt(source);
    }

    @Override
    public int getNumOfAllPost() {
        return forumpostRepository.countAllBy();
    }


}
