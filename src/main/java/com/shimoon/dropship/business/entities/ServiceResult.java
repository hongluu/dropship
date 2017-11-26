package com.shimoon.dropship.business.entities;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

public class ServiceResult implements Serializable{

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private boolean success;

    @Setter
    @Getter
    private Object data;

}
