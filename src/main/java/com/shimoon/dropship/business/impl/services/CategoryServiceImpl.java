package com.shimoon.dropship.business.impl.services;

import com.shimoon.dropship.business.entities.CategoryService;
import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.repository.CategoryRepository;
import com.shimoon.dropship.web.entities.AmazonCate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    private static final String AMAZON_GET_CAT_URL = "http://www.findbrowsenodes.com/";

    @Override
    public void getAmazonCatOnlineAndUpdate() {
        try {
            Document document = Jsoup.connect(AMAZON_GET_CAT_URL).get();
            Elements els = document.select("#section1 div.hn");
            List<AmazonCate> amazonCates = new ArrayList<>();

            for (Element el : els) {
                AmazonCate amazonCate = new AmazonCate();
                amazonCate.setName(el.select(".c_name").text());
                amazonCate.setCode(el.select(".a2-node").text());
                amazonCate.setUrl(AMAZON_GET_CAT_URL + el.select(".c_name").attr("href"));
                loadSubCat(amazonCate);
                amazonCates.add(amazonCate);

            };

            System.out.println(amazonCates);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadSubCat(AmazonCate amazonCate) {
        try {
            Document document = Jsoup.connect(amazonCate.getUrl()).get();
            Elements els = document.select("#section1 div.subll");
            List<AmazonCate> aCats = new ArrayList<>();
            for (Element el : els) {
                AmazonCate _amazonCate = new AmazonCate();
                _amazonCate.setName(el.select("a").text());
                _amazonCate.setCode(el.select("div").text());
                aCats.add(_amazonCate);
            }
            amazonCate.setSub_categories(aCats);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
