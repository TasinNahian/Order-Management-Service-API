package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceOrderItem extends BaseBundled implements Serializable {
    private String action;
    private String id;
    private int quantity;
    private String state;
    private String serviceOrderId;
    private String otherServiceOrderItemList;
}
