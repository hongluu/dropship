package com.shimoon.dropship.business.entities;

import com.shimoon.dropship.persitent.entities.MarketPlace;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.InvocationTargetException;

public class MarketPlaceVO {
    @Getter(AccessLevel.PUBLIC) @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int status;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String name;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String source;

    public MarketPlaceVO(MarketPlace marketPlace){

        try {
            BeanUtils.copyProperties(this, marketPlace);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
