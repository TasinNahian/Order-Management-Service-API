package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item extends BaseBundled {
    private String id;
    private String href;
    private String itemId;
    private String role;
    private String serviceOrderHref;
    private String serviceOrderId;
    private String itemAction;
    @JsonProperty("@referredType")
    private String referredType;
}
