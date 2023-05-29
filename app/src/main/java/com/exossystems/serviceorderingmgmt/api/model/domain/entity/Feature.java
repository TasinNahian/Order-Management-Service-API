package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Feature implements Serializable {
    private String id;
    private boolean isBundle;
    private boolean isEnabled;
    private String name;
    private String serviceRefOrValueId;

}
