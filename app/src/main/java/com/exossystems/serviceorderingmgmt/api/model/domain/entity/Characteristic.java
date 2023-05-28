package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class Characteristic extends BaseBundled{
    private String id;
    private String name;
    private String value;
    private String value_type;
    private String featureId;
    private String serviceRefOrValueId;
    private String serviceRelationshipId;
}
