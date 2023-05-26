package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class TargetServiceSchema extends BaseBundled{
    private String id;
    private String serviceSpecificationRefId;
}
