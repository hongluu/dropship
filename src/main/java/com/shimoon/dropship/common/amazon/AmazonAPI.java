package com.shimoon.dropship.common.amazon;

import com.shimoon.dropship.web.entities.Product;
import com.shimoon.dropship.web.entities.SearchParam;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
public class AmazonAPI {
    private final static Logger LOGGER = Logger.getLogger(AmazonAPI.class);

    public List<Product> searchItem(SearchParam searchParam) {
        List<Product> productList = new ArrayList<>();
        String urlData = AmazonQueryBuilder.build(searchParam);
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(urlData).openStream(), "UTF-8", "", Parser.xmlParser());
            System.out.println(doc);
            Elements elItems = doc.select("Item");
            elItems.forEach(el->{

            });
        } catch (IOException e) {
            LOGGER.debug("No products found");
        }

        return productList;
    }
}


