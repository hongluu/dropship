package com.shimoon.dropship.business.entities;

import com.shimoon.dropship.persitent.entities.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class CategoryVO {
    @Getter(AccessLevel.PUBLIC) @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String code;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String parent_code;

    @Getter(AccessLevel.PUBLIC) @Setter private String name;

    @Getter(AccessLevel.PUBLIC) @Setter private int marketplace_id;

    public CategoryVO(Category category){
        try {
            BeanUtils.copyProperties(this, category);
            this.marketplace_id = category.getMarketPlace().getId();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
