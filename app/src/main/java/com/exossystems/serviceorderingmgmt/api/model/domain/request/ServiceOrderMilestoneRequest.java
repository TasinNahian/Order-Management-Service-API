package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ServiceOrderMilestoneRequest extends BaseBundled {
    private String description;
    private String id;
    private String message;
    private Timestamp milestoneDate;
    private String name;
    private String status;
    private List<ServiceOrderItemRefRequest> serviceOrderItem;
}
