package com.exossystems.serviceorderingmgmt.api.model.services.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.Item;
import com.exossystems.serviceorderingmgmt.api.model.services.ServiceOrderService;
import com.exossystems.serviceorderingmgmt.api.util.Defs;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoWriteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceOrderServiceImpl implements ServiceOrderService {
    private final ServiceOrderDaoWriteRepository serviceOrderDaoWriteRepository;
    private static final Logger LOGGER = LogManager.getLogger(ServiceOrderServiceImpl.class);
    @Override
    public void saveServiceOrderRequest(ServiceOrderRequest serviceOrderRequest) {
        ServiceOrder serviceOrder = new ServiceOrder();
        String serviceOrderId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();
        String serviceOrderHref = "";
        serviceOrder.setId(serviceOrderId);
        BeanUtils.copyProperties(serviceOrderRequest, serviceOrder);
        LOGGER.info("Saving Service Order ...");
        //save ServiceOrder
        serviceOrderDaoWriteRepository.saveServiceOrder(serviceOrder);
        //externalReferenceRequest
        if (serviceOrderRequest.getExternalReference() != null && serviceOrderRequest.getExternalReference().size() > 0){
            LOGGER.info("Saving External Reference Order ...");
            List<ExternalReferenceRequest> externalReference = serviceOrderRequest.getExternalReference();
            saveExternalReferenceRequest(externalReference);
        }
        //noteRequest
        if (serviceOrderRequest.getNote() != null && serviceOrderRequest.getNote().size() > 0) {
            List<NoteRequest> note = serviceOrderRequest.getNote();
            saveNoteRequest(note);
        }
        //orderRelationship
        if (serviceOrderRequest.getOrderRelationship() != null && serviceOrderRequest.getOrderRelationship().size() > 0) {
            List<ServiceOrderRelationship> orderRelationshipList = serviceOrderRequest.getOrderRelationship();
            for (ServiceOrderRelationship orderRelationship : orderRelationshipList) {
                saveOrderRelationship(orderRelationship);
            }
        }
        //relatedParty
        if (serviceOrderRequest.getRelatedParty() != null && serviceOrderRequest.getRelatedParty().size() > 0) {
            List<RelatedParty> relatedParty = serviceOrderRequest.getRelatedParty();
            for (RelatedParty relatedParty : serviceOrderRequest.getRelatedParty()) {
                serviceOrderDaoWriteRepository.saveRelatedParty(relatedParty);
            }
        }
        //serviceOrderItem
        if (serviceOrderRequest.getServiceOrderItem() != null && serviceOrderRequest.getServiceOrderItem().size() > 0) {
            List<ServiceOrderItemRequest> serviceOrderItem = serviceOrderRequest.getServiceOrderItem();
            saveServiceOrderItemRequest(serviceOrderItem, serviceOrderHref, serviceOrderId);
        }
    }

    @Override
    public void saveAppointmentRefRequest(AppointmentRef appointmentRef) {
        LOGGER.info("Saving Service Order ...");
        serviceOrderDaoWriteRepository.saveAppointmentRef(appointmentRef);

    }

    @Override
    public void saveServiceCharacteristicRequest(List<ServiceCharacteristicRequest> serviceCharacteristic, String featureId, String serviceId, String serviceRelationshipId) {
        for (ServiceCharacteristicRequest serviceCharacteristicRequest : serviceCharacteristic) {
            Characteristic characteristic = new Characteristic();
            BeanUtils.copyProperties(serviceCharacteristicRequest, characteristic);
            String characteristicId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();
            characteristic.setId(characteristicId);
            characteristic.setFeatureId(featureId);
            characteristic.setServiceId(serviceId);
            characteristic.setServiceRelationshipId(serviceRelationshipId);
            serviceOrderDaoWriteRepository.saveCharacteristic(characteristic);
            if(serviceCharacteristicRequest.getCharacteristicRelationship() !=null && serviceCharacteristicRequest.getCharacteristicRelationship().size() >0){
                List<CharacteristicRelationshipRequest> characteristicRelationship = serviceCharacteristicRequest.getCharacteristicRelationship();
                saveCharacteristicRelationshipRequest(characteristicRelationship, characteristicId);
            }
        }

    }
    @Override
    public void saveCharacteristicRelationshipRequest(List<CharacteristicRelationshipRequest> characteristicRelationshipList, String characteristicId){
        for (CharacteristicRelationshipRequest characteristicRelationshipRequest : characteristicRelationshipList) {

            ServiceOrderRelationship characteristicRelationship = new ServiceOrderRelationship();
            BeanUtils.copyProperties(characteristicRelationshipRequest, characteristicRelationship);
            characteristicRelationship.setId(characteristicId);
            serviceOrderDaoWriteRepository.saveCharacteristicRelationship(characteristicRelationship);
        }
    }

    @Override
    public void saveErrorMessageRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveExternalReferenceRequest(ExternalReferenceRequest externalReferenceRequest) {


    }

    @Override
    public void saveFeatureRequest(List<FeatureRequest> featureList, String service_id) {
        Feature feature = new Feature();
        feature.setServiceId(service_id);
        for(FeatureRequest featureRequest: featureList){
            BeanUtils.copyProperties(featureRequest, feature);
            serviceOrderDaoWriteRepository.saveFeature(feature);

        }

    }

    @Override
    public void saveFeatureRelationshipRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveItemRequest(List<Item> serviceOrderItemList,String serviceOrderHref, String serviceOrderId) {
        for(Item servceOrderItem : serviceOrderItemList){
            servceOrderItem.setServiceOrderHref(serviceOrderHref);
            servceOrderItem.setServiceOrderId(serviceOrderId);
            serviceOrderDaoWriteRepository.saveItemRequest(servceOrderItem);
        }
    }

    @Override
    public void saveNoteRequest(List<NoteRequest> node) {
        NoteRequest noteRequest;
        int noteRequestSize = node.size();
        for (int i = 0; i < noteRequestSize; i++) {
            noteRequest = node.get(i);
            Note note = new Note();
            note.setId(noteRequest.getId().get(i));
            note.setAuthor(noteRequest.getAuthor().get(i));
            note.setDate(noteRequest.getDate().get(i));
            note.setText(noteRequest.getText().get(i));
            note.setBaseType(noteRequest.getBaseType());
            note.setBaseType(noteRequest.getSchemaLocation());
            note.setBaseType(noteRequest.getType());
            serviceOrderDaoWriteRepository.saveNote(note);
            LOGGER.info("Saving Service Order --Note = " + noteRequest.getId().get(i));
            System.out.println(note);
        }
    }

    @Override
    public void saveOrderItemRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void savePlaceRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveRelatedPartyRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveResourceRefRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveServiceRequest(ServiceRequest serviceRequest, String serviceOrderHref, String serviceOrderId) {
        ServiceRef service = new ServiceRef();
        String serviceId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();
        BeanUtils.copyProperties(serviceRequest, service);
        if (serviceRequest.getFeature() != null && serviceRequest.getFeature().size() > 0){
            List<FeatureRequest> feature = serviceRequest.getFeature();
            saveFeatureRequest(feature, serviceRequest.getId());
        }
        if (serviceRequest.getNote() != null && serviceRequest.getNote().size() > 0){
            List<NoteRequest> note = serviceRequest.getNote();
            saveNoteRequest(note,null, serviceId);
        }
        if (serviceRequest.getRelatedEntity() != null && serviceRequest.getRelatedEntity().size() > 0){
            List<RelatedParty> relatedEntity = serviceRequest.getRelatedEntity();
            saveRelatedEntityRequest(relatedEntity,null, serviceId);
        }
        if (serviceRequest.getRelatedParty() != null && serviceRequest.getRelatedParty().size() > 0){
            List<RelatedParty> relatedParty = serviceRequest.getRelatedEntity();
            saveRelatedPartyRequest(relatedParty, null, serviceId);
        }
        if (serviceRequest.getServiceCharacteristic() != null && serviceRequest.getServiceCharacteristic().size() > 0){
            List<ServiceCharacteristicRequest> serviceCharacteristic = serviceRequest.getServiceCharacteristic();
            saveServiceCharacteristicRequest(serviceCharacteristic,null, serviceId, null);
        }
        if (serviceRequest.getItem() != null && serviceRequest.getItem().size() > 0){
            List<Item> serviceOrderItem = serviceRequest.getServiceOrderItem();
            saveItemRequest(serviceOrderItem, serviceOrderHref, serviceOrderId);
        }
        if (serviceRequest.getServiceSpecification() != null){
            ServiceSpecificationRef serviceSpecification = serviceRequest.getServiceSpecification();
            saveServiceSpecificationRequest(serviceSpecification, serviceId);
        }
        if(serviceRequest.getSupportingResource() !=null && serviceRequest.getSupportingResource().size() >0) {
            List<ResourceRef> supportingResource = serviceRequest.getSupportingResource();
            saveSupportingResource(supportingResource, serviceId);

        }
        if(serviceRequest.getSupportingService() !=null && serviceRequest.getSupportingService().size()>0){
            saveSupportingServiceRequest(serviceRequest.getSupportingService());
        }
        }


    @Override
    public void saveServiceOrderItemRequest(List<ServiceOrderItemRequest> serviceOrderItemRequestList, String serviceOrderHref, String serviceOrderId) {

        for(ServiceOrderItemRequest serviceOrderItemRequest : serviceOrderItemRequestList) {
            ServiceOrderItem serviceOrderItem = new ServiceOrderItem();
//        String serviceOrderItemId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();
//        serviceOrderItem.setId(serviceOrderItemId);
            BeanUtils.copyProperties(serviceOrderItemRequest, serviceOrderItem);
            LOGGER.info("Saving Service Order Item ...");
            serviceOrderDaoWriteRepository.saveServiceOrderItem(serviceOrderItem);
            if (serviceOrderItemRequest.getAppointment() != null) {
                AppointmentRef appointment = serviceOrderItemRequest.getAppointment();
                saveAppointmentRefRequest(appointment);
            }
            if (serviceOrderItemRequest.getErrorMessage() != null &&serviceOrderItemRequest.getErrorMessage().size()>0) {
                ServiceRequest service = serviceOrderItemRequest.getService();
                saveServiceRequest(service, serviceOrderHref, serviceOrderId);
            }
            if (serviceOrderItemRequest.getService() != null) {
                ServiceRequest service = serviceOrderItemRequest.getService();
                saveServiceRequest(service, serviceOrderHref, serviceOrderId);
            }
            if (serviceOrderItemRequest.getServiceOrderItem() != null && serviceOrderItemRequest.getServiceOrderItem().size() > 0) {
//                saveServiceOrderItemRequest(serviceOrderItemRequest.getServiceOrderItem());
            }
            if (serviceOrderItemRequest.getServiceOrderItemRelationship() != null && serviceOrderItemRequest.getServiceOrderItemRelationship().size() >0) {
                saveServiceOrderItemRelationshipRequest(serviceOrderItemRequest.getServiceOrderItemRelationship());
            }
        }

    }

    @Override
    public void saveOrderItemRelationshipRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveOrderRelationshipRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveServiceRelationshipRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveSpecificationRefRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveSupportingServiceRequest(ServiceOrderRequest serviceOrderRequest) {

    }

    @Override
    public void saveValidForRequest(ServiceOrderRequest serviceOrderRequest) {

    }


}
