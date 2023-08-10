package com.exossystems.serviceorderingmgmt.api.model.services.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.PaginatedServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.services.ServiceOrderService;
import com.exossystems.serviceorderingmgmt.api.util.Defs;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoReadRepository;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoWriteRepository;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional
public class ServiceOrderServiceImpl implements ServiceOrderService {
    private final ServiceOrderDaoWriteRepository serviceOrderDaoWriteRepository;
    private final ServiceOrderDaoReadRepository serviceOrderDaoReadRepository;

    @Autowired
    public ServiceOrderServiceImpl(ServiceOrderDaoWriteRepository serviceOrderDaoWriteRepository, ServiceOrderDaoReadRepository serviceOrderDaoReadRepository) {
        this.serviceOrderDaoWriteRepository = serviceOrderDaoWriteRepository;
        this.serviceOrderDaoReadRepository = serviceOrderDaoReadRepository;
    }

    private static final Logger LOGGER = LogManager.getLogger(ServiceOrderServiceImpl.class);
    @Override
    public ServiceOrderResponse saveServiceOrderRequest(ServiceOrderRequest serviceOrderRequest) {
        ServiceOrder serviceOrder = new ServiceOrder();

        if (serviceOrderRequest.getId() == null) {
            serviceOrderRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ID_SEQUENCE).toString());
        }
        if (serviceOrderRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order", serviceOrderRequest.getId())) {
            //todo: will create customised exception
            throw new RuntimeException("serviceOrderRequest ID is present in db");
        }

        BeanUtils.copyProperties(serviceOrderRequest, serviceOrder);

        LOGGER.info("Saving Service Order ...");
        //save ServiceOrder in db
        serviceOrderDaoWriteRepository.saveServiceOrder(serviceOrder);

        //externalReferenceRequest
        if (serviceOrderRequest.getExternalReference() != null && serviceOrderRequest.getExternalReference().size() > 0){
            List<ExternalReferenceRequest> externalReferenceRequestListList = serviceOrderRequest.getExternalReference();
            LOGGER.info("Saving externalReferenceRequest ...");
            saveExternalReferenceRequest(externalReferenceRequestListList, serviceOrder.getId());
        }
        //orderRelationship
        if (serviceOrderRequest.getOrderRelationship() != null && serviceOrderRequest.getOrderRelationship().size() > 0) {
            List<ServiceOrderRelationshipRequest> serviceOrderRelationshipRequestList = serviceOrderRequest.getOrderRelationship();
            LOGGER.info("Saving ServiceOrderRelationshipRequest ...");
            saveServiceOrderRelationshipRequest(serviceOrderRelationshipRequestList, serviceOrder.getId());
            }
        //serviceOrderItem
        if (serviceOrderRequest.getServiceOrderItem() != null && serviceOrderRequest.getServiceOrderItem().size() > 0) {
            List<ServiceOrderItemRequest> serviceOrderItemRequestList = serviceOrderRequest.getServiceOrderItem();
            LOGGER.info("Saving ServiceOrderItemRequest ...");
            saveServiceOrderItemRequest(serviceOrderItemRequestList, serviceOrder.getId());
        }
        //note
        if (serviceOrderRequest.getNote() != null && serviceOrderRequest.getNote().size() > 0) {
            List<NoteRequest> noteRequestList = serviceOrderRequest.getNote();
            LOGGER.info("Saving ServiceOrderNoteRequest ...");
            saveNoteRequest(noteRequestList, serviceOrder.getId(),null);
        }
        //errorMessage
        if (serviceOrderRequest.getErrorMessage() != null && serviceOrderRequest.getErrorMessage().size() > 0) {
            List<ServiceOrderErrorMessageRequest> serviceOrderErrorMessageRequestList = serviceOrderRequest.getErrorMessage();
            LOGGER.info("Saving ServiceOrderErrorMessageRequest ...");
            saveServiceOrderErrorMessageRequest(serviceOrderErrorMessageRequestList, serviceOrder.getId());
        }
        //milestone
        if (serviceOrderRequest.getMilestone() != null && serviceOrderRequest.getMilestone().size() > 0) {
            List<ServiceOrderMilestoneRequest> serviceOrderMilestoneRequestList = serviceOrderRequest.getMilestone();
            LOGGER.info("Saving ServiceOrderMilestoneRequest ...");
            saveServiceOrderMilestoneRequest(serviceOrderMilestoneRequestList, serviceOrder.getId());
        }
        //jeopardyAlert
        if (serviceOrderRequest.getJeopardyAlert() != null && serviceOrderRequest.getJeopardyAlert().size() > 0) {
            List<ServiceOrderJeopardyAlertRequest> serviceOrderJeopardyAlertRequestList = serviceOrderRequest.getJeopardyAlert();
            LOGGER.info("Saving JeopardyAlertRequest ...");
            saveJeopardyAlertRequest(serviceOrderJeopardyAlertRequestList, serviceOrder.getId());
        }
        //relatedParty
        if (serviceOrderRequest.getRelatedParty() != null && serviceOrderRequest.getRelatedParty().size() > 0) {
            List<RelatedPartyRequest> relatedPartyRequestList = serviceOrderRequest.getRelatedParty();
            LOGGER.info("Saving RelatedPartyRequest ...");
            saveRelatedPartyRequest(relatedPartyRequestList, serviceOrder.getId(), null);
        }

        ServiceOrderResponse serviceOrderResponse = new ServiceOrderResponse();
        BeanUtils.copyProperties(serviceOrderRequest, serviceOrderResponse);
        serviceOrderResponse.setId(serviceOrder.getId());
        String jsonString = new Gson().toJson(serviceOrderRequest);
        serviceOrderDaoWriteRepository.saveServiceOrderJson(serviceOrder.getId(), jsonString);

        return serviceOrderResponse;

    }

    @Override
    public void saveExternalReferenceRequest(List<ExternalReferenceRequest> externalReferenceRequestList, String serviceOrderId) {
        for(ExternalReferenceRequest externalReferenceRequest: externalReferenceRequestList) {
            ExternalReference externalReference = new ExternalReference();
            if (externalReferenceRequest.getId() == null) {
                externalReferenceRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.EXTERNAL_REFERENCE_ID_SEQUENCE).toString());
            }
            if (externalReferenceRequest.getId() != null && serviceOrderDaoReadRepository.getId("external_reference", externalReferenceRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("externalReferenceRequest ID is present in db");
            }

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

            if (serviceOrderRelationshipRequest.getId() == null) {
                serviceOrderRelationshipRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_RELATIONSHIP_ID_SEQUENCE).toString());
            }
            if (serviceOrderRelationshipRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_relationship", serviceOrderRelationshipRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderRelationshipRequest ID is present in db");
            }
            BeanUtils.copyProperties(serviceOrderRelationshipRequest, serviceOrderRelationship);

            serviceOrderRelationship.setServiceOrderId(serviceOrderId);

            serviceOrderRelationship.setBaseType(serviceOrderRelationshipRequest.getBaseType());
            serviceOrderRelationship.setSchemaLocation(serviceOrderRelationshipRequest.getSchemaLocation());
            serviceOrderRelationship.setType(serviceOrderRelationshipRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderRelationship(serviceOrderRelationship);
        }
    }

    @Override
    public void saveNoteRequest(List<NoteRequest> noteRequestList, String serviceOrderId, String serviceRefOrValueId) {
        for(NoteRequest noteRequest : noteRequestList){
            Note note = new Note();
            if (noteRequest.getId() == null) {
                noteRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.NOTE_ID_SEQUENCE).toString());
            }
            if (noteRequest.getId() != null && serviceOrderDaoReadRepository.getId("note", noteRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("noteRequest ID is present in db");
            }
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
            if (relatedPartyRequest.getId() == null) {
                relatedPartyRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.RELATED_PARTY_ID_SEQUENCE).toString());
            }
            if (relatedPartyRequest.getId() != null && serviceOrderDaoReadRepository.getId("related_party", relatedPartyRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("relatedPartyRequest ID is present in db");
            }
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
            String serviceOrderErrorMessageId = serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ERROR_MESSAGE_ID_SEQUENCE).toString();
            if (serviceOrderErrorMessageRequest.getId() == null) {
                serviceOrderErrorMessageRequest.setId(serviceOrderErrorMessageId);
            }
            if (serviceOrderErrorMessageRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_error_message", serviceOrderErrorMessageRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderErrorMessageRequest ID is present in db");
            }
            BeanUtils.copyProperties(serviceOrderErrorMessageRequest, serviceOrderErrorMessage);

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
            if (serviceOrderMilestoneRequest.getId() == null) {
                serviceOrderMilestoneRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_MILESTONE_ID_SEQUENCE).toString());
            }
            if (serviceOrderMilestoneRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_milestone", serviceOrderMilestoneRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderMilestoneRequest ID is present in db");
            }
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
            if (serviceOrderJeopardyAlertRequest.getId() == null) {
                serviceOrderJeopardyAlertRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_JEOPARDY_ID_SEQUENCE).toString());
            }
            if (serviceOrderJeopardyAlertRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_jeopardy_alert", serviceOrderJeopardyAlertRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderJeopardyAlertRequest ID is present in db");
            }
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

            if (serviceOrderItemRequest.getId() == null) {
                serviceOrderItemRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ITEM_ID_SEQUENCE).toString());
            }
            if (serviceOrderItemRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_item", serviceOrderItemRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderItemRequest ID is present in db");
            }
            BeanUtils.copyProperties(serviceOrderItemRequest, serviceOrderItem);

            serviceOrderItem.setServiceOrderId(serviceOrderId);

            serviceOrderItem.setBaseType(serviceOrderItemRequest.getBaseType());
            serviceOrderItem.setSchemaLocation(serviceOrderItemRequest.getSchemaLocation());
            serviceOrderItem.setType(serviceOrderItemRequest.getType());
            //to do
            serviceOrderDaoWriteRepository.saveServiceOrderItem(serviceOrderItem); //to do

            if(serviceOrderItemRequest.getServiceOrderItemRelationship() != null && serviceOrderItemRequest.getServiceOrderItemRelationship().size() >0){

                List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationshipRequestList = serviceOrderItemRequest.getServiceOrderItemRelationship();

                saveServiceOrderItemRelationshipRequest(serviceOrderItemRelationshipRequestList, serviceOrderItem.getId());

            }
            if(serviceOrderItemRequest.getErrorMessage() != null && serviceOrderItemRequest.getErrorMessage().size() > 0){

                List<ServiceOrderItemErrorMessageRequest> serviceOrderItemErrorMessageRequestList = serviceOrderItemRequest.getErrorMessage();

                saveServiceOrderItemErrorMessageRequest(serviceOrderItemErrorMessageRequestList, serviceOrderItem.getId());

            }
            if(serviceOrderItemRequest.getAppointment() !=null){

                AppointmentRefRequest appointmentRefRequest = serviceOrderItemRequest.getAppointment();

                saveAppointmentRefRequest(appointmentRefRequest, serviceOrderItem.getId());

            }
            if(serviceOrderItemRequest.getService() !=null){

                ServiceRefOrValueRequest serviceRefOrValueRequest = serviceOrderItemRequest.getService();

                saveServiceRefOrValueRequest(serviceRefOrValueRequest, serviceOrderItem.getId(), null);

            }
        }
    }

    @Override
    public void saveServiceOrderItemRelationshipRequest(List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationshipRequestList, String serviceOrderItemId) {
        for(ServiceOrderItemRelationshipRequest serviceOrderItemRelationshipRequest : serviceOrderItemRelationshipRequestList){

            ServiceOrderItemRelationship serviceOrderItemRelationship = new ServiceOrderItemRelationship();

            if (serviceOrderItemRelationshipRequest.getId() == null) {
                serviceOrderItemRelationshipRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ITEM_RELATIONSHIP_ID_SEQUENCE).toString());
            }
            if (serviceOrderItemRelationshipRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_item_relationship", serviceOrderItemRelationshipRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderItemRelationshipRequest ID is present in db");
            }
            BeanUtils.copyProperties(serviceOrderItemRelationshipRequest, serviceOrderItemRelationship);
            //to do
            serviceOrderItemRelationship.setServiceOrderItemId(serviceOrderItemId);

            serviceOrderItemRelationship.setBaseType(serviceOrderItemRelationshipRequest.getBaseType());
            serviceOrderItemRelationship.setSchemaLocation(serviceOrderItemRelationshipRequest.getSchemaLocation());
            serviceOrderItemRelationship.setType(serviceOrderItemRelationshipRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderItemRelationship(serviceOrderItemRelationship);

            if(serviceOrderItemRelationshipRequest.getOrderItem() != null && serviceOrderItemRelationshipRequest.getOrderItem().size() > 0){

                List<ServiceOrderItemRefRequest> serviceOrderItemRefRequestList = serviceOrderItemRelationshipRequest.getOrderItem();

                saveServiceOrderItemRefRequest(serviceOrderItemRefRequestList,serviceOrderItemRelationship.getId(), null, null,null);

            }
        }
    }
    @Override
    public void saveServiceOrderItemRefRequest(List<ServiceOrderItemRefRequest> serviceOrderItemRefRequestList,String serviceOrderItemRelationshipId,String serviceOrderErrorMessageId,String serviceOrderMilestoneId,String serviceOrderJeopardyAlertId){
        for(ServiceOrderItemRefRequest serviceOrderItemRefRequest : serviceOrderItemRefRequestList){

            ServiceOrderItemRef serviceOrderItemRef = new ServiceOrderItemRef();
            if (serviceOrderItemRefRequest.getId() == null) {
                serviceOrderItemRefRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ITEM_REF_ID_SEQUENCE).toString());
            }
            if (serviceOrderItemRefRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_item_ref", serviceOrderItemRefRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderItemRefRequest ID is present in db");
            }
            BeanUtils.copyProperties(serviceOrderItemRefRequest, serviceOrderItemRef);

            serviceOrderItemRef.setServiceOrderItemRelationshipId(serviceOrderItemRelationshipId);
            serviceOrderItemRef.setServiceOrderErrorMessageId(serviceOrderErrorMessageId);
            serviceOrderItemRef.setServiceOrderMilestoneId(serviceOrderMilestoneId);
            serviceOrderItemRef.setServiceOrderJeopardyAlertId(serviceOrderJeopardyAlertId);

            serviceOrderItemRef.setBaseType(serviceOrderItemRefRequest.getBaseType());
            serviceOrderItemRef.setSchemaLocation(serviceOrderItemRefRequest.getSchemaLocation());
            serviceOrderItemRef.setType(serviceOrderItemRefRequest.getType());
            //todo
            serviceOrderDaoWriteRepository.saveServiceOrderItemRef(serviceOrderItemRef);
        }
    }


    @Override
    public void saveServiceOrderItemErrorMessageRequest(List<ServiceOrderItemErrorMessageRequest> serviceOrderItemErrorMessageRequestList, String serviceOrderItemId) {

        for(ServiceOrderItemErrorMessageRequest serviceOrderItemErrorMessageRequest : serviceOrderItemErrorMessageRequestList){

            ServiceOrderItemErrorMessage serviceOrderItemErrorMessage = new ServiceOrderItemErrorMessage();

            if (serviceOrderItemErrorMessageRequest.getId() == null) {
                serviceOrderItemErrorMessageRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_ORDER_ITEM_ERROR_MESSAGE_ID_SEQUENCE).toString());
            }
            if (serviceOrderItemErrorMessageRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_item_error_message", serviceOrderItemErrorMessageRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceOrderItemErrorMessageRequest ID is present in db");
            }
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

        if (appointmentRefRequest.getId() == null) {
            appointmentRefRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.APPOINTMENT_REF_SEQUENCE).toString());
        }
        if (appointmentRefRequest.getId() != null && serviceOrderDaoReadRepository.getId("appointment_ref", appointmentRefRequest.getId())) {
            //todo: will create customised exception
            throw new RuntimeException("appointmentRefRequest ID is present in db");
        }

        BeanUtils.copyProperties(appointmentRefRequest, appointmentRef);

        appointmentRef.setServiceOrderItemId(serviceOrderItemId);

        appointmentRef.setBaseType(appointmentRefRequest.getBaseType());
        appointmentRef.setSchemaLocation(appointmentRefRequest.getSchemaLocation());
        appointmentRef.setType(appointmentRefRequest.getType());

        serviceOrderDaoWriteRepository.saveAppointmentRef(appointmentRef);
    }


    @Override
    public void saveServiceRefOrValueRequest(ServiceRefOrValueRequest serviceRefOrValueRequest, String serviceOrderItemId, String serviceRelationshipId) {
        ServiceRefOrValue serviceRefOrValue = new ServiceRefOrValue();

//        String serviceRefOrValueId = serviceRefOrValueRequest.getId();

        if(serviceRefOrValueRequest.getId() == null ){
            serviceRefOrValueRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_REF_OR_VALUE_ID_SEQUENCE).toString());
        }
        if(serviceRefOrValueRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_ref_or_value", serviceRefOrValueRequest.getId())){
            //todo: will create customised exception
            throw new RuntimeException("serviceRefOrValueRequestId is present in db");
        }

        BeanUtils.copyProperties(serviceRefOrValueRequest, serviceRefOrValue);

        serviceRefOrValue.setServiceOrderItemId(serviceOrderItemId);
        serviceRefOrValue.setServiceRelationshipId(serviceRelationshipId);
        serviceRefOrValue.setBaseType(serviceRefOrValueRequest.getBaseType());
        serviceRefOrValue.setSchemaLocation(serviceRefOrValueRequest.getSchemaLocation());
        serviceRefOrValue.setType(serviceRefOrValueRequest.getType());

        //todo
        serviceOrderDaoWriteRepository.saveServiceRefOrValue(serviceRefOrValue);

        if(serviceRefOrValueRequest.getServiceRelationship() != null && serviceRefOrValueRequest.getServiceRelationship().size() > 0){

            List<ServiceRelationshipRequest> serviceRelationship = serviceRefOrValueRequest.getServiceRelationship();

            saveServiceRelationshipRequest(serviceRelationship, serviceRefOrValue.getId());

        }
        if(serviceRefOrValueRequest.getServiceCharacteristics() != null && serviceRefOrValueRequest.getServiceCharacteristics().size() > 0){

            List<CharacteristicRequest> serviceCharacteristics = serviceRefOrValueRequest.getServiceCharacteristics();

            saveCharacteristicRequest(serviceCharacteristics,null, serviceRefOrValue.getId(), null);

        }
        if(serviceRefOrValueRequest.getRelatedParty() != null && serviceRefOrValueRequest.getRelatedParty().size() > 0){

            List<RelatedPartyRequest> relatedParty = serviceRefOrValueRequest.getRelatedParty();

            saveRelatedPartyRequest(relatedParty, null, serviceRefOrValue.getId());

        }
        if(serviceRefOrValueRequest.getFeature() != null && serviceRefOrValueRequest.getFeature().size() > 0){

            List<FeatureRequest> feature = serviceRefOrValueRequest.getFeature();

            saveFeatureRequest(feature, serviceRefOrValue.getId());

        }
        if(serviceRefOrValueRequest.getPlace() != null && serviceRefOrValueRequest.getPlace().size() > 0){

            List<RelatedPlaceRefOrValueRequest> place = serviceRefOrValueRequest.getPlace();

            saveRelatedPlaceRefOrValueRequest(place, serviceRefOrValue.getId());

        }
        if(serviceRefOrValueRequest.getRelatedEntity() != null && serviceRefOrValueRequest.getRelatedEntity().size() > 0){

            List<RelatedEntityRefOrValueRequest> relatedEntity = serviceRefOrValueRequest.getRelatedEntity();

            saveRelatedEntityRefOrValueRequest(relatedEntity, serviceRefOrValue.getId());


        }
        if(serviceRefOrValueRequest.getServiceOrderItem() != null && serviceRefOrValueRequest.getServiceOrderItem().size() > 0){

            List<RelatedServiceOrderItemRequest> serviceOrderItem = serviceRefOrValueRequest.getServiceOrderItem();

            saveRelatedServiceOrderItemRequest(serviceOrderItem, serviceRefOrValue.getId());


        }
        if(serviceRefOrValueRequest.getSupportingResource() != null && serviceRefOrValueRequest.getSupportingResource().size() > 0){

            List<ResourceRefRequest> supportingResource = serviceRefOrValueRequest.getSupportingResource();

            saveResourceRefRequest(supportingResource, serviceRefOrValue.getId());


        }
        if(serviceRefOrValueRequest.getServiceSpecification() != null){

            ServiceSpecificationRefRequest serviceSpecification = serviceRefOrValueRequest.getServiceSpecification();

            saveServiceSpecificationRefRequest(serviceSpecification, serviceRefOrValue.getId());

        }
    }
    @Override
    public void saveServiceRelationshipRequest(List<ServiceRelationshipRequest> serviceRelationshipRequestList, String serviceRefOrValueId) {
       for(ServiceRelationshipRequest serviceRelationshipRequest : serviceRelationshipRequestList) {

           ServiceRelationship serviceRelationship = new ServiceRelationship();
           if(serviceRelationshipRequest.getId() == null ){
               serviceRelationshipRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_RELATIONSHIP_ID_SEQUENCE).toString());
           }
           if(serviceRelationshipRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_order_jeopardy_alert", serviceRelationshipRequest.getId())){
               //todo: will create customised exception
               throw new RuntimeException("serviceRelationshipRequest ID is present in db");
           }
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
           if(serviceRelationshipRequest.getService() != null) {

               saveServiceRefOrValueRequest(serviceRelationshipRequest.getService(), null, serviceRelationship.getId());

           }
       }

    }

    @Override
    public void saveCharacteristicRequest(List<CharacteristicRequest> serviceCharacteristicList,String featureId, String serviceRefOrValueId, String serviceRelationshipId) {
        for(CharacteristicRequest characteristicRequest : serviceCharacteristicList) {

            Characteristic characteristic = new Characteristic();

            if(characteristicRequest.getId() == null ){
                characteristicRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.CHARACTERISTIC_ID_SEQUENCE).toString());
            }
            if(characteristicRequest.getId() != null && serviceOrderDaoReadRepository.getId("characteristic", characteristicRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("serviceRelationshipRequest ID is present in db");
            }
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

            if(characteristicRelationshipRequest.getId() == null ){
                characteristicRelationshipRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.CHARACTERISTIC_RELATIONSHIP_ID_SEQUENCE).toString());
            }
            if(characteristicRelationshipRequest.getId() != null && serviceOrderDaoReadRepository.getId("characteristic_relationship", characteristicRelationshipRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("characteristicRelationshipRequest ID is present in db");
            }
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

            if(featureRequest.getId() == null ){
                featureRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.FEATURE_ID_SEQUENCE).toString());
            }
            if(featureRequest.getId() != null && serviceOrderDaoReadRepository.getId("feature", featureRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("featureRequest ID is present in db");
            }

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

            if(constraintRequest.getId() == null ){
                constraintRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.CONSTRAINT_REF_ID_SEQUENCE).toString());
            }
            if(constraintRequest.getId() != null && serviceOrderDaoReadRepository.getId("constraint_ref", constraintRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("featureRequest ID is present in db");
            }
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

            if(featureRelationshipRequest.getId() == null ){
                featureRelationshipRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.FEATURE_RELATIONSHIP_ID_SEQUENCE).toString());
            }
            if(featureRelationshipRequest.getId() != null && serviceOrderDaoReadRepository.getId("feature_relationship", featureRelationshipRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("featureRelationshipRequest ID is present in db");
            }

            BeanUtils.copyProperties(featureRelationshipRequest, featureRelationship);

            featureRelationship.setFeatureId(featureId);

            //todo
            serviceOrderDaoWriteRepository.saveFeatureRelationship(featureRelationship);
            if(featureRelationshipRequest.getValidFor() != null) {
                saveValidForRequest(featureRelationshipRequest.getValidFor());
            }
        }
    }
    @Override
    public void saveValidForRequest(ValidForRequest validForRequest){
        ValidFor validFor = new ValidFor();

        if(validForRequest.getId() == null ){
            validForRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.VALID_FOR_ID_SEQUENCE).toString());
        }
        if(validForRequest.getId() != null && serviceOrderDaoReadRepository.getId("valid_for", validForRequest.getId())){
            //todo: will create customised exception
            throw new RuntimeException("valid_for ID is present in db");
        }
        BeanUtils.copyProperties(validForRequest, validFor);

        serviceOrderDaoWriteRepository.saveValidFor(validFor);
    }
    public void saveRelatedPlaceRefOrValueRequest(List<RelatedPlaceRefOrValueRequest> place, String serviceRefOrValueId) {
        for(RelatedPlaceRefOrValueRequest relatedPlaceRefOrValueRequest : place){
            RelatedPlaceRefOrValue relatedPlaceRefOrValue = new RelatedPlaceRefOrValue();

            if(relatedPlaceRefOrValueRequest.getId() == null ){
                relatedPlaceRefOrValueRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.RELATED_PLACE_REF_OR_VALUE_ID_SEQUENCE).toString());
            }
            if(relatedPlaceRefOrValueRequest.getId() != null && serviceOrderDaoReadRepository.getId("related_place_ref_or_value", relatedPlaceRefOrValueRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("place ID is present in db");
            }
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

            if(relatedEntityRefOrValueRequest.getId() == null ){
                relatedEntityRefOrValueRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.RELATED_ENTITY_REF_OR_VALUE_ID_SEQUENCE).toString());
            }
            if(relatedEntityRefOrValueRequest.getId() != null && serviceOrderDaoReadRepository.getId("related_entity_ref_or_value", relatedEntityRefOrValueRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("featureRelationshipRequest ID is present in db");
            }

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

            if(relatedServiceOrderItemRequest.getId() == null ){
                relatedServiceOrderItemRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.RELATED_SERVICE_ORDER_ITEM_ID_SEQUENCE).toString());
            }
            if(relatedServiceOrderItemRequest.getId() != null && serviceOrderDaoReadRepository.getId("related_service_order_item", relatedServiceOrderItemRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("relatedServiceOrderItemRequest ID is present in db");
            }

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

            if(resourceRefRequest.getId() == null ){
                resourceRefRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.RESOURCE_REF_ID_SEQUENCE).toString());
            }
            if(resourceRefRequest.getId() != null && serviceOrderDaoReadRepository.getId("resource_ref", resourceRefRequest.getId())){
                //todo: will create customised exception
                throw new RuntimeException("resourceRefRequest ID is present in db");
            }

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
    public void saveServiceSpecificationRefRequest(ServiceSpecificationRefRequest serviceSpecificationRefRequest, String serviceRefOrValueId) {
            ServiceSpecificationRef serviceSpecificationRef = new ServiceSpecificationRef();

            if (serviceSpecificationRefRequest.getId() == null) {
                serviceSpecificationRefRequest.setId(serviceOrderDaoWriteRepository.getNextSequenceValue(Defs.SERVICE_SPECIFICATION_ID_SEQUENCE).toString());
            }
            if (serviceSpecificationRefRequest.getId() != null && serviceOrderDaoReadRepository.getId("service_specification_ref", serviceSpecificationRefRequest.getId())) {
                //todo: will create customised exception
                throw new RuntimeException("serviceSpecificationRefRequest ID is present in db");
            }

            BeanUtils.copyProperties(serviceSpecificationRefRequest, serviceSpecificationRef);

            serviceSpecificationRef.setServiceRefOrValueId(serviceRefOrValueId);

            serviceSpecificationRef.setBaseType(serviceSpecificationRefRequest.getBaseType());
            serviceSpecificationRef.setSchemaLocation(serviceSpecificationRefRequest.getSchemaLocation());
            serviceSpecificationRef.setType(serviceSpecificationRefRequest.getType());

            //todo
            serviceOrderDaoWriteRepository.saveServiceSpecificationRef(serviceSpecificationRef);
    }



    @Override
    public ServiceOrderResponse getServiceOrderJsonById(String id){
        ServiceOrderJson serviceOrderJson = serviceOrderDaoReadRepository.getServiceOrderJsonById(id);
        if(Objects.isNull(serviceOrderJson)){
            //todo will create customised exception
            throw new RuntimeException("ServiceOrderJson is not found with this id!");
        }
        return new Gson().fromJson(serviceOrderJson.getData(), ServiceOrderResponse.class);
    }
    //getPaginatedServiceOrder retrieves data from json table
    @Override
    public PaginatedServiceOrderResponse getPaginatedServiceOrder(Map<String, Object> requestParams, Integer limit, Integer offset){
        PaginatedServiceOrderResponse paginatedServiceOrderResponse = new PaginatedServiceOrderResponse();
        Long totalServiceOrderCount = serviceOrderDaoReadRepository.getTotalServiceOrder();
        paginatedServiceOrderResponse.setTotalCount(totalServiceOrderCount);

        List<String> serviceOrderIdList = serviceOrderDaoReadRepository.getPaginatedServiceOrderId(limit, offset);
        List<ServiceOrderResponse> serviceOrderResponseList = new ArrayList<>();
        if(serviceOrderIdList != null && !serviceOrderIdList.isEmpty()){
            for(String serviceOrderId : serviceOrderIdList){
                ServiceOrderResponse serviceOrderResponse = this.getServiceOrderJsonById(serviceOrderId);
                serviceOrderResponseList.add(serviceOrderResponse);
            }
        }
        paginatedServiceOrderResponse.setResultCount((long) serviceOrderResponseList.size());
        paginatedServiceOrderResponse.setServiceOrderResponseList(serviceOrderResponseList);
        return paginatedServiceOrderResponse;
    }

    //todo
    @Override
    public ServiceOrderResponse updateServiceOrder(String serviceOrderId, ServiceOrderRequest serviceOrderRequest) {
        ServiceOrder serviceOrder = serviceOrderDaoReadRepository.getServiceOrderById(serviceOrderId);
        if (Objects.isNull(serviceOrder)) {
//            throw new SureNotFoundException(Defs.SERVICE_ORDER_NOT_FOUND);
        }
        //todo more validation


        Map<String, String> map = new HashMap<>();
        if (serviceOrderRequest.getCancellationDate() != null) {
            map.put("cancellation_date", "'"+serviceOrderRequest.getCancellationDate()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getCancellationReason())) {
            map.put("cancellation_reason", "'"+serviceOrderRequest.getCancellationReason()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getCategory())) {
            map.put("category", "'"+serviceOrderRequest.getCategory()+"'");
        }
        if (serviceOrderRequest.getCompletionDate() != null) {
            map.put("completion_date", "'"+serviceOrderRequest.getCompletionDate()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getDescription())) {
            map.put("description", "'"+serviceOrderRequest.getDescription()+"'");
        }
        if (serviceOrderRequest.getExpectedCompletionDate() != null) {
            map.put("expected_completion_date", "'"+serviceOrderRequest.getExpectedCompletionDate()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getExternalId())) {
            map.put("external_id", "'"+serviceOrderRequest.getExternalId()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getHref())) {
            map.put("href", "'"+serviceOrderRequest.getHref()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getId())) {
            map.put("id", "'"+serviceOrderRequest.getId()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getNotificationContact())) {
            map.put("notification_contact", "'"+serviceOrderRequest.getNotificationContact()+"'");
        }
        if (serviceOrderRequest.getOrderDate() != null) {
            map.put("order_date", "'"+serviceOrderRequest.getOrderDate()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getPriority())) {
            map.put("priority", "'"+serviceOrderRequest.getPriority()+"'");
        }
        if (serviceOrderRequest.getRequestedCompletionDate() != null) {
            map.put("requested_completion_date", "'"+serviceOrderRequest.getRequestedCompletionDate()+"'");
        }
        if (serviceOrderRequest.getRequestedStartDate() != null) {
            map.put("requested_start_date", "'"+serviceOrderRequest.getRequestedStartDate()+"'");
        }
        if (serviceOrderRequest.getStartDate() != null) {
            map.put("start_date", "'"+serviceOrderRequest.getStartDate()+"'");
        }
        if (StringUtils.hasLength(serviceOrderRequest.getState())) {
            map.put("state", "'"+serviceOrderRequest.getState()+"'");
        }
        if(!map.isEmpty()){
            serviceOrderDaoWriteRepository.updateServiceOrderById(serviceOrderId, map);
        }

        ServiceOrder serviceOrderUpdated = serviceOrderDaoReadRepository.getServiceOrderById(serviceOrderId);
        BeanUtils.copyProperties(serviceOrderUpdated, serviceOrderRequest);

        if(serviceOrderRequest.getExternalReference() != null || serviceOrderRequest.getExternalReference().isEmpty()){
            deleteExternalReferenceByServiceOrderId(serviceOrderId);
            this.saveExternalReferenceRequest(serviceOrderRequest.getExternalReference(), serviceOrderId);
        }else{
            serviceOrderRequest.setExternalReference(getExternalReference(serviceOrderId));
        }
        if(serviceOrderRequest.getOrderRelationship() != null || serviceOrderRequest.getOrderRelationship().isEmpty()){
            deleteServiceOrderRelationshipByServiceOrderId(serviceOrderId);
            saveServiceOrderRelationshipRequest(serviceOrderRequest.getOrderRelationship(), serviceOrderId);
        }else{
            serviceOrderRequest.setOrderRelationship(getServiceOrderRelationship(serviceOrderId));
        }

        if(serviceOrderRequest.getServiceOrderItem() != null || serviceOrderRequest.getServiceOrderItem().isEmpty()){
            deleteServiceOrderItemByServiceOrderId(serviceOrderId);
            saveServiceOrderItemRequest(serviceOrderRequest.getServiceOrderItem(), serviceOrderId);
        }else{
            serviceOrderRequest.setServiceOrderItem(getServiceOrderItem(serviceOrderId));
        }

        if(serviceOrderRequest.getNote() != null || serviceOrderRequest.getNote().isEmpty()){
            deleteNoteByServiceOrderId(serviceOrderId);
            saveNoteRequest(serviceOrderRequest.getNote(),serviceOrderId,null);
        }else{
            serviceOrderRequest.setNote(getNote(serviceOrderId));
        }

        if(serviceOrderRequest.getErrorMessage() != null || serviceOrderRequest.getErrorMessage().isEmpty()){
            deleteServiceOrderErrorMessageByServiceOrderId(serviceOrderId);
            saveServiceOrderErrorMessageRequest(serviceOrderRequest.getErrorMessage(),serviceOrderId);
        }else{
            serviceOrderRequest.setErrorMessage(getServiceOrderErrorMessage(serviceOrderId));
        }

        if(serviceOrderRequest.getMilestone() != null || serviceOrderRequest.getMilestone().isEmpty()){
            deleteServiceOrderMilestoneByServiceOrderId(serviceOrderId);
            saveServiceOrderMilestoneRequest(serviceOrderRequest.getMilestone(), serviceOrderId);
        }else{
            serviceOrderRequest.setMilestone(getServiceOrderMilestone(serviceOrderId));
        }

        if(serviceOrderRequest.getJeopardyAlert() != null || serviceOrderRequest.getJeopardyAlert().isEmpty()){
            deleteServiceOrderJeopardyAlertByServiceOrderId(serviceOrderId);
            saveJeopardyAlertRequest(serviceOrderRequest.getJeopardyAlert(), serviceOrderId);
        }else{
            serviceOrderRequest.setJeopardyAlert(getServiceOrderJeopardyAlert(serviceOrderId));
        }

        if(serviceOrderRequest.getRelatedParty() != null || serviceOrderRequest.getRelatedParty().isEmpty()){
            deleteRelatedPartyByServiceOrderId(serviceOrderId);
            saveRelatedPartyRequest(serviceOrderRequest.getRelatedParty(), serviceOrderId,null);
        }else{
            serviceOrderRequest.setRelatedParty(getRelatedParty(serviceOrderId));
        }

        ServiceOrderResponse  serviceOrderResponse = new ServiceOrderResponse();
        BeanUtils.copyProperties(serviceOrderRequest, serviceOrderResponse);
        String jsonString = new Gson().toJson(serviceOrderRequest);
        serviceOrderDaoWriteRepository.updateServiceOrderJsonById(serviceOrderId, jsonString);
        return serviceOrderResponse;

    }
    //todo

    private void deleteExternalReferenceByServiceOrderId(String serviceOrderId) {
        List<ExternalReference> externalReferenceList = serviceOrderDaoReadRepository.getExternalReferenceForServiceOrder(serviceOrderId);
        if (externalReferenceList != null && !externalReferenceList.isEmpty()) {
            StringBuilder
            for (ExternalReference externalReference : externalReferenceList) {
                if (externalReference.get)
            }
            serviceOrderDaoWriteRepository.deleteExternalReferenceByServiceOrderId(serviceOrderId);
        }
    }
    //todo
    private void deleteServiceOrderRelationshipByServiceOrderId(String serviceOrderId){
        ServiceOrderRelationsh
        serviceOrderDaoWriteRepository.deleteServiceOrderRelationshipByServiceOrderId(serviceOrderId);
    }
    //todo
    private void deleteServiceOrderItemByServiceOrderId(String serviceOrderId){
       List<ServiceOrderItem> serviceOrderItemList = serviceOrderDaoReadRepository.getServiceOrderItemForServiceOrder(serviceOrderId);
       if(serviceOrderItemList !=null && !serviceOrderItemList.isEmpty()){

       }
    }
    //todo
    private void deleteNoteByServiceOrderId(String serviceOrderId){

    }
    private void deleteServiceOrderErrorMessageByServiceOrderId(String serviceOrderId){

    }
    private void deleteServiceOrderMilestoneByServiceOrderId(String serviceOrderId){

    }
    private void deleteServiceOrderJeopardyAlertByServiceOrderId(String serviceOrderId){

    }
    private void deleteRelatedPartyByServiceOrderId(String serviceOrderId){

    }
    private List<ExternalReferenceRequest> getExternalReferenceByServiceOrderId(String serviceOrderId){

    }
    private List<ServiceOrderRelationshipRequest> getServiceOrderRelationshipByServiceOrderId(String serviceOrderId){

    }
    private List<ExternalReference> getServiceOrderItemByServiceOrderId(String serviceOrderId){

    }
    private List<ExternalReference> getNoteByServiceOrderId(String serviceOrderId){

    }
    private List<ExternalReference> getServiceOrderMilestoneByServiceOrderId(String serviceOrderId){

    }
    private List<ServiceOrderJeopardyAlert> getServiceOrderJeopardyAlertByServiceOrderId(String serviceOrderId){

    }
    private List<RelatedParty> getRelatedPartyByServiceOrderId(String serviceOrderId){

    }

}
