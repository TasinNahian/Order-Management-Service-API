package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServiceOrderItemRefRequest extends BaseBundled {
    private String id;
    private String itemId;
    private String serviceOrderHref;
    private String serviceOrderId;
    @JsonProperty("@referredType")
    private String referredType;
//    private String serviceOrderItemRelationshipId;
//    private String serviceOrderErrorMessageId;
//    private String serviceOrderMilestoneId;
//    private String serviceOrderJeopardyAlertId;
}
