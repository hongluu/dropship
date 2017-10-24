package com.shimoon.webcrawler.web.controller;

import com.shimoon.webcrawler.business.entities.ForumService;
import com.shimoon.webcrawler.business.entities.ServiceResult;
import com.shimoon.webcrawler.business.impl.services.CrawlerService;
import com.shimoon.webcrawler.common.StringUtils;
import com.shimoon.webcrawler.persitent.entities.ForumConfig;
import com.shimoon.webcrawler.persitent.entities.ForumPost;
import com.shimoon.webcrawler.web.entities.ForumConfVO;
import com.shimoon.webcrawler.web.entities.ForumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import java.util.List;
import java.util.Locale;

@Controller
public class ForumController extends BaseController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/forum/add")
    public String goAddNewForum(Locale locale, Model model) {
        ForumConfig forumConfig = new ForumConfig();
        model.addAttribute(forumConfig);
        return "/pages/forum/forum_config";
    }

    @GetMapping("/forum/edit")
    public String goAddNewForum(Locale locale, Model model, @RequestParam("id") int id) {
        ForumConfig forumConfig = forumService.getForumDtoBy(id);
        model.addAttribute(forumConfig);
        return "/pages/forum/forum_config";
    }

    @GetMapping("/forum/list")
    public String goListForum(Locale locale, Model model) {
        List<ForumConfVO> forumConfigList = forumService.getAllForumConfigDateDesc();
        model.addAttribute("forumConfigList", forumConfigList);
        return "/pages/forum/forum_config_list";
    }

    @PostMapping("/forum/add")
    public String addNewForum(Locale locale, Model model, @ModelAttribute("forumConfig") ForumConfig forumConfig, BindingResult result) {
        forumConfig.setSource(StringUtils.removeAccent(forumConfig.getForum_name()));
        forumService.save(forumConfig);

        return "redirect:/forum/list";
    }

    @GetMapping("/forum/crawl")
    public String crawlForum(Locale locale, Model model, @RequestParam("id") int id) {
        ForumConfVO forumConfig = forumService.getForumByIdAndUpdateStatus(id, ForumConfVO.RUNNING);
        try {
            ServiceResult serviceResult = crawlerService.crawl(forumConfig);
            if (serviceResult.isSuccess()) {
                model.addAttribute("error", serviceResult.getMessages());
                forumConfig.setStatus(ForumConfVO.RUNNING);
                forumService.save(forumConfig.toForumConfig());
            } else {
                model.addAttribute("success", serviceResult.getMessages());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/forum/list";

    }

    @GetMapping("/forum/stop-crawl")
    public String stopCrawlForum(Locale locale, Model model, @RequestParam("source") String source) {
        crawlerService.stopCrawler(source);
        return "redirect:/forum/list";

    }

    @GetMapping("/forum/pause-crawl")
    public String pauseCrawlForum(Locale locale, Model model, @RequestParam("source") String source) {
        crawlerService.pauseCrawler(source);
        return "redirect:/forum/list";

    }

    @GetMapping("/forum/view-data")
    public String viewData(Locale locale, Model model, @RequestParam("source") String source) {

        ForumVO forumVO = new ForumVO();
        forumVO.setForumName(source);

        forumVO.setNumOfCompletedPost(forumService.getNumOfAllPost());
        forumVO.setForumPosts(forumService.getForumpostBy(source));
        if (forumVO.getForumPosts() != null && forumVO.getForumPosts().size()>0)
            forumVO.setPostTitle(forumVO.getForumPosts().get(0).getTitle());


        model.addAttribute("forum", forumVO);
        return "/pages/forum/forum_post";

    }
}
