package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceOrderItem extends BaseBundled{
    private String action;
    private String id;
    private int quantity;
    private String state;
    private String serviceOrderId;
    private String otherServiceOrderItemList;
}
