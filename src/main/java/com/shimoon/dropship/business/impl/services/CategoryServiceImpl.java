package com.shimoon.dropship.business.impl.services;

import com.shimoon.dropship.business.entities.ServiceResult;
import com.shimoon.dropship.business.service.CategoryService;
import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.entities.MarketPlace;
import com.shimoon.dropship.persitent.repository.CategoryRepository;
import com.shimoon.dropship.persitent.repository.MarketplaceRepo;
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


    private final CategoryRepository categoryRepository;
    private final MarketplaceRepo marketplaceRepo;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, MarketplaceRepo marketplaceRepo) {
        this.categoryRepository = categoryRepository;
        this.marketplaceRepo = marketplaceRepo;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    private static final String AMAZON_GET_CAT_URL = "http://www.findbrowsenodes.com/";

    @Override
    public void getAmazonCatOnlineAndUpdate() {
        try {

            categoryRepository.deleteCategoryBySource(AmazonCate.AMAZON_URL);
            marketplaceRepo.deleteMarketPlaceBySource(AmazonCate.AMAZON_URL);
            List<MarketPlace> allMarketplace = getAllMarketPlaceFromWeb();
            List<AmazonCate> amazonCategories = new ArrayList<>();
            for (MarketPlace marketPlace : allMarketplace) {
                rebuildDatabaseCategoryBy(getAllAmazonCategoriesFromWeb(marketPlace));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Category> getCategoryBy(String source) {
        return categoryRepository.findAllBySource(source);
    }

    private List<MarketPlace> getAllMarketPlaceFromWeb() throws IOException {
        Document document = Jsoup.connect(AMAZON_GET_CAT_URL).get();
        Elements els = document.select("#locales li");
        List<MarketPlace> allMarketplace = new ArrayList<>();
        for (Element el : els) {
            MarketPlace marketPlace = new MarketPlace();
            marketPlace.setName("webservices."+el.select("a").text());
            marketPlace.setUrl_to_find_brower_node(el.select("a").attr("href").toLowerCase());
            marketPlace.setSource(AmazonCate.AMAZON_URL);
            allMarketplace.add(marketplaceRepo.saveAndFlush(marketPlace));
        }
        return allMarketplace;
    }

    private List<AmazonCate> getAllAmazonCategoriesFromWeb(MarketPlace marketPlace) throws IOException {
        Document document = Jsoup.connect(marketPlace.getUrl_to_find_brower_node()).get();
        Elements els = document.select("#section1 div.hn");
        List<AmazonCate> amazonCates = new ArrayList<>();

        for (Element el : els) {
            AmazonCate amazonCate = new AmazonCate();
            amazonCate.setName(el.select(".c_name").text());
            amazonCate.setCode(el.select(".label-group .a2-node").text());
            amazonCate.setUrl(AMAZON_GET_CAT_URL + el.select(".c_name").attr("href"));
            amazonCate.setMarketplace(marketPlace);
            loadSubCat(amazonCate);
            amazonCates.add(amazonCate);

        }
        ;
        return amazonCates;
    }

    private void rebuildDatabaseCategoryBy(List<AmazonCate> amazonCates) {
        for(AmazonCate amazonCate :amazonCates){
            Category category = categoryRepository.saveAndFlush(amazonCate.toCategory());
            List<AmazonCate> listSub = amazonCate.getSub_categories();
            for(AmazonCate subCatAmazon : listSub){
                Category subCatDto = subCatAmazon.toCategory();
                subCatDto.setParent_id(category.getId());
                subCatDto.setParent_code(category.getCode());
                categoryRepository.save(subCatDto);
            }
        }
    }

    private void loadSubCat(AmazonCate amazonCate) {
        try {
            Document document = Jsoup.connect(amazonCate.getUrl()).get();
            Elements els = document.select("#section1 div.subll");
            List<AmazonCate> aCats = new ArrayList<>();
            for (Element el : els) {
                AmazonCate _amazonSubCate = new AmazonCate();
                _amazonSubCate.setName(el.select("a").text());
                _amazonSubCate.setCode(el.select(".label-group .a2-node").text());

                _amazonSubCate.setMarketplace(amazonCate.getMarketplace());
                if(_amazonSubCate.getCode()!=null && !_amazonSubCate.getCode().isEmpty()
                        && _amazonSubCate.getName()!=null && !_amazonSubCate.getName().isEmpty())
                aCats.add(_amazonSubCate);
            }
            amazonCate.setSub_categories(aCats);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
