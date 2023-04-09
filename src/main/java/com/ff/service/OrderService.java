package com.ff.service;

import com.ff.entity.OrderEntity;
import com.ff.utils.AddItemRequest;
import com.ff.utils.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderEntity> viewAllOrder();
    OrderEntity changeStatus(Long order_id, String status);
    OrderEntity makeOrder(OrderRequest orderDetail);
    OrderEntity addNewItem(AddItemRequest newItem);
}
