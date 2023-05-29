package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RelatedServiceOrderItemRequest extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String itemAction;
    private String itemId;
    private String role;
    private String serviceOrderHref;
    private String serviceOrderId;
    @JsonProperty("@referredType")
    private String referredType;
//    private String serviceRefOrValueId;
}
