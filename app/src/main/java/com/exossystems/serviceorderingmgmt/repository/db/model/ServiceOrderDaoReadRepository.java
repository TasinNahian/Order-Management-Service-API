package com.exossystems.serviceorderingmgmt.repository.db.model;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrder;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderJson;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;

import java.util.List;

public interface ServiceOrderDaoReadRepository {
    ServiceOrderJson getServiceOrderJsonById(String individualId);
    ServiceOrderResponse getServiceOrderPaginationById(String serviceOrderId);
    Boolean getId(String table_name, String id);
    List<String> getPaginatedServiceOrderId(Integer limit, Integer offset);
    Long getTotalServiceOrder();
//    ServiceOrder getServiceOrderById(String serviceOrderId);


}