package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;

public class ServiceOrderItem extends BaseBundled implements Serializable {
    private String action;
    private String id;
    private int quantity;
    private String state;
    private String serviceOrderId;
    private String otherServiceOrderItemList;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getOtherServiceOrderItemList() {
        return otherServiceOrderItemList;
    }

    public void setOtherServiceOrderItemList(String otherServiceOrderItemList) {
        this.otherServiceOrderItemList = otherServiceOrderItemList;
    }
}
