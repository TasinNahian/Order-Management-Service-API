package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelatedServiceOrderItem extends BaseBundled implements Serializable {
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
