package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConstraintRequest extends BaseBundled {
    private String id;
    private String href;
    private String name;
    private String version;
    @JsonProperty("@referredType")
    private String referredType;
}
