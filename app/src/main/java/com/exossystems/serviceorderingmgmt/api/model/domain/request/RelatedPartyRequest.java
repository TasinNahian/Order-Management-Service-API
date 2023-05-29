package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RelatedPartyRequest extends BaseBundled implements Serializable {
    private String href;
    private String name;
    private String role;
    @JsonProperty("@referredType")
    private String referredType;

}