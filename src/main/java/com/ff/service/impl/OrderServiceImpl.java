package com.ff.service.impl;

import com.ff.entity.OrderEntity;
import com.ff.entity.enum_pkg.Status;
import com.ff.repository.OrderRepository;
import com.ff.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<OrderEntity> viewAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity changeStatus(Long order_id, String status) {
        OrderEntity order = orderRepository.findById(order_id).get();
        if(order == null){
            return null;
        }
        if(status.toUpperCase().equals("COMPLETED"))
            order.setStatus_Order(Status.COMPLETED);
        if(status.toUpperCase().equals("PROGRESS"))
            order.setStatus_Order(Status.PROGRESS);
        if(status.toUpperCase().equals("CANCELLED"))
            order.setStatus_Order(Status.CANCELLED);
        orderRepository.save(order);
        return order;
    }
}
