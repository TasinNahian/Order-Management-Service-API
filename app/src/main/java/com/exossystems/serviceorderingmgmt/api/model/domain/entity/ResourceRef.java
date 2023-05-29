package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResourceRef extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String name;
    @JsonProperty("@referredType")
    private String referredType;
    private String serviceRefOrValueId;
}
