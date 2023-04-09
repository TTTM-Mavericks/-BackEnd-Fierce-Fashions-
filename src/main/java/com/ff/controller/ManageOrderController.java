package com.ff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ff.entity.OrderEntity;
import com.ff.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manageOrder")
public class ManageOrderController {
    @Autowired
    OrderService orderService;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/viewAllOrder")
    public ResponseEntity<List<OrderEntity>> vielAllOrder() {
        List<OrderEntity> listOrder = orderService.viewAllOrder();
        return new ResponseEntity<>(listOrder, HttpStatus.OK);
    }
    @GetMapping("/changeOrderStatus")
    public ResponseEntity<OrderEntity> changeStatus(@RequestBody String json) throws JsonProcessingException {
        ChangeStatusRequest request = objectMapper.readValue(json, ChangeStatusRequest.class);
        if(request == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        OrderEntity order = orderService.changeStatus(request.getId_order(), request.getOrderStatus());
        if(order != null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
class ChangeStatusRequest{
    private Long id_order;
    private String orderStatus;
    // Completed
    // Processed
    // Cancelled

    public Long getId_order() {
        return id_order;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
