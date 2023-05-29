package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ServiceOrderJeopardyAlertRequest extends BaseBundled implements Serializable {

    private Timestamp alertDate;
    private String exception;
    private String id;
    private String jeopardyType;
    private String message;
    private String name;
    private List<ServiceOrderItemRefRequest> serviceOrderItem;

}
