package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class Feature {
    private String id;
    private boolean isBundle;
    private boolean isEnabled;
    private String name;
    private String serviceRefOrValueId;

}
