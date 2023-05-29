package com.exossystems.serviceorderingmgmt.api.model.mapper;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderJson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceOrderJsonMapper implements RowMapper<ServiceOrderJson>{

    @Override
    public ServiceOrderJson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ServiceOrderJson serviceOrderJson = new ServiceOrderJson();
        serviceOrderJson.setId(resultSet.getString("id"));
        serviceOrderJson.setData(resultSet.getString("data"));
        return serviceOrderJson;
    }
}

