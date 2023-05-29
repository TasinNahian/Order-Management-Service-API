package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ServiceOrderItemRelationshipRequest extends BaseBundled implements Serializable {
//    private String id;
    private String relationshipType;
   // private String serviceOrderItemId;
    List<ServiceOrderItemRefRequest> orderItem;
}
