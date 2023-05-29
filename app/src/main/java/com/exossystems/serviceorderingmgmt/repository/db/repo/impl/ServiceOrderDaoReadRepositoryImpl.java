package com.exossystems.serviceorderingmgmt.repository.db.repo.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderJson;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.mapper.ServiceOrderJsonMapper;
import com.exossystems.serviceorderingmgmt.api.model.mapper.ServiceOrderMapper;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
@Repository
@RequiredArgsConstructor
public class ServiceOrderDaoReadRepositoryImpl extends JdbcDaoSupport implements ServiceOrderDaoReadRepository {
    private final DataSource dataSource;

    private static final String SELECT_SERVICE_ORDER_JSON = "SELECT * FROM service_order_json WHERE ID='%s'";
    private static final String SELECT_SERVICE_ORDER = "SELECT * FROM service_order WHERE ID='%s'";


    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }
    @Override
    public ServiceOrderJson getServiceOrderJson(String serviceOrderId) {
        List<ServiceOrderJson> serviceOrderJsonList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_SERVICE_ORDER_JSON, serviceOrderId), new ServiceOrderJsonMapper());
        return serviceOrderJsonList.isEmpty() ? null : serviceOrderJsonList.get(0);
    }
    @Override
    public ServiceOrderResponse getServiceOrder(String serviceOrderId){
        List<ServiceOrderResponse> serviceOrderResponseList = Objects.requireNonNull(getJdbcTemplate().query(
                String.format(SELECT_SERVICE_ORDER, serviceOrderId), new ServiceOrderMapper()));
        return serviceOrderResponseList.isEmpty() ? null : serviceOrderResponseList.get(0);
    }
}
