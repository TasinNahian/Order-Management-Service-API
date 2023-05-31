package com.exossystems.serviceorderingmgmt.repository.db.model;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;

public interface ServiceOrderDaoWriteRepository {

    Long getNextSequenceValue(String sequenceName);

    boolean saveServiceOrder(ServiceOrder serviceOrder);

    boolean saveExternalReference(ExternalReference externalReference);

    boolean saveServiceOrderRelationship(ServiceOrderRelationship serviceOrderRelationship);

    Boolean saveNote(Note note);

    Boolean saveRelatedParty(RelatedParty relatedParty);

    Boolean saveServiceOrderErrorMessage(ServiceOrderErrorMessage serviceOrderErrorMessage);

    Boolean saveServiceOrderMilestone(ServiceOrderMilestone serviceOrderMilestone);

    Boolean saveServiceJeopardyAlert(ServiceOrderJeopardyAlert serviceOrderJeopardyAlert);

    Boolean saveServiceOrderItem(ServiceOrderItem serviceOrderItem);

    Boolean saveServiceOrderItemRelationship(ServiceOrderItemRelationship serviceOrderItemRelationship);

    Boolean saveServiceOrderItemRef(ServiceOrderItemRef serviceOrderItemRef);

    Boolean saveServiceOrderItemErrorMessage(ServiceOrderItemErrorMessage serviceOrderItemErrorMessage);

    Boolean saveAppointmentRef(AppointmentRef appointmentRef);

    Boolean saveServiceRefOrValue(ServiceRefOrValue serviceRefOrValue);

    Boolean saveServiceRelationshipRequest(ServiceRelationship serviceRelationship);

    Boolean saveCharacteristic(Characteristic characteristic);

    Boolean saveCharacterRelationship(CharacteristicRelationship characteristicRelationship);

    Boolean saveFeature(Feature feature);

    Boolean saveConstraint(Constraint constraint);

    Boolean saveFeatureRelationship(FeatureRelationship featureRelationship);
    Boolean saveValidFor(ValidFor validFor);

    Boolean saveRelatedPlaceRefOrValue(RelatedPlaceRefOrValue relatedPlaceRefOrValue);

    Boolean saveRelatedEntityRefOrValue(RelatedEntityRefOrValue relatedEntityRefOrValue);

    Boolean saveRelatedServiceOrderItem(RelatedServiceOrderItem relatedServiceOrderItem);

    Boolean saveResourceRef(ResourceRef resourceRef);

    Boolean saveServiceSpecificationRef(ServiceSpecificationRef serviceSpecificationRef);

    Boolean saveServiceOrderJson(String serviceOrderId, String jsonData);
}
