package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class Constraint extends BaseBundled{

    private String href;
    private String id;
    private String name;
    private String version;
    private String referredType;
    private String featureId;
}
