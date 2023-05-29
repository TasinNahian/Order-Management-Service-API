package com.exossystems.serviceorderingmgmt.repository.db.model;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrderJson;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;

public interface ServiceOrderDaoReadRepository {
    ServiceOrderJson getServiceOrderJson(String individualId);
    ServiceOrderResponse getServiceOrder(String serviceOrderId);
}