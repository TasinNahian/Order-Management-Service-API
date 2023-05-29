package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Characteristic extends BaseBundled implements Serializable {
    private String id;
    private String name;
    private String value;
    private String value_type;
    private String featureId;
    private String serviceRefOrValueId;
    private String serviceRelationshipId;
}
