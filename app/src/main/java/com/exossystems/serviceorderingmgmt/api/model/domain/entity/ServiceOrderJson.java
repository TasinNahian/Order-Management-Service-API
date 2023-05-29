package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceOrderJson implements Serializable {
    private String id;
    private String data;
}
