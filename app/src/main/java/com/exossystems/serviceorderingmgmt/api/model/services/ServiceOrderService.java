package com.exossystems.serviceorderingmgmt.api.model.services;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.AppointmentRef;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.Note;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.*;

import java.util.List;

public interface ServiceOrderService {
     void saveAppointmentRefRequest(AppointmentRef appointmentRef);
     void saveCharacteristicRequest(List<ServiceCharacteristicRequest> serviceCharacteristic, String featureId, String serviceId, String serviceRelationshipId);
     void saveErrorMessageRequest(ServiceOrderRequest serviceOrderRequest);
     void saveExternalReferenceRequest(ExternalReferenceRequest externalReferenceRequest);
     void saveFeatureRequest(List<FeatureRequest> featureList, String service_id);
     void saveFeatureRelationshipRequest(ServiceOrderRequest serviceOrderRequest);
     void saveItemRequest(List<Item> serviceOrderItemList,String serviceOrderHref, String serviceOrderId)
     void saveNoteRequest(List<NoteRequest> node);
     void saveOrderItemRequest(ServiceOrderRequest serviceOrderRequest);
     void savePlaceRequest(ServiceOrderRequest serviceOrderRequest);
     void saveRelatedPartyRequest(ServiceOrderRequest serviceOrderRequest);
     void saveResourceRefRequest(ServiceOrderRequest serviceOrderRequest);
    public void saveServiceRequest(ServiceRequest serviceRequest, String serviceOrderHref, String serviceOrderId);
    void saveServiceOrderRequest(ServiceOrderRequest serviceOrderRequest);
     void saveServiceOrderItemRequest(List<ServiceOrderItemRequest> serviceOrderItemRequestList, String serviceOrderHref, String serviceOrderId);     void saveOrderItemRelationshipRequest(ServiceOrderRequest serviceOrderRequest);
     void saveOrderRelationshipRequest(ServiceOrderRequest serviceOrderRequest);
     void saveServiceRelationshipRequest(ServiceOrderRequest serviceOrderRequest);
     void saveSpecificationRefRequest(ServiceOrderRequest serviceOrderRequest);
     void saveSupportingServiceRequest(ServiceOrderRequest serviceOrderRequest);
     void saveValidForRequest(ServiceOrderRequest serviceOrderRequest);
    void saveServiceCharacteristicRequest(List<ServiceCharacteristicRequest> serviceCharacteristic, String featureId, String serviceId, String serviceRelationshipId);
    void saveCharacteristicRelationshipRequest(List<CharacteristicRelationshipRequest> characteristicRelationshipList, String characteristicId);

}
