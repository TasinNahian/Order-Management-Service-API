package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;

public class ServiceOrderJson implements Serializable {
    private String id;
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
