package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.RelatedParty;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrder;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


public class ServiceOrderRequest extends BaseBundled {
    private Timestamp cancellationDate;
    private String cancellationReason;
    private String category;
    private String description;
    private String externalId;
    private String notificationContact;
    private String priority;
    @JsonProperty("requestedCompletionDate")
    private Timestamp expectedCompletionDate;
    @JsonProperty("requestedStartDate")
    private Timestamp startDate;
    private List<ExternalReferenceRequest> externalReference;
    private List<NoteRequest> note;
    private List<ServiceOrderRelationship> orderRelationship;
    private List<RelatedParty> relatedParty;
    private List<ServiceOrderItemRequest> serviceOrderItem;





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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getNotificationContact() {
        return notificationContact;
    }

    public void setNotificationContact(String notificationContact) {
        this.notificationContact = notificationContact;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Timestamp getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Timestamp expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public List<ExternalReferenceRequest> getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(List<ExternalReferenceRequest> externalReference) {
        this.externalReference = externalReference;
    }

    public List<NoteRequest> getNote() {
        return note;
    }

    public void setNote(List<NoteRequest> note) {
        this.note = note;
    }

    public List<ServiceOrderRelationship> getOrderRelationship() {
        return orderRelationship;
    }

    public void setOrderRelationship(List<ServiceOrderRelationship> orderRelationship) {
        this.orderRelationship = orderRelationship;
    }

    public List<RelatedParty> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedParty> relatedParty) {
        this.relatedParty = relatedParty;
    }

    public List<ServiceOrderItemRequest> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<ServiceOrderItemRequest> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }
}
