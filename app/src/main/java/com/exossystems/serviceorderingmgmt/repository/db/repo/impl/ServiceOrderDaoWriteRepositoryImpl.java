package com.exossystems.serviceorderingmgmt.repository.db.repo.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoWriteRepository;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
@Repository
public class ServiceOrderDaoWriteRepositoryImpl extends JdbcDaoSupport implements ServiceOrderDaoWriteRepository {
    private final DataSource dataSource;
    @Autowired
    public ServiceOrderDaoWriteRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }
    //saving Json SQL
    private static final String INSERT_INDIVIDUAL_JSON_SQL = "INSERT INTO service_order_json( id, data ) VALUES (?, ?) ";
    private static final String INSERT_SERVICE_ORDER = "INSERT INTO public.service_order(cancellation_date,cancellation_reason,category,completion_date,description,expected_completion_date,external_id,href,id,notification_contact,order_date,priority,requested_completion_date,requested_start_date,start_date,state,base_type,schema_location,type)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_EXTERNAL_REFERENCE = "INSERT INTO public.external_reference (external_reference_type,href,id,name,service_order_id,base_type,schema_location,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SERVICE_ORDER_RELATIONSHIP = "INSERT INTO public.service_order_relationship (href,id,relationship_type,referred_type, service_order_id,base_type,schema_location,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_NOTE = "INSERT INTO public.note (author,date,id,text,service_order_id,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_RELATED_PARTY= "INSERT INTO public.related_party (href,id,name,role,referred_type,service_order_id,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_ORDER_ERROR_MESSAGE= "INSERT INTO public.service_order_error_message (id,code,message,reason,reference_error,status,timestamp,service_order_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_ORDER_MILESTONE= "INSERT INTO public.service_order_milestone (description,id,message,milestone_date,name,status,service_order_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_ORDER_JEOPARDY_ALERT= "INSERT INTO public.service_order_jeopardy_alert (alert_date,exception,id,jeopardy_type,message,name,service_order_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String INSERT_SERVICE_ORDER_ITEM = "INSERT INTO public.service_order_item (action,id,quantity,state,service_order_id,service_order_item,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_ORDER_ITEM_RELATIONSHIP = "INSERT INTO public.service_order_item_relationship (id,relationship_type,service_order_item_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_ORDER_ITEM_REF = "INSERT INTO public.service_order_item_ref (id,item_id,service_order_href,service_order_id,referred_type,service_order_item_relationship_id,service_order_error_message_id,service_order_milestone_id,service_order_jeopardy_alert_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_ORDER_ITEM_ERROR_MESSAGE = "INSERT INTO public.service_order_item_error_message (id,code,message,reason,reference_error,status,timestamp,service_order_item_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_APPOINTMENT_REF= "INSERT INTO public.appointment_ref (description,href,id,referred_type,service_order_item_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_REF_OR_VALUE = "INSERT INTO public.service_ref_or_value (category,description,end_date,has_started,href,id,is_bundle,is_service_enabled,is_stateful,name,service_date,service_type,start_date,start_mode,state,referred_type,service_order_item_id,supporting_service,service_relationship_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_RELATIONSHIP = "INSERT INTO public.service_relationship (href,id,relationship_type,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?)";
    private static final String INSERT_CHARACTERISTIC = "INSERT INTO public.characteristic (id,name,value,value_type,feature_id,service_ref_or_value_id,service_relationship_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_CHARACTERISTIC_RELATIONSHIP = "INSERT INTO public.characteristic_relationship (href,id,relationship_type,characteristic_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?)";
    private static final String INSERT_FEATURE = "INSERT INTO public.feature (id,is_bundle,is_enabled,name,service_ref_or_value_id) VALUES (?,?,?,?,?)";
    private static final String INSERT_CONSTRAINT_REF = "INSERT INTO public.constraint_ref (href,id,name,version,referred_type,feature_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_FEATURE_RELATIONSHIP = "INSERT INTO public.feature_relationship (id,name,relationship_type,feature_id,valid_for_id) VALUES (?,?,?,?,?)";
    private static final String INSERT_RELATED_PLACE_REF_OR_VALUE = "INSERT INTO public.related_place_ref_or_value (href,id,name,role,referred_type,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_RELATED_ENTITY_REF_OR_VALUE = "INSERT INTO public.related_entity_ref_or_value (href,id,name,role,referred_type,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_RELATED_SERVICE_ORDER_ITEM = "INSERT INTO public.related_service_order_item (href,id,item_action,item_id,role,service_order_href,service_order_id,base_type,referred_type,schema_location,type,service_ref_or_value_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_RESOURCE_REF = "INSERT INTO public.resource_ref (href,id,name,referred_type,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?)";
    private static final String INSERT_SERVICE_SPECIFICATION_REF = "INSERT INTO public.service_specification_ref (href,id,name,version,referred_type,service_ref_or_value_id,base_type,schema_location,type) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_VALID_FOR = "INSERT INTO public.valid_for (id,start_date_time,end_date_time) VALUES (?,?,?)";

    //Creating ID with Sequence
    @Override
    public Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT nextval('%s')";
        final SqlRowSet sqlRowSet = Objects.requireNonNull(getJdbcTemplate()).queryForRowSet(String.format(sql, sequenceName));
        sqlRowSet.next();
        return sqlRowSet.getLong(1);
    }
    @Override
    public boolean saveServiceOrder(ServiceOrder serviceOrder) {
        System.out.println("in repo: " + serviceOrder);
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setTimestamp(1, serviceOrder.getCancellationDate());
                preparedStatement.setString(2, serviceOrder.getCancellationReason());
                preparedStatement.setString(3, serviceOrder.getCategory());
                preparedStatement.setTimestamp(4, serviceOrder.getCompletionDate());
                preparedStatement.setString(5, serviceOrder.getDescription());
                preparedStatement.setTimestamp(6, serviceOrder.getExpectedCompletionDate());
                preparedStatement.setString(7, serviceOrder.getExternalId());
                preparedStatement.setString(8, serviceOrder.getHref());
                preparedStatement.setString(9, serviceOrder.getId());
                preparedStatement.setString(10, serviceOrder.getNotificationContact());
                preparedStatement.setTimestamp(11, serviceOrder.getOrderDate());
                preparedStatement.setString(12, serviceOrder.getPriority());
                preparedStatement.setTimestamp(13, serviceOrder.getRequestedCompletionDate());
                preparedStatement.setTimestamp(14, serviceOrder.getRequestedStartDate());
                preparedStatement.setTimestamp(15, serviceOrder.getStartDate());
                preparedStatement.setString(16, serviceOrder.getState());
                preparedStatement.setString(17, serviceOrder.getBaseType());
                preparedStatement.setString(18, serviceOrder.getSchemaLocation());
                preparedStatement.setString(19, serviceOrder.getType());
                return preparedStatement.execute();
            }
        }
        ));
    }
    @Override
    public boolean saveExternalReference(ExternalReference externalReference) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_EXTERNAL_REFERENCE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, externalReference.getExternalReferenceType());
                        preparedStatement.setString(2, externalReference.getHref());
                        preparedStatement.setString(3, externalReference.getId());
                        preparedStatement.setString(4, externalReference.getName());
                        preparedStatement.setString(5, externalReference.getServiceOrderId());
                        preparedStatement.setString(6, externalReference.getBaseType());
                        preparedStatement.setString(7, externalReference.getSchemaLocation());
                        preparedStatement.setString(8, externalReference.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }
    @Override
    public boolean saveServiceOrderRelationship(ServiceOrderRelationship serviceOrderRelationship) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_RELATIONSHIP, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderRelationship.getHref());
                        preparedStatement.setString(2, serviceOrderRelationship.getId());
                        preparedStatement.setString(3, serviceOrderRelationship.getRelationshipType());
                        preparedStatement.setString(4, serviceOrderRelationship.getReferredType());
                        preparedStatement.setString(5, serviceOrderRelationship.getServiceOrderId());
                        preparedStatement.setString(6, serviceOrderRelationship.getBaseType());
                        preparedStatement.setString(7, serviceOrderRelationship.getSchemaLocation());
                        preparedStatement.setString(8, serviceOrderRelationship.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }
    @Override
    public Boolean saveNote(Note note) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_NOTE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, note.getAuthor());
                        preparedStatement.setTimestamp(2, note.getDate());
                        preparedStatement.setString(3, note.getId());
                        preparedStatement.setString(4, note.getText());
                        preparedStatement.setString(5, note.getServiceOrderId());
                        preparedStatement.setString(6, note.getServiceRefOrValueId());
                        preparedStatement.setString(7, note.getBaseType());
                        preparedStatement.setString(8, note.getSchemaLocation());
                        preparedStatement.setString(9, note.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }
    @Override
    public Boolean saveRelatedParty(RelatedParty relatedParty) {
        System.out.println(relatedParty);
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_RELATED_PARTY, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, relatedParty.getHref());
                        preparedStatement.setString(2, relatedParty.getId());
                        preparedStatement.setString(3, relatedParty.getName());
                        preparedStatement.setString(4, relatedParty.getRole());
                        preparedStatement.setString(5, relatedParty.getReferredType());
                        preparedStatement.setString(6, relatedParty.getServiceOrderId());
                        preparedStatement.setString(7, relatedParty.getServiceRefOrValueId());
                        preparedStatement.setString(8, relatedParty.getBaseType());
                        preparedStatement.setString(9, relatedParty.getSchemaLocation());
                        preparedStatement.setString(10, relatedParty.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }
    @Override
    public Boolean saveServiceOrderErrorMessage(ServiceOrderErrorMessage serviceOrderErrorMessage) {
        System.out.println(serviceOrderErrorMessage);
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_ERROR_MESSAGE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderErrorMessage.getId());
                        preparedStatement.setString(2, serviceOrderErrorMessage.getCode());
                        preparedStatement.setString(3, serviceOrderErrorMessage.getMessage());
                        preparedStatement.setString(4, serviceOrderErrorMessage.getReason());
                        preparedStatement.setString(5, serviceOrderErrorMessage.getReferenceError());
                        preparedStatement.setString(6, serviceOrderErrorMessage.getStatus());
                        preparedStatement.setTimestamp(7, serviceOrderErrorMessage.getTimestamp());
                        preparedStatement.setString(8, serviceOrderErrorMessage.getServiceOrderId());
                        preparedStatement.setString(9, serviceOrderErrorMessage.getBaseType());
                        preparedStatement.setString(10, serviceOrderErrorMessage.getSchemaLocation());
                        preparedStatement.setString(11, serviceOrderErrorMessage.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceOrderMilestone(ServiceOrderMilestone serviceOrderMilestone) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_MILESTONE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderMilestone.getDescription());
                        preparedStatement.setString(2, serviceOrderMilestone.getId());
                        preparedStatement.setString(3, serviceOrderMilestone.getMessage());
                        preparedStatement.setTimestamp(4, serviceOrderMilestone.getMilestoneDate());
                        preparedStatement.setString(5, serviceOrderMilestone.getName());
                        preparedStatement.setString(6, serviceOrderMilestone.getStatus());
                        preparedStatement.setString(7, serviceOrderMilestone.getServiceOrderId());
                        preparedStatement.setString(8, serviceOrderMilestone.getBaseType());
                        preparedStatement.setString(9, serviceOrderMilestone.getSchemaLocation());
                        preparedStatement.setString(10, serviceOrderMilestone.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceJeopardyAlert(ServiceOrderJeopardyAlert serviceOrderJeopardyAlert) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_JEOPARDY_ALERT, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setTimestamp(1, serviceOrderJeopardyAlert.getAlertDate());
                        preparedStatement.setString(2, serviceOrderJeopardyAlert.getException());
                        preparedStatement.setString(3, serviceOrderJeopardyAlert.getId());
                        preparedStatement.setString(4, serviceOrderJeopardyAlert.getJeopardyType());
                        preparedStatement.setString(5, serviceOrderJeopardyAlert.getMessage());
                        preparedStatement.setString(6, serviceOrderJeopardyAlert.getName());
                        preparedStatement.setString(7, serviceOrderJeopardyAlert.getServiceOrderId());
                        preparedStatement.setString(8, serviceOrderJeopardyAlert.getBaseType());
                        preparedStatement.setString(9, serviceOrderJeopardyAlert.getSchemaLocation());
                        preparedStatement.setString(10, serviceOrderJeopardyAlert.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceOrderItem(ServiceOrderItem serviceOrderItem) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_ITEM, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderItem.getAction());
                        preparedStatement.setString(2, serviceOrderItem.getId());
                        preparedStatement.setInt(3, serviceOrderItem.getQuantity());
                        preparedStatement.setString(4, serviceOrderItem.getState());
                        preparedStatement.setString(5, serviceOrderItem.getServiceOrderId());
                        preparedStatement.setString(6, serviceOrderItem.getOtherServiceOrderItemList());
                        preparedStatement.setString(7, serviceOrderItem.getBaseType());
                        preparedStatement.setString(8, serviceOrderItem.getSchemaLocation());
                        preparedStatement.setString(9, serviceOrderItem.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceOrderItemRelationship(ServiceOrderItemRelationship serviceOrderItemRelationship) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_ITEM_RELATIONSHIP, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderItemRelationship.getId());
                        preparedStatement.setString(2, serviceOrderItemRelationship.getRelationshipType());
                        preparedStatement.setString(3, serviceOrderItemRelationship.getServiceOrderItemId());
                        preparedStatement.setString(4, serviceOrderItemRelationship.getBaseType());
                        preparedStatement.setString(5, serviceOrderItemRelationship.getSchemaLocation());
                        preparedStatement.setString(6, serviceOrderItemRelationship.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceOrderItemRef(ServiceOrderItemRef serviceOrderItemRef) {
        System.out.println(serviceOrderItemRef);
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_ITEM_REF, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderItemRef.getId());
                        preparedStatement.setString(2, serviceOrderItemRef.getItemId());
                        preparedStatement.setString(3, serviceOrderItemRef.getServiceOrderHref());
                        preparedStatement.setString(4, serviceOrderItemRef.getServiceOrderId());
                        preparedStatement.setString(5, serviceOrderItemRef.getReferredType());
                        preparedStatement.setString(6, serviceOrderItemRef.getServiceOrderItemRelationshipId());
                        preparedStatement.setString(7, serviceOrderItemRef.getServiceOrderErrorMessageId());
                        preparedStatement.setString(8, serviceOrderItemRef.getServiceOrderMilestoneId());
                        preparedStatement.setString(9, serviceOrderItemRef.getServiceOrderJeopardyAlertId());
                        preparedStatement.setString(10, serviceOrderItemRef.getBaseType());
                        preparedStatement.setString(11, serviceOrderItemRef.getSchemaLocation());
                        preparedStatement.setString(12, serviceOrderItemRef.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceOrderItemErrorMessage(ServiceOrderItemErrorMessage serviceOrderItemErrorMessage) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER_ITEM_ERROR_MESSAGE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceOrderItemErrorMessage.getId());
                        preparedStatement.setString(2, serviceOrderItemErrorMessage.getCode());
                        preparedStatement.setString(3, serviceOrderItemErrorMessage.getMessage());
                        preparedStatement.setString(4, serviceOrderItemErrorMessage.getReason());
                        preparedStatement.setString(5, serviceOrderItemErrorMessage.getReferenceError());
                        preparedStatement.setString(6, serviceOrderItemErrorMessage.getStatus());
                        preparedStatement.setTimestamp(7, serviceOrderItemErrorMessage.getTimestamp());
                        preparedStatement.setString(8, serviceOrderItemErrorMessage.getServiceOrderItemId());
                        preparedStatement.setString(9, serviceOrderItemErrorMessage.getBaseType());
                        preparedStatement.setString(10, serviceOrderItemErrorMessage.getSchemaLocation());
                        preparedStatement.setString(11, serviceOrderItemErrorMessage.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveAppointmentRef(AppointmentRef appointmentRef) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_APPOINTMENT_REF, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, appointmentRef.getDescription());
                        preparedStatement.setString(2, appointmentRef.getHref());
                        preparedStatement.setString(3, appointmentRef.getId());
                        preparedStatement.setString(4, appointmentRef.getReferredType());
                        preparedStatement.setString(5, appointmentRef.getServiceOrderItemId());
                        preparedStatement.setString(6, appointmentRef.getBaseType());
                        preparedStatement.setString(7, appointmentRef.getSchemaLocation());
                        preparedStatement.setString(8, appointmentRef.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceRefOrValue(ServiceRefOrValue serviceRefOrValue) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_REF_OR_VALUE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceRefOrValue.getCategory());
                        preparedStatement.setString(2, serviceRefOrValue.getDescription());
                        preparedStatement.setTimestamp(3, serviceRefOrValue.getEndDate());
                        preparedStatement.setBoolean(4, serviceRefOrValue.isHasStarted());
                        preparedStatement.setString(5, serviceRefOrValue.getHref());
                        preparedStatement.setString(6, serviceRefOrValue.getId());
                        preparedStatement.setBoolean(7, serviceRefOrValue.isBundle());
                        preparedStatement.setBoolean(8, serviceRefOrValue.isServiceEnabled());
                        preparedStatement.setBoolean(9, serviceRefOrValue.isStateful());
                        preparedStatement.setString(10, serviceRefOrValue.getName());
                        preparedStatement.setTimestamp(11, serviceRefOrValue.getServiceDate());
                        preparedStatement.setString(12, serviceRefOrValue.getServiceType());
                        preparedStatement.setTimestamp(13, serviceRefOrValue.getStartDate());
                        preparedStatement.setString(14, serviceRefOrValue.getStartMode());
                        preparedStatement.setString(15, serviceRefOrValue.getState());
                        preparedStatement.setString(16, serviceRefOrValue.getReferredType());
                        preparedStatement.setString(17, serviceRefOrValue.getServiceOrderItemId());
                        preparedStatement.setString(18, serviceRefOrValue.getSupportingService());
                        preparedStatement.setString(19, serviceRefOrValue.getServiceRelationshipId());
                        preparedStatement.setString(20, serviceRefOrValue.getBaseType());
                        preparedStatement.setString(21, serviceRefOrValue.getSchemaLocation());
                        preparedStatement.setString(22, serviceRefOrValue.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceRelationshipRequest(ServiceRelationship serviceRelationship) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_RELATIONSHIP, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceRelationship.getHref());
                        preparedStatement.setString(2, serviceRelationship.getId());
                        preparedStatement.setString(3, serviceRelationship.getRelationshipType());
                        preparedStatement.setString(4, serviceRelationship.getServiceRefOrValueId());
                        preparedStatement.setString(5, serviceRelationship.getBaseType());
                        preparedStatement.setString(6, serviceRelationship.getSchemaLocation());
                        preparedStatement.setString(7, serviceRelationship.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveCharacteristic(Characteristic characteristic) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_CHARACTERISTIC, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, characteristic.getId());
                        preparedStatement.setString(2, characteristic.getName());
                        preparedStatement.setString(3, characteristic.getValue());
                        preparedStatement.setString(4, characteristic.getValue_type());
                        preparedStatement.setString(5, characteristic.getFeatureId());
                        preparedStatement.setString(6, characteristic.getServiceRefOrValueId());
                        preparedStatement.setString(7, characteristic.getServiceRelationshipId());
                        preparedStatement.setString(8, characteristic.getBaseType());
                        preparedStatement.setString(9, characteristic.getSchemaLocation());
                        preparedStatement.setString(10, characteristic.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveCharacterRelationship(CharacteristicRelationship characteristicRelationship) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_CHARACTERISTIC_RELATIONSHIP, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, characteristicRelationship.getHref());
                        preparedStatement.setString(2, characteristicRelationship.getId());
                        preparedStatement.setString(3, characteristicRelationship.getRelationshipType());
                        preparedStatement.setString(4, characteristicRelationship.getCharacteristicId());
                        preparedStatement.setString(5, characteristicRelationship.getBaseType());
                        preparedStatement.setString(6, characteristicRelationship.getSchemaLocation());
                        preparedStatement.setString(7, characteristicRelationship.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveFeature(Feature feature) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_FEATURE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, feature.getId());
                        preparedStatement.setBoolean(2, feature.isBundle());
                        preparedStatement.setBoolean(3, feature.isEnabled());
                        preparedStatement.setString(4, feature.getName());
                        preparedStatement.setString(5, feature.getServiceRefOrValueId());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveConstraint(Constraint constraint) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_CONSTRAINT_REF, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, constraint.getHref());
                        preparedStatement.setString(2, constraint.getId());
                        preparedStatement.setString(3, constraint.getName());
                        preparedStatement.setString(4, constraint.getVersion());
                        preparedStatement.setString(5, constraint.getReferredType());
                        preparedStatement.setString(6, constraint.getFeatureId());
                        preparedStatement.setString(7, constraint.getBaseType());
                        preparedStatement.setString(8, constraint.getSchemaLocation());
                        preparedStatement.setString(9, constraint.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveFeatureRelationship(FeatureRelationship featureRelationship) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_FEATURE_RELATIONSHIP, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, featureRelationship.getId());
                        preparedStatement.setString(2, featureRelationship.getName());
                        preparedStatement.setString(3, featureRelationship.getRelationshipType());
                        preparedStatement.setString(4, featureRelationship.getFeatureId());
                        preparedStatement.setString(5, featureRelationship.getValidForId());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveValidFor(ValidFor validFor) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_VALID_FOR, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, validFor.getId());
                        preparedStatement.setTimestamp(2, validFor.getStartDateTime());
                        preparedStatement.setTimestamp(3, validFor.getEndDateTime());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveRelatedPlaceRefOrValue(RelatedPlaceRefOrValue relatedPlaceRefOrValue) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_RELATED_PLACE_REF_OR_VALUE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, relatedPlaceRefOrValue.getHref());
                        preparedStatement.setString(2, relatedPlaceRefOrValue.getId());
                        preparedStatement.setString(3, relatedPlaceRefOrValue.getName());
                        preparedStatement.setString(4, relatedPlaceRefOrValue.getRole());
                        preparedStatement.setString(5, relatedPlaceRefOrValue.getReferredType());
                        preparedStatement.setString(6, relatedPlaceRefOrValue.getServiceRefOrValueId());
                        preparedStatement.setString(7, relatedPlaceRefOrValue.getBaseType());
                        preparedStatement.setString(8, relatedPlaceRefOrValue.getSchemaLocation());
                        preparedStatement.setString(9, relatedPlaceRefOrValue.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveRelatedEntityRefOrValue(RelatedEntityRefOrValue relatedEntityRefOrValue) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_RELATED_ENTITY_REF_OR_VALUE, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, relatedEntityRefOrValue.getHref());
                        preparedStatement.setString(2, relatedEntityRefOrValue.getId());
                        preparedStatement.setString(3, relatedEntityRefOrValue.getName());
                        preparedStatement.setString(4, relatedEntityRefOrValue.getRole());
                        preparedStatement.setString(5, relatedEntityRefOrValue.getReferredType());
                        preparedStatement.setString(6, relatedEntityRefOrValue.getServiceRefOrValueId());
                        preparedStatement.setString(7, relatedEntityRefOrValue.getBaseType());
                        preparedStatement.setString(8, relatedEntityRefOrValue.getSchemaLocation());
                        preparedStatement.setString(9, relatedEntityRefOrValue.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveRelatedServiceOrderItem(RelatedServiceOrderItem relatedServiceOrderItem) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_RELATED_SERVICE_ORDER_ITEM, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, relatedServiceOrderItem.getHref());
                        preparedStatement.setString(2, relatedServiceOrderItem.getId());
                        preparedStatement.setString(3, relatedServiceOrderItem.getItemAction());
                        preparedStatement.setString(4, relatedServiceOrderItem.getItemId());
                        preparedStatement.setString(5, relatedServiceOrderItem.getRole());
                        preparedStatement.setString(6, relatedServiceOrderItem.getServiceOrderHref());
                        preparedStatement.setString(7, relatedServiceOrderItem.getServiceOrderId());
                        preparedStatement.setString(8, relatedServiceOrderItem.getReferredType());
                        preparedStatement.setString(9, relatedServiceOrderItem.getServiceRefOrValueId());
                        preparedStatement.setString(10, relatedServiceOrderItem.getBaseType());
                        preparedStatement.setString(11, relatedServiceOrderItem.getSchemaLocation());
                        preparedStatement.setString(12, relatedServiceOrderItem.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveResourceRef(ResourceRef resourceRef) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_RESOURCE_REF, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, resourceRef.getHref());
                        preparedStatement.setString(2, resourceRef.getId());
                        preparedStatement.setString(3, resourceRef.getName());
                        preparedStatement.setString(4, resourceRef.getReferredType());
                        preparedStatement.setString(5, resourceRef.getServiceRefOrValueId());

                        preparedStatement.setString(6, resourceRef.getBaseType());
                        preparedStatement.setString(7, resourceRef.getSchemaLocation());
                        preparedStatement.setString(8, resourceRef.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceSpecificationRef(ServiceSpecificationRef serviceSpecificationRef) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_SPECIFICATION_REF, new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, serviceSpecificationRef.getHref());
                        preparedStatement.setString(2, serviceSpecificationRef.getId());
                        preparedStatement.setString(3, serviceSpecificationRef.getName());
                        preparedStatement.setString(4, serviceSpecificationRef.getVersion());
                        preparedStatement.setString(5, serviceSpecificationRef.getReferredType());
                        preparedStatement.setString(6, serviceSpecificationRef.getServiceRefOrValueId());
                        preparedStatement.setString(7, serviceSpecificationRef.getBaseType());
                        preparedStatement.setString(8, serviceSpecificationRef.getSchemaLocation());
                        preparedStatement.setString(9, serviceSpecificationRef.getType());
                        return preparedStatement.execute();
                    }
                }
        ));
    }

    @Override
    public Boolean saveServiceOrderJson( String serviceOrderId, String jsonData) {
        return Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_INDIVIDUAL_JSON_SQL, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setString(1, serviceOrderId);
                PGobject jsonObject = new PGobject();
                jsonObject.setType("json");
                jsonObject.setValue(jsonData);
                preparedStatement.setObject(2, jsonObject);
                return preparedStatement.execute();
            }
        });

    }
}

