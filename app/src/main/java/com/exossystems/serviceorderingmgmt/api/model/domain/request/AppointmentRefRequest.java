package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppointmentRefRequest extends BaseBundled {
    private String description;
    private String href;
    private String id;
    @JsonProperty("@referredType")
    private String referredType;
    private String serviceOrderItemId;
}
