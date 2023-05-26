package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class Place extends BaseBundled{
    private String id;
    private String href;
    private String name;
    private String role;
    private String serviceId;
}
