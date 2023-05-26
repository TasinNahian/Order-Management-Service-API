package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class ErrorMessageRequest extends BaseBundled {
    private String code;
    private String message;
    private String reason;
    private String referenceError;
    private String status;
    private Timestamp timestamp;
}
