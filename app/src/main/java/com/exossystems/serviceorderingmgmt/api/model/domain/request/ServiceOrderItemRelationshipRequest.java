package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.util.List;

@Data
public class ServiceOrderItemRelationshipRequest extends BaseBundled {
//    private String id;
    private String relationshipType;
   // private String serviceOrderItemId;
    List<ServiceOrderItemRefRequest> orderItem;
}
