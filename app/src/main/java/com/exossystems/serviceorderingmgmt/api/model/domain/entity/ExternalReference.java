package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ExternalReference extends BaseBundled{
    private String external_reference_type;
    private String href;
    private String id;
    private String name;
    private String serviceOrderId;
}
