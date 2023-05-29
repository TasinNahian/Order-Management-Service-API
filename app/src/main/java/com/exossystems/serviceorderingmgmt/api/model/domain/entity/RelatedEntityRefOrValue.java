package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelatedEntityRefOrValue extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String name;
    private String role;
    private String referredType;
    private String serviceRefOrValueId;
}
