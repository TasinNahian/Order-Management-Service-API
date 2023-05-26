package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServiceOrderRelationship extends BaseBundled{
    private String id;
    private String href;
    private String relationshipType;
    private String serviceOrderId;
    private String characteristicId;
    @JsonProperty("@referredType")
    private String referredType;
}
