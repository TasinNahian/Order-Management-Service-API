package com.exossystems.serviceorderingmgmt.repository.db.repo.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrder;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderJson;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.mapper.ServiceOrderJsonMapper;
import com.exossystems.serviceorderingmgmt.api.model.mapper.ServiceOrderResponseMapper;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
@Repository
public class ServiceOrderDaoReadRepositoryImpl extends JdbcDaoSupport implements ServiceOrderDaoReadRepository {
    private final DataSource dataSource;
    @Autowired
    public ServiceOrderDaoReadRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_SERVICE_ORDER_JSON = "SELECT * FROM service_order_json WHERE ID='%s'";
    private static final String SELECT_SERVICE_ORDER = "SELECT * FROM service_order WHERE ID='%s'";
    private static final String GET_ID_SQL = "SELECT COUNT(*) FROM %s WHERE id = '%s'";
    private static final String SELECT_SERVICE_ORDER_WITH_LIMIT_OFFSET = "SELECT id FROM service_order ORDER BY id LIMIT %s OFFSET %s ";
    private static final String COUNT_SERVICE_ORDER = "SELECT count(*) FROM service_order";

    private static final String SELECT_INDIVIDUAL_SQL = "SELECT * FROM service_order WHERE id='%s'";


    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Boolean getId(String table_name, String id){
        return Objects.requireNonNull(getJdbcTemplate())
                .queryForObject(
                        String.format(GET_ID_SQL, table_name, id),
                        (rs, rowNum) -> rs.getInt(1) > 0
                );
    }
    @Override
    public ServiceOrderJson getServiceOrderJsonById(String serviceOrderId){
        List<ServiceOrderJson> serviceOrderJsonList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_SERVICE_ORDER_JSON, serviceOrderId), new ServiceOrderJsonMapper());
        return serviceOrderJsonList.isEmpty() ? null : serviceOrderJsonList.get(0);
    }
    @Override
    public ServiceOrderResponse getServiceOrderPaginationById (String serviceOrderId){
        List<ServiceOrderResponse> serviceOrderResponseList = Objects.requireNonNull(getJdbcTemplate().query(
                String.format(SELECT_SERVICE_ORDER, serviceOrderId), new ServiceOrderResponseMapper()));
        return serviceOrderResponseList.isEmpty() ? null : serviceOrderResponseList.get(0);
    }
    @Override
    public List<String> getPaginatedServiceOrderId(Integer limit, Integer offset){
        return Objects.requireNonNull(getJdbcTemplate().queryForList(String.format(SELECT_SERVICE_ORDER_WITH_LIMIT_OFFSET, limit, offset), String.class));
    }

    @Override
    public Long getTotalServiceOrder(){
        return Objects.requireNonNull(getJdbcTemplate().queryForObject(COUNT_SERVICE_ORDER, Long.class));
    }

//    @Override
//    public ServiceOrder getServiceOrder(String serviceOrderId){
////        List<ServiceOrder> serviceOrderList = Objects.requireNonNull(getJdbcTemplate().query(String.format(SELECT_INDIVIDUAL_SQL, serviceOrderId), new ServiceOrderMapper()));
//        return null;
//    }
}
