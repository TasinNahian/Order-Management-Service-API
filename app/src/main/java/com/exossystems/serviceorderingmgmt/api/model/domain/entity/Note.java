package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;
import java.sql.Timestamp;
public class Note extends BaseBundled implements Serializable {
    private String author;
    private Timestamp date;
    private String id;
    private String text;
    private String serviceOrderId;
    private String serviceRefOrValueId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getServiceRefOrValueId() {
        return serviceRefOrValueId;
    }

    public void setServiceRefOrValueId(String serviceRefOrValueId) {
        this.serviceRefOrValueId = serviceRefOrValueId;
    }
}
