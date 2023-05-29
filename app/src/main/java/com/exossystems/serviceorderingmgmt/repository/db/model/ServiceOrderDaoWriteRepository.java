package com.exossystems.serviceorderingmgmt.repository.db.model;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.ValidForRequest;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;

public interface ServiceOrderDaoWriteRepository {

    Long getNextSequenceValue(String sequenceName);

    boolean saveServiceOrder(ServiceOrder serviceOrder);

    boolean saveExternalReference(ExternalReference externalReference);

    boolean saveServiceOrderRelationship(ServiceOrderRelationship serviceOrderRelationship);

    void saveNote(Note note);

    void saveRelatedParty(RelatedParty relatedParty);

    void saveServiceOrderErrorMessage(ServiceOrderErrorMessage serviceOrderErrorMessage);

    void saveServiceOrderMilestone(ServiceOrderMilestone serviceOrderMilestone);

    void saveServiceJeopardyAlert(ServiceOrderJeopardyAlert serviceOrderJeopardyAlert);

    void saveServiceOrderItem(ServiceOrderItem serviceOrderItem);

    void saveServiceOrderItemRelationship(ServiceOrderItemRelationship serviceOrderItemRelationship);

    void saveServiceOrderItemRef(ServiceOrderItemRef serviceOrderItemRef);

    void saveServiceOrderItemErrorMessage(ServiceOrderItemErrorMessage serviceOrderItemErrorMessage);

    void saveAppointmentRef(AppointmentRef appointmentRef);

    void saveServiceRefOrValue(ServiceRefOrValue serviceRefOrValue);

    void saveServiceRelationshipRequest(ServiceRelationship serviceRelationship);

    void saveCharacteristic(Characteristic characteristic);

    void saveCharacterRelationship(CharacteristicRelationship characteristicRelationship);

    void saveFeature(Feature feature);

    void saveConstraint(Constraint constraint);

    void saveFeatureRelationship(FeatureRelationship featureRelationship);
    void saveValidFor(ValidFor validFor);

    void saveRelatedPlaceRefOrValue(RelatedPlaceRefOrValue relatedPlaceRefOrValue);

    void saveRelatedEntityRefOrValue(RelatedEntityRefOrValue relatedEntityRefOrValue);

    void saveRelatedServiceOrderItem(RelatedServiceOrderItem relatedServiceOrderItem);

    void saveResourceRef(ResourceRef resourceRef);

    void saveServiceSpecificationRef(ServiceSpecificationRef serviceSpecificationRef);

    Boolean saveServiceOrderJson(String serviceOrderId, String jsonData);
}
