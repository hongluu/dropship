package com.shimoon.dropship.business.entities;

import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.entities.MarketPlace;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AdvanceSearchInfo {
    @Setter @Getter
    private List<CategoryVO> categories;
    @Setter @Getter
    private List<MarketPlaceVO> marketplaces;
    public AdvanceSearchInfo(){};
    public AdvanceSearchInfo(List<CategoryVO> categories, List<MarketPlaceVO> marketplaces) {
        this.categories = categories;
        this.marketplaces = marketplaces;
    }
}
