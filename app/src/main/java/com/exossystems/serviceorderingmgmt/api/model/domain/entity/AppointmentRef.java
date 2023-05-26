package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppointmentRef extends BaseBundled {
    private String id;
    private String href;
    private String description;
    @JsonProperty("@referredType")
    private String referredType;
    private String serviceOrderItemId;
}
