package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceOrderItem extends BaseBundled{
    private String id;
    private int quantity;
    private String action;
    private String state;
    private String serviceOrderId;
}
