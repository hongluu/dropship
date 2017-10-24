package com.shimoon.webcrawler.business.entities;


import lombok.Getter;
import lombok.Setter;

public class ServiceResult {

    @Getter
    @Setter
    private Object data;
    @Getter
    @Setter
    private boolean isSuccess;
    @Getter
    @Setter
    private String[] messages;

    public ServiceResult(Object data, boolean isSuccess, String[] messages) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.messages = messages;
    }

    public ServiceResult(boolean isSuccess, String[] messages) {
        this.isSuccess = isSuccess;
        this.messages = messages;
    }

    public ServiceResult() {
        this.isSuccess=true;
    }

}
