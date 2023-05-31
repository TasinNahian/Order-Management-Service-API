package com.exossystems.serviceorderingmgmt.api.controller;

import com.exossystems.serviceorderingmgmt.api.model.domain.request.ServiceOrderRequest;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.PaginatedServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.services.ServiceOrderService;
import com.exossystems.serviceorderingmgmt.api.util.RequestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;
    @Autowired
    public ServiceOrderController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }
    private static final Logger LOGGER = LogManager.getLogger(ServiceOrderController.class);
    @PostMapping(value = "/serviceOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderRequest> createServiceOrder(@RequestBody ServiceOrderRequest serviceOrderRequest){
        LOGGER.info("Saving Service Order...");
        return new ResponseEntity<>(serviceOrderService.saveServiceOrderRequest(serviceOrderRequest),HttpStatus.CREATED);
    }
    @GetMapping(value = "/serviceOrder/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderResponse> getServiceOrderJsonById(@PathVariable String id){
        LOGGER.info("Call getServiceOrderJsonById {}");
        return new ResponseEntity<>(serviceOrderService.getServiceOrderById(id), HttpStatus.OK);
    }
    @GetMapping(value = "/serviceOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServiceOrderResponse>> getPaginatedServiceOrder(@RequestParam Map<String, Object> requestParams){
        LOGGER.info("Calling getPaginatedServiceOrder");

        int[] limitOffset = RequestUtil.validateRequestParams(requestParams);
        PaginatedServiceOrderResponse paginatedServiceOrderResponse = serviceOrderService.getPaginatedServiceOrder(requestParams, limitOffset[0],limitOffset[1]);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        List<String> totalCount = new ArrayList<>();
        totalCount.add(String.valueOf(paginatedServiceOrderResponse.getTotalCount()));
        headers.put("X-Total-Count", totalCount);

        List<String> resultCount = new ArrayList<>();
        resultCount.add(String.valueOf(paginatedServiceOrderResponse.getResultCount()));
        headers.put("X-Result-Count", resultCount);

        return new ResponseEntity<>(paginatedServiceOrderResponse.getServiceOrderResponseList(), headers, HttpStatus.OK);

    }

}
