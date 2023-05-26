package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResourceRef extends BaseBundled{
    private String id;
    private String href;
    private String name;
    @JsonProperty("@referredType")
    private String referredType;
    private String serviceId;

}
