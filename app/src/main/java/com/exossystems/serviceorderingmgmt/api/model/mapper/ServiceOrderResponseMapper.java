package com.exossystems.serviceorderingmgmt.api.model.mapper;

import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceOrderResponseMapper implements RowMapper<ServiceOrderResponse>{


    @Override
    public ServiceOrderResponse mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ServiceOrderResponse serviceOrderResponse = new ServiceOrderResponse();

        serviceOrderResponse.setCancellationDate(resultSet.getTimestamp("cancellation_date"));
        serviceOrderResponse.setCancellationReason(resultSet.getString("cancellation_reason"));
        serviceOrderResponse.setCategory(resultSet.getString("category"));
        serviceOrderResponse.setCompletionDate(resultSet.getTimestamp("completion_date"));
        serviceOrderResponse.setDescription(resultSet.getString("description"));
        serviceOrderResponse.setExpectedCompletionDate((resultSet.getTimestamp("expected_completion_date")));
        serviceOrderResponse.setExternalId(resultSet.getString("external_id"));
        serviceOrderResponse.setHref(resultSet.getString("href"));
        serviceOrderResponse.setId(resultSet.getString("id"));
        serviceOrderResponse.setNotificationContact(resultSet.getString("notification_contact"));
        serviceOrderResponse.setOrderDate(resultSet.getTimestamp("order_date"));
        serviceOrderResponse.setPriority(resultSet.getString("priority"));
        serviceOrderResponse.setRequestedCompletionDate(resultSet.getTimestamp("requested_completion_date"));
        serviceOrderResponse.setRequestedStartDate(resultSet.getTimestamp("requested_start_date"));
        serviceOrderResponse.setStartDate(resultSet.getTimestamp("start_date"));
        serviceOrderResponse.setState(resultSet.getString("state"));

        serviceOrderResponse.setBaseType(resultSet.getString("base_type"));
        serviceOrderResponse.setSchemaLocation(resultSet.getString("schema_location"));
        serviceOrderResponse.setType(resultSet.getString(""));
        return serviceOrderResponse;
    }
}
