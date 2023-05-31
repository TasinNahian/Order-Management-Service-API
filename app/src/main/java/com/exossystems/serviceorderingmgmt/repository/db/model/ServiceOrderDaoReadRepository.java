package com.exossystems.serviceorderingmgmt.repository.db.model;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderJson;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;

import java.util.List;

public interface ServiceOrderDaoReadRepository {
    ServiceOrderJson getServiceOrderJson(String individualId);
    ServiceOrderResponse getServiceOrder(String serviceOrderId);
    Boolean getId(String table_name, String id);
    List<String> getPaginatedServiceOrder(Integer limit, Integer offset);
    Long getTotalServiceOrder();
}