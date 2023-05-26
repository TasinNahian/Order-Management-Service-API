package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceRestriction extends BaseBundled{
    private String id;
    private String href;
    private String category;
    private String name;
    private String serviceType;
    private String state;
    private String serviceOrderItemId;
}
