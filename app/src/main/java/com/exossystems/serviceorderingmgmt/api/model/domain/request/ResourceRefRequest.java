package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

@Data
public class ResourceRefRequest extends BaseBundled {

    private String href;
    private String id;
    private String name;
    @JsonProperty("@referredType")
    private String referredType;
//    private String serviceRefOrValueId;
}
