package com.shimoon.dropship.web.controller;

import com.shimoon.dropship.business.entities.MarketPlaceVO;
import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.entities.MarketPlace;
import com.shimoon.dropship.persitent.repository.CategoryRepository;
import com.shimoon.dropship.persitent.repository.MarketplaceRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContextBean {
    private final CategoryRepository categoryRepository;
    private final MarketplaceRepo marketplaceRepo;

    @Setter
    @Getter
    private List<Category> categories;

    @Setter
    @Getter
    private List<MarketPlace> marketPlaces;

    @Autowired
    public ContextBean(CategoryRepository categoryRepository, MarketplaceRepo marketplaceRepo) {
        this.categoryRepository = categoryRepository;
        this.marketplaceRepo = marketplaceRepo;
    }

    @PostConstruct
    private void init() {
        categories = categoryRepository.findAll();
        marketPlaces = marketplaceRepo.findAll();

    }

    @Cacheable(value = "categoriesBySource", key = "#source")
    public List<Category> getCategoriesBySource(String source) {
        return categories.stream().filter(x -> x.getSource().equals(source)).collect(Collectors.toList());
    }

    @Cacheable(value = "marketPlacesBySource", key = "#source")
    public List<MarketPlace> getMarketPlacesBySource(String source) {
        return marketPlaces.stream().filter(x -> x.getSource().equals(source)).collect(Collectors.toList());
    }
    @Cacheable(value = "categoriesBy" )
    public List<Category> getCategoriesBy(String source, int market_id) {
        return categories.stream().filter(x -> x.getSource().equals(source) && x.getMarketPlace().getId() == market_id).collect(Collectors.toList());

    }
    @Cacheable(value = "categoriesBy" )
    public List<Category> getCategoriesBy(String source, String query) {
        return categories.stream().filter(x -> x.getSource().equals(source)
                && (x.getName().contains(query) || x.getCode().contains(query)))
                .collect(Collectors.toList());

    }

    @Cacheable(value = "categoriesBy" )
    public List<Category> getCategoriesBy(String source, int market_id, String query) {
        return categories.stream().filter(x -> x.getSource().equals(source)
                && x.getMarketPlace().getId() == market_id
                && (x.getName().contains(query) || x.getCode().contains(query)))
                .collect(Collectors.toList());
    }
}
