package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class Characteristic extends BaseBundled{
    private String id;
    private String name;
    private String value_type;
    private String value;
    private String featureId;
    private String serviceId;
    private String serviceRelationshipId;
}
