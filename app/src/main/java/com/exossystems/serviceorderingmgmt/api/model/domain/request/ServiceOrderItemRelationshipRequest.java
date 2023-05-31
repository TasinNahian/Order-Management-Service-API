package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

import java.io.Serializable;
import java.util.List;

public class ServiceOrderItemRelationshipRequest extends BaseBundled implements Serializable {
    private String id;
    private String relationshipType;
    List<ServiceOrderItemRefRequest> orderItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public List<ServiceOrderItemRefRequest> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<ServiceOrderItemRefRequest> orderItem) {
        this.orderItem = orderItem;
    }
}
