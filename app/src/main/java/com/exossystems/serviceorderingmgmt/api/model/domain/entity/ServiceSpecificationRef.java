package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServiceSpecificationRef extends BaseBundled{
    private String id;
    private String href;
    private String name;
    private String version;
    private String serviceId;
    private String featureId;
    @JsonProperty("@referredType")
    private String referredType;
}
