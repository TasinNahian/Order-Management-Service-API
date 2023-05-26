package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalReferenceRequest extends BaseBundled {
    private String id;
    private String href;
    private String externalReferenceType;
    private String name;
}
