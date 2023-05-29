package com.exossystems.serviceorderingmgmt.api.controller;

import com.exossystems.serviceorderingmgmt.api.model.domain.request.ServiceOrderRequest;
import com.exossystems.serviceorderingmgmt.api.model.domain.response.ServiceOrderResponse;
import com.exossystems.serviceorderingmgmt.api.model.services.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;
    private static final Logger LOGGER = LogManager.getLogger(ServiceOrderController.class);


    @PostMapping(value = "/serviceOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderRequest> createServiceOrder(@RequestBody ServiceOrderRequest serviceOrderRequest){
        LOGGER.info("Saving Service Order...");
        ServiceOrderResponse serviceOrderResponse = serviceOrderService.saveServiceOrderRequest(serviceOrderRequest);
        return new ResponseEntity<>(serviceOrderResponse,HttpStatus.CREATED);
    }
    @GetMapping(value = "/serviceOrder/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderResponse> getServiceOrderJsonById(@PathVariable String id){
        LOGGER.info("Call getIndividualById {}");
        return new ResponseEntity<>(serviceOrderService.getServiceOrderJsonById(id), HttpStatus.OK);
    }

}
