package com.exossystems.serviceorderingmgmt.api.model.services.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.Item;
import com.exossystems.serviceorderingmgmt.api.model.services.ServiceOrderRequestService;
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
public class ServiceOrderServiceImpl implements ServiceOrderRequestService {
    private final ServiceOrderDaoWriteRepository serviceOrderDaoWriteRepository;


    private static final Logger LOGGER = LogManager.getLogger(ServiceOrderServiceImpl.class);
    @Override
    public void saveServiceOrderRequest(ServiceOrderRequest serviceOrderRequest) {
        String serviceOrderId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();

        serviceOrderRequest.setId(serviceOrderId);

        ServiceOrder serviceOrder = new ServiceOrder();

        BeanUtils.copyProperties(serviceOrderRequest, serviceOrder);

        LOGGER.info("Saving Service Order ...");
        //save ServiceOrder in db
        //todo
        serviceOrderDaoWriteRepository.saveServiceOrder(serviceOrder);

        //externalReferenceRequest
        if (serviceOrderRequest.getExternalReference() != null && serviceOrderRequest.getExternalReference().size() > 0){
            List<ExternalReferenceRequest> externalReferenceRequestListList = serviceOrderRequest.getExternalReference();
            LOGGER.info("Saving externalReferenceRequest ...");
            saveExternalReferenceRequest(externalReferenceRequestListList, serviceOrderId);
        }
        //orderRelationship
        if (serviceOrderRequest.getOrderRelationship() != null && serviceOrderRequest.getOrderRelationship().size() > 0) {
            List<ServiceOrderRelationshipRequest> serviceOrderRelationshipRequestList = serviceOrderRequest.getOrderRelationship();
            LOGGER.info("Saving ServiceOrderRelationshipRequest ...");
            saveServiceOrderRelationshipRequest(serviceOrderRelationshipRequestList, serviceOrderId);
            }
        //serviceOrderItem
        if (serviceOrderRequest.getServiceOrderItem() != null && serviceOrderRequest.getServiceOrderItem().size() > 0) {
            List<ServiceOrderItemRequest> serviceOrderItemRequestList = serviceOrderRequest.getServiceOrderItem();
            LOGGER.info("Saving ServiceOrderItemRequest ...");
            saveServiceOrderItemRequest(serviceOrderItemRequestList, serviceOrderId);
        }
        //note
        if (serviceOrderRequest.getNote() != null && serviceOrderRequest.getNote().size() > 0) {
            List<NoteRequest> noteRequestList = serviceOrderRequest.getNote();
            LOGGER.info("Saving ServiceOrderNoteRequest ...");
            saveNoteRequest(noteRequestList, serviceOrderId,null);
        }
        //errorMessage
        if (serviceOrderRequest.getErrorMessage() != null && serviceOrderRequest.getErrorMessage().size() > 0) {
            List<ServiceOrderErrorMessageRequest> serviceOrderErrorMessageRequestList = serviceOrderRequest.getErrorMessage();
            LOGGER.info("Saving ServiceOrderErrorMessageRequest ...");
            saveServiceOrderErrorMessageRequest(serviceOrderErrorMessageRequestList, serviceOrderId);
        }
        //milestone
        if (serviceOrderRequest.getMilestone() != null && serviceOrderRequest.getMilestone().size() > 0) {
            List<ServiceOrderMilestoneRequest> serviceOrderMilestoneRequestList = serviceOrderRequest.getMilestone();
            LOGGER.info("Saving ServiceOrderMilestoneRequest ...");
            saveServiceOrderMilestoneRequest(serviceOrderMilestoneRequestList, serviceOrderId);
        }
        //jeopardyAlert
        if (serviceOrderRequest.getJeopardyAlert() != null && serviceOrderRequest.getJeopardyAlert().size() > 0) {
            List<ServiceOrderJeopardyAlertRequest> serviceOrderJeopardyAlertRequestList = serviceOrderRequest.getJeopardyAlert();
            LOGGER.info("Saving JeopardyAlertRequest ...");
            saveJeopardyAlertRequest(serviceOrderJeopardyAlertRequestList, serviceOrderId);
        }
        //relatedParty
        if (serviceOrderRequest.getRelatedParty() != null && serviceOrderRequest.getRelatedParty().size() > 0) {
            List<RelatedPartyRequest> relatedPartyRequestList = serviceOrderRequest.getRelatedParty();
            LOGGER.info("Saving RelatedPartyRequest ...");
            saveRelatedPartyRequest(relatedPartyRequestList, serviceOrderId, null);
        }

    }

