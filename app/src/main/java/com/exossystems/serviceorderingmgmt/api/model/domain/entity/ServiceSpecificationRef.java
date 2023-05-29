package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceSpecificationRef extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String name;
    private String version;
    private String referredType;
    private String serviceRefOrId;
}
