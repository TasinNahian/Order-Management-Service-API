package com.exossystems.serviceorderingmgmt.api.model.services;

import com.exossystems.serviceorderingmgmt.api.model.domain.request.*;

import java.util.List;

public interface ServiceOrderRequestService {

    void saveServiceOrderRequest(ServiceOrderRequest serviceOrderRequest);
    void saveExternalReferenceRequest(List<ExternalReferenceRequest> externalReferenceRequestList, String serviceOrderId);
    void saveServiceOrderRelationshipRequest(List<ServiceOrderRelationshipRequest> serviceOrderRelationshipRequestList, String serviceOrderId);
    void saveServiceOrderItemRequest(List<ServiceOrderItemRequest> serviceOrderItemRequestList, String serviceOrderId);
    void saveNoteRequest( List<NoteRequest> noteRequestList, String serviceOrderId, String serviceRefOrValueId);
    void saveRelatedPartyRequest(List<RelatedPartyRequest> relatedPartyRequestList, String serviceOrderId, String serviceRefOrValueId);
    void saveServiceOrderErrorMessageRequest(List<ServiceOrderErrorMessageRequest> serviceOrderErrorMessageRequestList,String serviceOrderId);

    void saveServiceOrderMilestoneRequest(List<ServiceOrderMilestoneRequest> serviceOrderMilestoneRequestList,String serviceOrderId);

    void saveJeopardyAlertRequest(List<ServiceOrderJeopardyAlertRequest> serviceOrderJeopardyAlertRequestList,String serviceOrderId);
    void saveServiceOrderItemRelationshipRequest(List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationshipRequestList,String serviceOrderItemId);
    void saveServiceOrderItemErrorMessageRequest(List<ServiceOrderItemErrorMessageRequest> serviceOrderItemErrorMessageRequestList,String serviceOrderItemId);
    void saveAppointmentRefRequest(AppointmentRefRequest appointmentRefRequest,String serviceOrderItemId);
    void saveServiceOrderItemRefRequest(List<ServiceOrderItemRefRequest> serviceOrderItemRefRequestList, String serviceOrderItemRelationshipId, String serviceOrderErrorMessageId, String serviceOrderMilestoneId, String serviceOrderJeopardyAlertId);
    void saveServiceRefOrValueRequest(ServiceRefOrValueRequest serviceRefOrValueRequest,String serviceOrderItemId);
    void saveServiceRelationshipRequest(List<ServiceRelationshipRequest> serviceRelationship,String serviceRefOrValueId);
    void saveCharacteristicRequest(List<CharacteristicRequest> serviceCharacteristicList,String featureId, String serviceRefOrValueId, String serviceRelationshipId);
    void saveCharacteristicRelationshipRequest(List<CharacteristicRelationshipRequest> characteristicRelationshipList, String characteristicId);

    void saveFeatureRequest(List<FeatureRequest> feature, String serviceRefOrValueId);
    void saveConstraintRequest(List<ConstraintRequest> constraintRequestList, String featureId);

    void saveFeatureRelationshipRequest(List<FeatureRelationshipRequest> featureRelationshipRequestList, String featureId);

    void saveRelatedPlaceRefOrValueRequest(List<RelatedPlaceRefOrValueRequest> place, String serviceRefOrValueId);
    void saveRelatedEntityRefOrValueRequest(List<RelatedEntityRefOrValueRequest>relatedEntityRefOrValueRequestList, String serviceRefOrValueId);
    void saveRelatedServiceOrderItemRequest(List<RelatedServiceOrderItemRequest> serviceOrderItemRequestList, String serviceRefOrValueId);
    void saveResourceRefRequest(List<ResourceRefRequest>supportingResource, String serviceRefOrValueId);
    void saveServiceSpecificationRefRequest(List<ServiceSpecificationRefRequest> serviceSpecification, String serviceRefOrValueId);


}
