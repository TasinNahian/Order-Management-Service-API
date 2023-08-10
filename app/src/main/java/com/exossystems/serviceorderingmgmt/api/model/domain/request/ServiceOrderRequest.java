package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceOrderRequest extends BaseBundled implements Serializable {
    private Timestamp cancellationDate;
    private String cancellationReason;
    private String category;
    private Timestamp completionDate;
    private String description;
    private Timestamp expectedCompletionDate;
    private String externalId;
    private String href;
    private String id;
    private String notificationContact;
    private Timestamp orderDate;
    private String priority;
    private Timestamp requestedCompletionDate;
    private Timestamp requestedStartDate;
    private Timestamp startDate;
    private String state;
    private List<ExternalReferenceRequest> externalReference; //done
    private List<ServiceOrderRelationshipRequest> orderRelationship; //done
    private List<ServiceOrderItemRequest> serviceOrderItem;
    private List<NoteRequest> note; //done
    private List<ServiceOrderErrorMessageRequest> errorMessage; //done
    private List<ServiceOrderMilestoneRequest> milestone; //done
    private List<ServiceOrderJeopardyAlertRequest> jeopardyAlert; //done
    private List<RelatedPartyRequest> relatedParty; // done

    public Timestamp getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Timestamp cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Timestamp expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotificationContact() {
        return notificationContact;
    }

    public void setNotificationContact(String notificationContact) {
        this.notificationContact = notificationContact;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Timestamp getRequestedCompletionDate() {
        return requestedCompletionDate;
    }

    public void setRequestedCompletionDate(Timestamp requestedCompletionDate) {
        this.requestedCompletionDate = requestedCompletionDate;
    }

    public Timestamp getRequestedStartDate() {
        return requestedStartDate;
    }

    public void setRequestedStartDate(Timestamp requestedStartDate) {
        this.requestedStartDate = requestedStartDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ExternalReferenceRequest> getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(List<ExternalReferenceRequest> externalReference) {
        this.externalReference = externalReference;
    }

    public List<ServiceOrderRelationshipRequest> getOrderRelationship() {
        return orderRelationship;
    }

    public void setOrderRelationship(List<ServiceOrderRelationshipRequest> orderRelationship) {
        this.orderRelationship = orderRelationship;
    }

    public List<ServiceOrderItemRequest> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<ServiceOrderItemRequest> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }

    public List<NoteRequest> getNote() {
        return note;
    }

    public void setNote(List<NoteRequest> note) {
        this.note = note;
    }

    public List<ServiceOrderErrorMessageRequest> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ServiceOrderErrorMessageRequest> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ServiceOrderMilestoneRequest> getMilestone() {
        return milestone;
    }

    public void setMilestone(List<ServiceOrderMilestoneRequest> milestone) {
        this.milestone = milestone;
    }

    public List<ServiceOrderJeopardyAlertRequest> getJeopardyAlert() {
        return jeopardyAlert;
    }

    public void setJeopardyAlert(List<ServiceOrderJeopardyAlertRequest> jeopardyAlert) {
        this.jeopardyAlert = jeopardyAlert;
    }

    public List<RelatedPartyRequest> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedPartyRequest> relatedParty) {
        this.relatedParty = relatedParty;
    }
}
