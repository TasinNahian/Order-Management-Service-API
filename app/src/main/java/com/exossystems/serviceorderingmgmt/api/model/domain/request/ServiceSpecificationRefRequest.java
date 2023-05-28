package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServiceSpecificationRefRequest extends BaseBundled {
    private String href;
    private String id;
    private String name;
    private String version;
    @JsonProperty("@referredType")
    private String referredType;
    private String serviceRefOrId;
}
