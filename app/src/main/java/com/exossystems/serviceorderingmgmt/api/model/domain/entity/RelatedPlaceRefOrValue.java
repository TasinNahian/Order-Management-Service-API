package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class RelatedPlaceRefOrValue extends BaseBundled {
    private String href;
    private String id;
    private String name;
    private String role;
    private String referredType;
    private String serviceRefOrValueId;
}
