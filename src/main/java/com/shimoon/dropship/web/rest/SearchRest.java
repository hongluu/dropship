package com.shimoon.dropship.web.rest;

import com.shimoon.dropship.business.entities.CategoryVO;
import com.shimoon.dropship.business.entities.MarketPlaceVO;
import com.shimoon.dropship.business.service.SearchService;
import com.shimoon.dropship.business.service.CategoryService;
import com.shimoon.dropship.business.entities.ServiceResult;
import com.shimoon.dropship.web.controller.ContextBean;
import com.shimoon.dropship.web.entities.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SessionScope
public class SearchRest {
    private final SearchService searchService;
    private final ContextBean contextBean;

    @Autowired
    public SearchRest(CategoryService categoryService, SearchService searchService, ContextBean contextBean) {
        this.searchService = searchService;
        this.contextBean = contextBean;
    }

    @RequestMapping(value = "/rest/search/advance/get-info", method = RequestMethod.GET)
    public ServiceResult getAdvanceInfo(@RequestParam("store") String store) {
        return searchService.getAdvanceInfo(store);
    }

    @RequestMapping(value = "/rest/search/advance/get-marketplace", method = RequestMethod.GET)
    public List<MarketPlaceVO> getMarketPlace(@RequestParam("store") String store) {
        if (store == null || store.isEmpty()) return new ArrayList<>();
        return contextBean.getMarketPlacesBySource(store).stream().map(x -> new MarketPlaceVO(x)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/rest/search/advance/get-categories", method = RequestMethod.GET)
    public List<CategoryVO> getCate(@RequestParam("store") String store, @RequestParam int market_id, @RequestParam String query) {

        if ((store == null || store.isEmpty()) && market_id == 0) return new ArrayList<>();
        if (market_id == 0)
            return contextBean.getCategoriesBy(store, query).stream().map(x -> new CategoryVO(x)).collect(Collectors.toList());
        return contextBean.getCategoriesBy(store, market_id, query).stream().map(x -> new CategoryVO(x)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/rest/search/simple", method = RequestMethod.GET)
    public List<CategoryVO> search(@RequestParam String store, @RequestParam String keyword,
                                         @RequestParam String min_price, @RequestParam String max_price) {

        searchService.search(new SearchParam(store,keyword,min_price,max_price));
        return new ArrayList<>();
    }

    @RequestMapping(value = "/rest/search", method = RequestMethod.GET)
    public List<CategoryVO> search(@RequestParam String store, @RequestParam String keyword,
                                   @RequestParam String min_price, @RequestParam String max_price,
                                   @RequestParam String market, @RequestParam String category_code) {
        searchService.search(new SearchParam(store,keyword,min_price,max_price,market,category_code));
        return new ArrayList<>();
    }
}
