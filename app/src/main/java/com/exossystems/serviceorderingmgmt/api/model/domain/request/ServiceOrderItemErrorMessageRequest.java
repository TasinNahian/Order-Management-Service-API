package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

@Data
public class ServiceOrderItemErrorMessageRequest extends BaseBundled {

    private String id;
    private String code;
    private String message;
    private String reason;
    private String referenceError;
    private String status;
    private String timestamp;
    private String serviceOrderItemId;
}
