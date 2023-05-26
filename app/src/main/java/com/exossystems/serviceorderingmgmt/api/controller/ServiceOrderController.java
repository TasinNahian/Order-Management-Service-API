package com.exossystems.serviceorderingmgmt.api.controller;

import com.exossystems.serviceorderingmgmt.api.model.domain.request.Item;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.ServiceOrderRequest;
import com.exossystems.serviceorderingmgmt.api.model.services.ServiceOrderService;
import com.exossystems.serviceorderingmgmt.api.model.services.impl.ServiceOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @PostMapping(value = "/serviceOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderRequest> createServiceOrder(@RequestBody ServiceOrderRequest serviceOrderRequest){
        serviceOrderService.saveServiceOrderRequest(serviceOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/serviceOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderRequest> createServiceOrder(){
        return null;
    }
}
