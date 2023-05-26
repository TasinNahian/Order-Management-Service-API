package com.exossystems.serviceorderingmgmt.repository.db.model;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrder;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.ServiceOrderRequest;

public interface ServiceOrderDaoWriteRepository {
    boolean saveServiceOrder(ServiceOrder serviceOrder);
    Long getNextSequenceValue(String sequenceName);

}
