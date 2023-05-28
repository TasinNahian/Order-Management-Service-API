package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class RelatedServiceOrderItem extends BaseBundled{
    private String href;
    private String id;
    private String itemAction;
    private String itemId;
    private String role;
    private String serviceOrderHref;
    private String serviceOrderId;
    private String referredType;
    private String serviceRefOrValueId;
}