    @Override
    public void saveExternalReferenceRequest(List<ExternalReferenceRequest> externalReferenceRequestList, String serviceOrderId) {
        for(ExternalReferenceRequest externalReferenceRequest: externalReferenceRequestList) {
            ExternalReference externalReference = new ExternalReference();

            BeanUtils.copyProperties(externalReferenceRequest, externalReference);

            externalReference.setServiceOrderId(serviceOrderId);

            externalReference.setBaseType(externalReferenceRequest.getBaseType());
            externalReference.setSchemaLocation(externalReferenceRequest.getSchemaLocation());
            externalReference.setType(externalReferenceRequest.getType());

            //to do
            serviceOrderDaoWriteRepository.saveExternalReference(externalReference);
        }
    }

    @Override
    public void saveServiceOrderRelationshipRequest(List<ServiceOrderRelationshipRequest> serviceOrderRelationshipRequestList, String serviceOrderId) {
        for(ServiceOrderRelationshipRequest serviceOrderRelationshipRequest : serviceOrderRelationshipRequestList){
            ServiceOrderRelationship serviceOrderRelationship = new ServiceOrderRelationship();

            BeanUtils.copyProperties(serviceOrderRelationshipRequest, serviceOrderRelationship);

            serviceOrderRelationship.setServiceOrderId(serviceOrderId);

            serviceOrderRelationship.setBaseType(serviceOrderRelationshipRequest.getBaseType());
            serviceOrderRelationship.setSchemaLocation(serviceOrderRelationshipRequest.getSchemaLocation());
            serviceOrderRelationship.setType(serviceOrderRelationshipRequest.getType());

            LOGGER.info("Saving ServiceOrderRelationshipRequest ...");
            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderRelationship(serviceOrderRelationship);
        }
    }

