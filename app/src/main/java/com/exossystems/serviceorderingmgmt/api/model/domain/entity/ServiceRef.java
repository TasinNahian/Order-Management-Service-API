package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceRef extends BaseBundled{
    private String id;
    private String href;
    private String category;
    private String description;
    private String endDate;
    private boolean hasStarted;
    private boolean isBundle;
    private boolean isServiceEnabled;
    private boolean isStateful;
    private String name;
    private String serviceDate;
    private String serviceType;
    private String startDate;
    private String startMode;
    private String state;
    private String referredType;
    private String serviceOrderItemId;
}
