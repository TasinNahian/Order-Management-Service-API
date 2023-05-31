package com.exossystems.serviceorderingmgmt.api.model.domain.response;

import java.util.List;

public class PaginatedServiceOrderResponse {
    private List<ServiceOrderResponse> serviceOrderResponseList;
    private Long totalCount;
    private Long resultCount;

    public List<ServiceOrderResponse> getServiceOrderResponseList() {
        return serviceOrderResponseList;
    }

    public void setServiceOrderResponseList(List<ServiceOrderResponse> serviceOrderResponseList) {
        this.serviceOrderResponseList = serviceOrderResponseList;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Long resultCount) {
        this.resultCount = resultCount;
    }
}
