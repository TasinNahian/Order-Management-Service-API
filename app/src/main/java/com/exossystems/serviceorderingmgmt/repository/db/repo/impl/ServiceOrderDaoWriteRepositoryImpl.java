package com.exossystems.serviceorderingmgmt.repository.db.repo.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.*;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoWriteRepository;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGobject;
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
@RequiredArgsConstructor
public class ServiceOrderDaoWriteRepositoryImpl extends JdbcDaoSupport implements ServiceOrderDaoWriteRepository {
    private final DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }
    //saving Json SQL
    private static final String INSERT_INDIVIDUAL_JSON_SQL = "INSERT INTO service_order_json( id, data ) VALUES (?, ?) ";
    private static final String INSERT_SERVICE_ORDER = "INSERT INTO public.service_order(cancellation_date,cancellation_reason,category,completion_date,description,expected_completion_date,external_id,href,id,notification_contact,order_date,priority,requested_completion_date,requested_start_date,start_date,state,base_type,schema_location,type)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_EXTERNAL_REFERENCE = "INSERT INTO public.external_reference (external_reference_type,href,id,name,service_order_id,base_type,schema_location,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SERVICE_ORDER_RELATIONSHIP = "INSERT INTO public.service_order_relationship (href,id,relationship_type,referred_type, service_order_id,base_type,schema_location,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //todo
//    private static final String INSERT_NOTE;
//    private static final String INSERT_RELATED_PARTY;
//    private static final String INSERT_SERVICE_ORDER_ERROR_MESSAGE;
//    private static final String INSERT_SERVICE_ORDER_MILESTONE;
//    private static final String INSERT_SERVICE_JEOPARDY_ALERT;
//    private static final String INSERT_SERVICE_ORDER_ITEM;
//    private static final String INSERT_SERVICE_ORDER_ITEM_RELATIONSHIP ;
//    private static final String INSERT_SERVICE_ORDER_ITEM_REF ;
//    private static final String INSERT_SERVICE_ORDER_ITEM_ERROR_MESSAGE ;
//    private static final String INSERT_APPOINTMENT_REF;
//    private static final String INSERT_SERVICE_REF_OR_VALUE;
//    private static final String INSERT_SERVICE_RELATIONSHIP;
//    private static final String INSERT_CHARACTERISTIC;
//    private static final String INSERT_CHARACTERISTIC_RELATIONSHIP;
//    private static final String INSERT_FEATURE;
//    private static final String INSERT_CONSTRAINT ;
//    private static final String INSERT_FEATURE_RELATIONSHIP ;
//    private static final String INSERT_RELATED_PLACE_REF_OR_VALUE;
//    private static final String INSERT_RELATED_ENTITY_REF_OR_VALUE;
//    private static final String INSERT_RELATED_SERVICE_ORDER_ITEM;
//    private static final String INSERT_RESOURCE_REF;
//    private static final String INSERT_SERVICE_SPECIFICATION_REF;

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
                        preparedStatement.setString(1, externalReference.getExternal_reference_type());
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
    public void saveNote(Note note) {

    }

    @Override
    public void saveRelatedParty(RelatedParty relatedParty) {

    }

    @Override
    public void saveServiceOrderErrorMessage(ServiceOrderErrorMessage serviceOrderErrorMessage) {

    }

    @Override
    public void saveServiceOrderMilestone(ServiceOrderMilestone serviceOrderMilestone) {

    }

    @Override
    public void saveServiceJeopardyAlert(ServiceOrderJeopardyAlert serviceOrderJeopardyAlert) {

    }

    @Override
    public void saveServiceOrderItem(ServiceOrderItem serviceOrderItem) {

    }

    @Override
    public void saveServiceOrderItemRelationship(ServiceOrderItemRelationship serviceOrderItemRelationship) {

    }

    @Override
    public void saveServiceOrderItemRef(ServiceOrderItemRef serviceOrderItemRef) {

    }

    @Override
    public void saveServiceOrderItemErrorMessage(ServiceOrderItemErrorMessage serviceOrderItemErrorMessage) {

    }

    @Override
    public void saveAppointmentRef(AppointmentRef appointmentRef) {

    }

    @Override
    public void saveServiceRefOrValue(ServiceRefOrValue serviceRefOrValue) {

    }

    @Override
    public void saveServiceRelationshipRequest(ServiceRelationship serviceRelationship) {

    }

    @Override
    public void saveCharacteristic(Characteristic characteristic) {

    }

    @Override
    public void saveCharacterRelationship(CharacteristicRelationship characteristicRelationship) {

    }

    @Override
    public void saveFeature(Feature feature) {

    }

    @Override
    public void saveConstraint(Constraint constraint) {

    }

    @Override
    public void saveFeatureRelationship(FeatureRelationship featureRelationship) {

    }

    @Override
    public void saveValidFor(ValidFor validFor) {

    }

    @Override
    public void saveRelatedPlaceRefOrValue(RelatedPlaceRefOrValue relatedPlaceRefOrValue) {

    }

    @Override
    public void saveRelatedEntityRefOrValue(RelatedEntityRefOrValue relatedEntityRefOrValue) {

    }

    @Override
    public void saveRelatedServiceOrderItem(RelatedServiceOrderItem relatedServiceOrderItem) {

    }

    @Override
    public void saveResourceRef(ResourceRef resourceRef) {

    }

    @Override
    public void saveServiceSpecificationRef(ServiceSpecificationRef serviceSpecificationRef) {

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

