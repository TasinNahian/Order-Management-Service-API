package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.RelatedParty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ServiceOrderRequest extends BaseBundled {
    private Timestamp cancellationDate;
    private String cancellationReason;
    private String category;
    private Timestamp completionDate;
    private String description;
    private Timestamp expectedCompletionDate;
    private String externalId;
    private String href;
    @JsonIgnore
    private String id;
    private String notificationContact;
    private Timestamp orderDate;
    private String priority;
    private Timestamp requestedCompletionDate;
    private Timestamp requestedStartDate;
    private String startDate;
    private String state;
    private List<ExternalReferenceRequest> externalReference; //done
    private List<ServiceOrderRelationshipRequest> orderRelationship; //done
    private List<ServiceOrderItemRequest> serviceOrderItem;
    private List<NoteRequest> note; //done
    private List<ServiceOrderErrorMessageRequest> errorMessage; //done
    private List<ServiceOrderMilestoneRequest> milestone; //done
    private List<ServiceOrderJeopardyAlertRequest> jeopardyAlert; //done
    private List<RelatedPartyRequest> relatedParty; // done


}
