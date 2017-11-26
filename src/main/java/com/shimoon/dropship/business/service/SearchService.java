package com.shimoon.dropship.business.service;

import com.shimoon.dropship.business.entities.ServiceResult;
import com.shimoon.dropship.web.entities.Product;
import com.shimoon.dropship.web.entities.SearchParam;

import java.util.List;

public interface SearchService {
    ServiceResult getAdvanceInfo(String store);

    List<Product> search(SearchParam searchParam);
}