    @Override
    public void saveNoteRequest(List<NoteRequest> noteRequestList, String serviceOrderId, String serviceRefOrValueId) {
        for(NoteRequest noteRequest : noteRequestList){
            Note note = new Note();

            BeanUtils.copyProperties(noteRequest, note);

            note.setServiceOrderId(serviceOrderId);
            note.setServiceRefOrValueId(serviceRefOrValueId);

            note.setBaseType(noteRequest.getBaseType());
            note.setSchemaLocation(noteRequest.getSchemaLocation());
            note.setType(noteRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveNote(note);
        }

    }

    @Override
    public void saveRelatedPartyRequest(List<RelatedPartyRequest> relatedPartyRequestList, String serviceOrderId, String serviceRefOrValueId) {
        for(RelatedPartyRequest relatedPartyRequest : relatedPartyRequestList){

            RelatedParty relatedParty = new RelatedParty();

            BeanUtils.copyProperties(relatedPartyRequest, relatedParty);

            relatedParty.setServiceOrderId(serviceOrderId);
            relatedParty.setServiceRefOrValueId(serviceRefOrValueId);

            relatedParty.setBaseType(relatedPartyRequest.getBaseType());
            relatedParty.setSchemaLocation(relatedPartyRequest.getSchemaLocation());
            relatedParty.setType(relatedPartyRequest.getType());
            // todo
            serviceOrderDaoWriteRepository.saveRelatedParty(relatedParty);
        }

    }

    @Override
    public void saveServiceOrderErrorMessageRequest(List<ServiceOrderErrorMessageRequest> serviceOrderErrorMessageRequestList, String serviceOrderId) {
        for(ServiceOrderErrorMessageRequest serviceOrderErrorMessageRequest : serviceOrderErrorMessageRequestList){

            ServiceOrderErrorMessage serviceOrderErrorMessage = new ServiceOrderErrorMessage();

            BeanUtils.copyProperties(serviceOrderErrorMessageRequest, serviceOrderErrorMessage);

            String serviceOrderErrorMessageId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();;

            serviceOrderErrorMessage.setId(serviceOrderErrorMessageId);
            serviceOrderErrorMessage.setServiceOrderId(serviceOrderId);

            serviceOrderErrorMessage.setBaseType(serviceOrderErrorMessageRequest.getBaseType());
            serviceOrderErrorMessage.setSchemaLocation(serviceOrderErrorMessageRequest.getSchemaLocation());
            serviceOrderErrorMessage.setType(serviceOrderErrorMessageRequest.getType());
            //to do
            serviceOrderDaoWriteRepository.saveServiceOrderErrorMessage(serviceOrderErrorMessage);

            if(serviceOrderErrorMessageRequest.getServiceOrderItem() !=null && serviceOrderErrorMessageRequest.getServiceOrderItem().size() > 0){
                List<ServiceOrderItemRefRequest> serviceOrderItem = serviceOrderErrorMessageRequest.getServiceOrderItem();
                saveServiceOrderItemRefRequest(serviceOrderItem, null,serviceOrderErrorMessageId,null,null);
            }
        }
    }

    @Override
    public void saveServiceOrderMilestoneRequest(List<ServiceOrderMilestoneRequest> serviceOrderMilestoneRequestList, String serviceOrderId) {
        for(ServiceOrderMilestoneRequest serviceOrderMilestoneRequest : serviceOrderMilestoneRequestList){

            ServiceOrderMilestone serviceOrderMilestone = new ServiceOrderMilestone();

            BeanUtils.copyProperties(serviceOrderMilestoneRequest, serviceOrderMilestone);

            serviceOrderMilestone.setServiceOrderId(serviceOrderId);

            serviceOrderMilestone.setBaseType(serviceOrderMilestoneRequest.getBaseType());
            serviceOrderMilestone.setSchemaLocation(serviceOrderMilestoneRequest.getSchemaLocation());
            serviceOrderMilestone.setType(serviceOrderMilestoneRequest.getType());
            //to do
            serviceOrderDaoWriteRepository.saveServiceOrderMilestone(serviceOrderMilestone);

            if(serviceOrderMilestoneRequest.getServiceOrderItem() !=null && serviceOrderMilestoneRequest.getServiceOrderItem().size() > 0){
                List<ServiceOrderItemRefRequest> serviceOrderItem = serviceOrderMilestoneRequest.getServiceOrderItem();
                saveServiceOrderItemRefRequest(serviceOrderItem, null,null,serviceOrderMilestone.getId(),null);
            }
        }
    }

    @Override
    public void saveJeopardyAlertRequest(List<ServiceOrderJeopardyAlertRequest> serviceOrderJeopardyAlertRequestList, String serviceOrderId) {
        for(ServiceOrderJeopardyAlertRequest serviceOrderJeopardyAlertRequest : serviceOrderJeopardyAlertRequestList){

            ServiceOrderJeopardyAlert serviceOrderJeopardyAlert = new ServiceOrderJeopardyAlert();

            BeanUtils.copyProperties(serviceOrderJeopardyAlertRequest, serviceOrderJeopardyAlert);

            serviceOrderJeopardyAlert.setServiceOrderId(serviceOrderId);

            serviceOrderJeopardyAlert.setBaseType(serviceOrderJeopardyAlertRequest.getBaseType());
            serviceOrderJeopardyAlert.setSchemaLocation(serviceOrderJeopardyAlertRequest.getSchemaLocation());
            serviceOrderJeopardyAlert.setType(serviceOrderJeopardyAlertRequest.getType());
            // to do
            serviceOrderDaoWriteRepository.saveServiceJeopardyAlert(serviceOrderJeopardyAlert);

            if(serviceOrderJeopardyAlertRequest.getServiceOrderItem() !=null && serviceOrderJeopardyAlertRequest.getServiceOrderItem().size() > 0){
                List<ServiceOrderItemRefRequest> serviceOrderItem = serviceOrderJeopardyAlertRequest.getServiceOrderItem();
                saveServiceOrderItemRefRequest(serviceOrderItem, null,null,null,serviceOrderJeopardyAlert.getId());
            }
        }
    }


    @Override
    public void saveServiceOrderItemRequest(List<ServiceOrderItemRequest> serviceOrderItemRequestList, String serviceOrderId) {
        for(ServiceOrderItemRequest serviceOrderItemRequest : serviceOrderItemRequestList){

            ServiceOrderItem serviceOrderItem = new ServiceOrderItem();

            String serviceOrderItemId = serviceOrderItemRequest.getId();

            BeanUtils.copyProperties(serviceOrderItemRequest, serviceOrderItem);

            serviceOrderItem.setServiceOrderId(serviceOrderId);

            serviceOrderItem.setBaseType(serviceOrderItemRequest.getBaseType());
            serviceOrderItem.setSchemaLocation(serviceOrderItemRequest.getSchemaLocation());
            serviceOrderItem.setType(serviceOrderItemRequest.getType());
            //to do
            serviceOrderDaoWriteRepository.saveServiceOrderItem(serviceOrderItem); //to do

            if(serviceOrderItemRequest.getServiceOrderItemRelationship() != null && serviceOrderItemRequest.getServiceOrderItemRelationship().size() >0){

                List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationshipRequestList = serviceOrderItemRequest.getServiceOrderItemRelationship();

                saveServiceOrderItemRelationshipRequest(serviceOrderItemRelationshipRequestList, serviceOrderItemId);

            }
            if(serviceOrderItemRequest.getErrorMessage() != null && serviceOrderItemRequest.getErrorMessage().size() > 0){

                List<ServiceOrderItemErrorMessageRequest> serviceOrderItemErrorMessageRequestList = serviceOrderItemRequest.getErrorMessage();

                saveServiceOrderItemErrorMessageRequest(serviceOrderItemErrorMessageRequestList, serviceOrderItemId);

            }
            if(serviceOrderItemRequest.getAppointment() !=null){

                AppointmentRefRequest appointmentRefRequest = serviceOrderItemRequest.getAppointment();

                saveAppointmentRefRequest(appointmentRefRequest, serviceOrderItemId);

            }
            if(serviceOrderItemRequest.getService() !=null){

                ServiceRefOrValueRequest serviceRefOrValueRequest = serviceOrderItemRequest.getService();

                saveServiceRefOrValueRequest(serviceRefOrValueRequest, serviceOrderItemId);

            }
        }
    }

    @Override
    public void saveServiceOrderItemRelationshipRequest(List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationshipRequestList, String serviceOrderItemId) {
        for(ServiceOrderItemRelationshipRequest serviceOrderItemRelationshipRequest : serviceOrderItemRelationshipRequestList){

            ServiceOrderItemRelationship serviceOrderItemRelationship = new ServiceOrderItemRelationship();

            BeanUtils.copyProperties(serviceOrderItemRelationshipRequest, serviceOrderItemRelationship);
            //to do
            String serviceOrderItemRelationshipId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString();

            serviceOrderItemRelationship.setId(serviceOrderItemRelationshipId);
            serviceOrderItemRelationship.setServiceOrderItemId(serviceOrderItemId);

            serviceOrderItemRelationship.setBaseType(serviceOrderItemRelationshipRequest.getBaseType());
            serviceOrderItemRelationship.setSchemaLocation(serviceOrderItemRelationshipRequest.getSchemaLocation());
            serviceOrderItemRelationship.setType(serviceOrderItemRelationshipRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderItemRelationship(serviceOrderItemRelationship);

            if(serviceOrderItemRelationshipRequest.getOrderItem() != null && serviceOrderItemRelationshipRequest.getOrderItem().size() > 0){

                List<ServiceOrderItemRefRequest> serviceOrderItemRefRequestList = serviceOrderItemRelationshipRequest.getOrderItem();

                saveServiceOrderItemRefRequest(serviceOrderItemRefRequestList,serviceOrderItemRelationshipId, null, null,null);

            }
        }
    }
    @Override
    public void saveServiceOrderItemRefRequest(List<ServiceOrderItemRefRequest> serviceOrderItemRefRequestList,String serviceOrderItemRelationshipId,String serviceOrderErrorMessageId,String serviceOrderMilestoneId,String serviceOrderJeopardyAlertId){
        for(ServiceOrderItemRefRequest serviceOrderItemRefRequest : serviceOrderItemRefRequestList){

            ServiceOrderItemRef serviceOrderItemRef = new ServiceOrderItemRef();

            BeanUtils.copyProperties(serviceOrderItemRefRequest, serviceOrderItemRef);

            serviceOrderItemRef.setServiceOrderItemRelationshipId(serviceOrderItemRelationshipId);
            serviceOrderItemRef.setServiceOrderItemRelationshipId(serviceOrderErrorMessageId);
            serviceOrderItemRef.setServiceOrderItemRelationshipId(serviceOrderMilestoneId);
            serviceOrderItemRef.setServiceOrderItemRelationshipId(serviceOrderJeopardyAlertId);

            serviceOrderItemRef.setBaseType(serviceOrderItemRefRequest.getBaseType());
            serviceOrderItemRef.setSchemaLocation(serviceOrderItemRefRequest.getSchemaLocation());
            serviceOrderItemRef.setType(serviceOrderItemRefRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderItem(serviceOrderItemRef);
        }
    }


    @Override
    public void saveServiceOrderItemErrorMessageRequest(List<ServiceOrderItemErrorMessageRequest> serviceOrderItemErrorMessageRequestList, String serviceOrderItemId) {

        for(ServiceOrderItemErrorMessageRequest serviceOrderItemErrorMessageRequest : serviceOrderItemErrorMessageRequestList){

            ServiceOrderItemErrorMessage serviceOrderItemErrorMessage = new ServiceOrderItemErrorMessage();

            BeanUtils.copyProperties(serviceOrderItemErrorMessageRequest, serviceOrderItemErrorMessage);

            serviceOrderItemErrorMessage.setServiceOrderItemId(serviceOrderItemId);

            serviceOrderItemErrorMessage.setBaseType(serviceOrderItemErrorMessageRequest.getBaseType());
            serviceOrderItemErrorMessage.setSchemaLocation(serviceOrderItemErrorMessageRequest.getSchemaLocation());
            serviceOrderItemErrorMessage.setType(serviceOrderItemErrorMessageRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderItemErrorMessage(serviceOrderItemErrorMessage);
        }
    }

    @Override
    public void saveAppointmentRefRequest(AppointmentRefRequest appointmentRefRequest, String serviceOrderItemId) {

        AppointmentRef appointmentRef = new AppointmentRef();

        BeanUtils.copyProperties(appointmentRefRequest, appointmentRef);

        appointmentRef.setServiceOrderItemId(serviceOrderItemId);

        appointmentRef.setBaseType(appointmentRefRequest.getBaseType());
        appointmentRef.setSchemaLocation(appointmentRefRequest.getSchemaLocation());
        appointmentRef.setType(appointmentRefRequest.getType());

        serviceOrderDaoWriteRepository.saveAppointmentRef(appointmentRef);
    }


    @Override
    public void saveServiceRefOrValueRequest(ServiceRefOrValueRequest serviceRefOrValueRequest, String serviceOrderItemId) {
        ServiceRefOrValue serviceRefOrValue = new ServiceRefOrValue();

        String serviceRefOrValueId = serviceRefOrValueRequest.getId();

        BeanUtils.copyProperties(serviceRefOrValueRequest, serviceRefOrValue);

        serviceRefOrValue.setServiceOrderItemId(serviceOrderItemId);
        serviceRefOrValue.setBaseType(serviceRefOrValueRequest.getBaseType());
        serviceRefOrValue.setSchemaLocation(serviceRefOrValueRequest.getSchemaLocation());
        serviceRefOrValue.setType(serviceRefOrValueRequest.getType());

        //todo
        serviceOrderDaoWriteRepository.saveServiceRefOrValue(serviceRefOrValue);

        if(serviceRefOrValueRequest.getServiceRelationship() != null && serviceRefOrValueRequest.getServiceRelationship().size() > 0){

            List<ServiceRelationshipRequest> serviceRelationship = serviceRefOrValueRequest.getServiceRelationship();

            saveServiceRelationshipRequest(serviceRelationship, serviceRefOrValueId);

        }
        if(serviceRefOrValueRequest.getServiceCharacteristics() != null && serviceRefOrValueRequest.getServiceCharacteristics().size() > 0){

            List<CharacteristicRequest> serviceCharacteristics = serviceRefOrValueRequest.getServiceCharacteristics();

            saveCharacteristicRequest(serviceCharacteristics,null, serviceRefOrValueId, null);

        }
        if(serviceRefOrValueRequest.getRelatedParty() != null && serviceRefOrValueRequest.getRelatedParty().size() > 0){

            List<RelatedPartyRequest> relatedParty = serviceRefOrValueRequest.getRelatedParty();

            saveRelatedPartyRequest(relatedParty, null, serviceRefOrValueId);

        }
        if(serviceRefOrValueRequest.getFeature() != null && serviceRefOrValueRequest.getFeature().size() > 0){

            List<FeatureRequest> feature = serviceRefOrValueRequest.getFeature();

            saveFeatureRequest(feature, serviceRefOrValueId);

        }
        if(serviceRefOrValueRequest.getPlace() != null && serviceRefOrValueRequest.getPlace().size() > 0){

            List<RelatedPlaceRefOrValueRequest> place = serviceRefOrValueRequest.getPlace();

            saveRelatedPlaceRefOrValueRequest(place, serviceRefOrValueId);

        }
        if(serviceRefOrValueRequest.getRelatedEntity() != null && serviceRefOrValueRequest.getRelatedEntity().size() > 0){

            List<RelatedEntityRefOrValueRequest> relatedEntity = serviceRefOrValueRequest.getRelatedEntity();

            saveRelatedEntityRefOrValueRequest(relatedEntity, serviceRefOrValueId);


        }
        if(serviceRefOrValueRequest.getServiceOrderItem() != null && serviceRefOrValueRequest.getServiceOrderItem().size() > 0){

            List<RelatedServiceOrderItemRequest> serviceOrderItem = serviceRefOrValueRequest.getServiceOrderItem();

            saveRelatedServiceOrderItemRequest(serviceOrderItem, serviceRefOrValueId);


        }
        if(serviceRefOrValueRequest.getSupportingResource() != null && serviceRefOrValueRequest.getSupportingResource().size() > 0){

            List<ResourceRefRequest> supportingResource = serviceRefOrValueRequest.getSupportingResource();

            saveResourceRefRequest(supportingResource, serviceRefOrValueId);


        }
        if(serviceRefOrValueRequest.getServiceSpecification() != null && serviceRefOrValueRequest.getServiceSpecification().size() > 0){

            List<ServiceSpecificationRefRequest> serviceSpecification = serviceRefOrValueRequest.getServiceSpecification();

            saveServiceSpecificationRefRequest(serviceSpecification, serviceRefOrValueId);

        }
    }
    @Override
    public void saveServiceRelationshipRequest(List<ServiceRelationshipRequest> serviceRelationshipRequestList, String serviceRefOrValueId) {
       for(ServiceRelationshipRequest serviceRelationshipRequest : serviceRelationshipRequestList) {

           ServiceRelationship serviceRelationship = new ServiceRelationship();

           BeanUtils.copyProperties(serviceRelationshipRequest, serviceRelationship);

           serviceRelationship.setServiceRefOrValueId(serviceRefOrValueId);

           serviceRelationship.setBaseType(serviceRelationshipRequest.getBaseType());
           serviceRelationship.setSchemaLocation(serviceRelationshipRequest.getSchemaLocation());
           serviceRelationship.setType(serviceRelationshipRequest.getType());

           //todo
           serviceOrderDaoWriteRepository.saveServiceRelationshipRequest(serviceRelationship);

           List<CharacteristicRequest> serviceRelationshipCharacteristic = serviceRelationshipRequest.getServiceRelationshipCharacteristic();

           if(serviceRelationshipCharacteristic != null && serviceRelationshipCharacteristic.size() >0) {

               saveCharacteristicRequest(serviceRelationshipCharacteristic, null, null, serviceRelationship.getId() );

           }
       }

    }

    @Override
    public void saveCharacteristicRequest(List<CharacteristicRequest> serviceCharacteristicList,String featureId, String serviceRefOrValueId, String serviceRelationshipId) {
        for(CharacteristicRequest characteristicRequest : serviceCharacteristicList) {

            Characteristic characteristic = new Characteristic();

            BeanUtils.copyProperties(characteristicRequest, characteristic);

            characteristic.setFeatureId(featureId);
            characteristic.setServiceRefOrValueId(serviceRefOrValueId);
            characteristic.setServiceRelationshipId(serviceRelationshipId);

            characteristic.setBaseType(characteristicRequest.getBaseType());
            characteristic.setSchemaLocation(characteristicRequest.getSchemaLocation());
            characteristic.setType(characteristicRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveCharacteristic(characteristic);

            List<CharacteristicRelationshipRequest> characteristicRelationshipRequest = characteristicRequest.getCharacteristicRelationship();

            if (characteristicRelationshipRequest != null && characteristicRelationshipRequest.size() > 0) {

                saveCharacteristicRelationshipRequest(characteristicRelationshipRequest, characteristic.getId());
            }
        }
    }
    @Override
    public void saveCharacteristicRelationshipRequest(List<CharacteristicRelationshipRequest> characteristicRelationshipList, String characteristicId){
        for(CharacteristicRelationshipRequest characteristicRelationshipRequest : characteristicRelationshipList){

            CharacteristicRelationship characteristicRelationship = new CharacteristicRelationship();

            BeanUtils.copyProperties(characteristicRelationshipRequest, characteristicRelationship);

            characteristicRelationship.setCharacteristicId(characteristicId);
            characteristicRelationship.setBaseType(characteristicRelationshipRequest.getBaseType());
            characteristicRelationship.setSchemaLocation(characteristicRelationshipRequest.getSchemaLocation());
            characteristicRelationship.setType(characteristicRelationshipRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveCharacterRelationship(characteristicRelationship);

        }
    }

    @Override
    public void saveFeatureRequest(List<FeatureRequest> featureRequestList, String serviceRefOrValueId) {
        for(FeatureRequest featureRequest : featureRequestList){

            Feature feature = new Feature();

            BeanUtils.copyProperties(featureRequest, feature);

            feature.setServiceRefOrValueId(serviceRefOrValueId);

            //todo
            serviceOrderDaoWriteRepository.saveFeature(feature);

            if(featureRequest.getFeatureCharacteristic() != null && featureRequest.getFeatureCharacteristic().size() > 0){
                List<CharacteristicRequest> featureCharacteristicRequestList = featureRequest.getFeatureCharacteristic();

                saveCharacteristicRequest(featureCharacteristicRequestList, feature.getId(), null, null);

            }
            if(featureRequest.getConstraint() != null && featureRequest.getConstraint().size() > 0){
                List<ConstraintRequest> constraintRequestList = featureRequest.getConstraint();

                saveConstraintRequest(constraintRequestList, feature.getId());

            }
            if(featureRequest.getFeatureRelationship() != null && featureRequest.getFeatureRelationship().size() > 0){
                List<FeatureRelationshipRequest> featureRelationshipRequestList = featureRequest.getFeatureRelationship();

                saveFeatureRelationshipRequest(featureRelationshipRequestList, feature.getId());
            }

        }
    }

    @Override
    public void saveConstraintRequest(List<ConstraintRequest> constraintRequestList, String featureId){
        for(ConstraintRequest constraintRequest : constraintRequestList){
            Constraint constraint = new Constraint();

            BeanUtils.copyProperties(constraintRequest, constraint);

            constraint.setFeatureId(featureId);

            constraint.setBaseType(constraintRequest.getBaseType());
            constraint.setSchemaLocation(constraintRequest.getSchemaLocation());
            constraint.setType(constraintRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveConstraint(constraint);
        }
    }

    @Override
    public void saveFeatureRelationshipRequest(List<FeatureRelationshipRequest> featureRelationshipRequestList, String featureId){
        for(FeatureRelationshipRequest featureRelationshipRequest : featureRelationshipRequestList){
            FeatureRelationship featureRelationship = new FeatureRelationship();

            BeanUtils.copyProperties(featureRelationshipRequest, featureRelationship);

            featureRelationship.setFeatureId(featureId);

            //todo
            serviceOrderDaoWriteRepository.saveFeatureRelationship(featureRelationship);
        }
    }
    public void saveRelatedPlaceRefOrValueRequest(List<RelatedPlaceRefOrValueRequest> place, String serviceRefOrValueId) {
        for(RelatedPlaceRefOrValueRequest relatedPlaceRefOrValueRequest : place){
            RelatedPlaceRefOrValue relatedPlaceRefOrValue = new RelatedPlaceRefOrValue();

            BeanUtils.copyProperties(relatedPlaceRefOrValueRequest, relatedPlaceRefOrValue);

            relatedPlaceRefOrValue.setServiceRefOrValueId(serviceRefOrValueId);

            relatedPlaceRefOrValue.setBaseType(relatedPlaceRefOrValueRequest.getBaseType());
            relatedPlaceRefOrValue.setSchemaLocation(relatedPlaceRefOrValueRequest.getSchemaLocation());
            relatedPlaceRefOrValue.setType(relatedPlaceRefOrValueRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveRelatedPlaceRefOrValue(relatedPlaceRefOrValue);
        }
    }

    @Override
    public void saveRelatedEntityRefOrValueRequest(List<RelatedEntityRefOrValueRequest> relatedEntityRefOrValueRequestList, String serviceRefOrValueId) {
        for(RelatedEntityRefOrValueRequest relatedEntityRefOrValueRequest : relatedEntityRefOrValueRequestList){
            RelatedEntityRefOrValue relatedEntityRefOrValue = new RelatedEntityRefOrValue();

            BeanUtils.copyProperties(relatedEntityRefOrValueRequest, relatedEntityRefOrValue);

            relatedEntityRefOrValue.setServiceRefOrValueId(serviceRefOrValueId);

            relatedEntityRefOrValue.setBaseType(relatedEntityRefOrValueRequest.getBaseType());
            relatedEntityRefOrValue.setSchemaLocation(relatedEntityRefOrValueRequest.getSchemaLocation());
            relatedEntityRefOrValue.setType(relatedEntityRefOrValueRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveRelatedEntityRefOrValue(relatedEntityRefOrValue);
        }

    }

    @Override
    public void saveRelatedServiceOrderItemRequest(List<RelatedServiceOrderItemRequest> serviceOrderItemRequestList, String serviceRefOrValueId) {
        for(RelatedServiceOrderItemRequest relatedServiceOrderItemRequest : serviceOrderItemRequestList){
            RelatedServiceOrderItem relatedServiceOrderItem = new RelatedServiceOrderItem();

            BeanUtils.copyProperties(relatedServiceOrderItemRequest, relatedServiceOrderItem);

            relatedServiceOrderItem.setServiceRefOrValueId(serviceRefOrValueId);

            relatedServiceOrderItem.setBaseType(relatedServiceOrderItemRequest.getBaseType());
            relatedServiceOrderItem.setSchemaLocation(relatedServiceOrderItemRequest.getSchemaLocation());
            relatedServiceOrderItem.setType(relatedServiceOrderItemRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveRelatedServiceOrderItem(relatedServiceOrderItem);
        }
    }

    @Override
    public void saveResourceRefRequest(List<ResourceRefRequest> supportingResource, String serviceRefOrValueId) {
        for(ResourceRefRequest resourceRefRequest : supportingResource){
            ResourceRef resourceRef = new ResourceRef();

            BeanUtils.copyProperties(resourceRefRequest, resourceRef);

            resourceRef.setServiceRefOrValueId(serviceRefOrValueId);

            resourceRef.setBaseType(resourceRefRequest.getBaseType());
            resourceRef.setSchemaLocation(resourceRefRequest.getSchemaLocation());
            resourceRef.setType(resourceRefRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveResourceRef(resourceRef);

        }
    }

    @Override
    public void saveServiceSpecificationRefRequest(List<ServiceSpecificationRefRequest> serviceSpecificationRefRequestList, String serviceRefOrValueId) {
        for(ServiceSpecificationRefRequest serviceSpecificationRefRequest : serviceSpecificationRefRequestList){
            ServiceSpecificationRef serviceSpecificationRef = new ServiceSpecificationRef();

            BeanUtils.copyProperties(serviceSpecificationRefRequest, serviceSpecificationRef);

            serviceSpecificationRef.setServiceRefOrId(serviceRefOrValueId);

            serviceSpecificationRef.setBaseType(serviceSpecificationRefRequest.getBaseType());
            serviceSpecificationRef.setSchemaLocation(serviceSpecificationRefRequest.getSchemaLocation());
            serviceSpecificationRef.setType(serviceSpecificationRefRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveServiceSpecificationRef(serviceSpecificationRef);
        }
    }

}
