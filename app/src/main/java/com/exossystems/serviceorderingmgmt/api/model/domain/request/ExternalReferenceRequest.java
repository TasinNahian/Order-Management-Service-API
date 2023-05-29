package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExternalReferenceRequest extends BaseBundled implements Serializable {
    private String external_reference_type;
    private String href;
    private String id;
    private String name;
//    private String serviceOrderId;
}
