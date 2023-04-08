package com.ff.service;

import com.ff.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> viewAllOrder();
    OrderEntity changeStatus(Long order_id, String status);
}
