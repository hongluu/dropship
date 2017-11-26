package com.shimoon.dropship.web.entities;

import lombok.Getter;
import lombok.Setter;

public class SearchParam {
    @Setter
    @Getter
    private String store;
    @Setter
    @Getter
    private String searchIndex;
    @Setter
    @Getter
    private String keyword;
    @Setter
    @Getter
    private String min_price;
    @Setter
    @Getter
    private String max_price;
    @Setter
    @Getter
    private String market;
    @Setter
    @Getter
    private String category_code;

    public SearchParam(String store, String keyword, String min_price, String max_price) {
        this.store = store;
        this.keyword = keyword;
        this.min_price = min_price;
        this.max_price = max_price;
    }

    public SearchParam(String store, String keyword, String min_price, String max_price, String market, String category_code) {
        this.store = store;
        this.keyword = keyword == ""?null :keyword;
        this.min_price = min_price  == ""?null :min_price;
        this.max_price = max_price == ""?null :max_price;
        this.market = (market != null && !market.isEmpty())? market.toLowerCase():null;
        this.category_code = category_code == "" ? null:category_code;
    }
}
