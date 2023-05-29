package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AppointmentRef extends BaseBundled implements Serializable {
    private String description;
    private String href;
    private String id;
    @JsonProperty("@referredType")
    private String referredType;
    private String serviceOrderItemId;
}
