package com.shimoon.dropship.common.amazon;

import com.shimoon.dropship.common.StringUtils;
import com.shimoon.dropship.web.entities.SearchParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AmazonQueryBuilder {
    /*
    * Your Access Key ID, as taken from the Your Account page.
    */
    private static final String ACCESS_KEY_ID = "AKIAIQBMLQBTNWCDMYMQ";

    /*
     * Your Secret Key corresponding to the above ID, as taken from the
     * Your Account page.
     */
    private static final String SECRET_KEY = "xpTAtI4EA/PODdFTegphS5Zm4xQf4tIbh3QX5hQE";

    /*
     * Use the end-point according to the region you are interested in.
     */
    private static final String ENDPOINT = "webservices.amazon.com";

    @Cacheable("amazon_query")
    public static String build(SearchParam searchParam) {
        /*
         * Set up the signed requests helper.
         */
        SignedRequestsHelper helper;

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, ACCESS_KEY_ID, SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        String requestUrl = null;

        Map<String, String> params = new HashMap<String, String>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", ACCESS_KEY_ID);
        params.put("AssociateTag", "guen-22");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(searchParam.getSearchIndex()))
            params.put("SearchIndex", searchParam.getSearchIndex());
        params.put("ResponseGroup", "Medium");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(searchParam.getKeyword()))
            params.put("Keywords", searchParam.getKeyword());
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(searchParam.getCategory_code()))
            params.put("BrowseNode", searchParam.getCategory_code());
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(searchParam.getMax_price()))
            params.put("MaximumPrice", searchParam.getMax_price());
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(searchParam.getMin_price()))
            params.put("MinimumPrice", searchParam.getMin_price());

        requestUrl = helper.sign(params);

        return requestUrl;
    }
}
