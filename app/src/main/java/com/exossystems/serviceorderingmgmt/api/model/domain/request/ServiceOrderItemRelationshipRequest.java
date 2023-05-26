package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

public class ServiceOrderItemRelationshipRequest extends BaseBundled {
    private String relationshipType;
    private Item orderItem;
}
