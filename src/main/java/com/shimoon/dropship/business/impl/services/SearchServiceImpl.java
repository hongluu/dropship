package com.shimoon.dropship.business.impl.services;

import com.shimoon.dropship.business.entities.AdvanceSearchInfo;
import com.shimoon.dropship.business.entities.CategoryVO;
import com.shimoon.dropship.business.entities.MarketPlaceVO;
import com.shimoon.dropship.business.entities.ServiceResult;
import com.shimoon.dropship.business.service.SearchService;
import com.shimoon.dropship.common.Const;
import com.shimoon.dropship.common.amazon.AmazonAPI;
import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.repository.CategoryRepository;
import com.shimoon.dropship.persitent.repository.MarketplaceRepo;
import com.shimoon.dropship.web.controller.ContextBean;
import com.shimoon.dropship.web.entities.Product;
import com.shimoon.dropship.web.entities.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final CategoryRepository categoryRepository;
    private final MarketplaceRepo marketplaceRepo;
    private final ContextBean contextBean;
    private final AmazonAPI amazonAPI;

    @Autowired
    public SearchServiceImpl(CategoryRepository categoryRepository, MarketplaceRepo marketplaceRepo, ContextBean contextBean, AmazonAPI amazonAPI) {
        this.categoryRepository = categoryRepository;
        this.marketplaceRepo = marketplaceRepo;
        this.contextBean = contextBean;
        this.amazonAPI = amazonAPI;
    }

    @Override
    public ServiceResult getAdvanceInfo(String store) {
        ServiceResult serviceResult = new ServiceResult();
        AdvanceSearchInfo advanceSearchInfo = new AdvanceSearchInfo();
        switch (store) {
            case "":
                return serviceResult;
            case Const.Amazon.SOURCE:
            case Const.Ebay.SOURCE: {
                List<Category> categories = contextBean.getCategoriesBySource(store);
                List<CategoryVO> categoryVOS = categories.stream().map(x -> new CategoryVO(x)).collect(Collectors.toList());
                advanceSearchInfo.setCategories(categoryVOS);
                List<MarketPlaceVO> marketPlaceVOS = marketplaceRepo.findAllBySource(store).stream().map(x -> new MarketPlaceVO(x)).collect(Collectors.toList());
                advanceSearchInfo.setMarketplaces(marketPlaceVOS);
                serviceResult.setSuccess(true);
                serviceResult.setData(advanceSearchInfo);
                return serviceResult;
            }
            default: {
                List<Category> categories = contextBean.getCategoriesBySource(store);
                List<CategoryVO> categoryVOS = categories.stream().map(x -> new CategoryVO(x)).collect(Collectors.toList());
                advanceSearchInfo.setCategories(categoryVOS);
                serviceResult.setSuccess(true);
                return serviceResult;
            }
        }
    }

    @Override
    public List<Product> search(SearchParam searchParam) {
        List<Product> productList = new ArrayList<>();
        switch (searchParam.getStore()){
            case Const.Amazon.SOURCE :{
                String categoryCode = searchParam.getCategory_code();
                if(categoryCode != null && !categoryCode.isEmpty()){
                    Category currentCate = categoryRepository.findByCode(categoryCode);
                    if(currentCate !=null && currentCate.getParent_code() != null){
                        searchParam.setSearchIndex(categoryRepository.findByCode(currentCate.getParent_code()).getName());
                    }else{
                        searchParam.setSearchIndex(currentCate.getName());
                    }

                }

                amazonAPI.searchItem(searchParam);
                break;
            }
            case Const.Ebay.SOURCE :{
                break;
            }
            case Const.Alibaba.SOURCE :{
                break;
            }
            case Const.Taobao.SOURCE :{
                break;
            }
            case Const.Rakute.SOURCE :{
                break;
            }
        };
        return productList;
    }
}
