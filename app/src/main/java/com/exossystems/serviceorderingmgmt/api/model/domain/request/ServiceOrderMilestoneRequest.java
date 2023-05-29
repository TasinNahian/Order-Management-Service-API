package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ServiceOrderMilestoneRequest extends BaseBundled implements Serializable {
    private String description;
    private String id;
    private String message;
    private Timestamp milestoneDate;
    private String name;
    private String status;
    private List<ServiceOrderItemRefRequest> serviceOrderItem;
}
