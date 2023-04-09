package com.ff.service.impl;

import com.ff.entity.OrderDetailsEntity;
import com.ff.entity.OrderEntity;
import com.ff.entity.ProductEntity;
import com.ff.entity.UserEntity;
import com.ff.entity.enum_pkg.Status;
import com.ff.repository.OrderRepository;
import com.ff.repository.ProductRepository;
import com.ff.repository.UserRepository;
import com.ff.service.OrderService;
import com.ff.utils.AddItemRequest;
import com.ff.utils.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
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

    @Override
    public OrderEntity makeOrder(OrderRequest orderDetail) {
        UserEntity user = userRepository.findUserByUsername(orderDetail.getUsername());
        if(user != null){
            String shipAddress = orderDetail.getShipAddress();
            if(shipAddress == null || shipAddress.isBlank() || shipAddress.isEmpty()){
                shipAddress = user.getAddress();
            }
            List<OrderDetailsEntity> listItem = orderDetail.getListItem();
            if(listItem != null && listItem.size() > 0){
                OrderEntity order = new OrderEntity(
                        user,
                        new Date(),
                        shipAddress,
                        Status.PROGRESS,
                        total(listItem),
                        listItem);
                orderRepository.save(order);
                return order;
            }
        }
        return null;
    }

    @Override
    public OrderEntity addNewItem(AddItemRequest newItem) {
        OrderEntity order = orderRepository.findById(newItem.getOrderId()).get();
        if(order != null){
            ProductEntity product = productRepository.findById(newItem.getProductId()).get();
            if(product != null) {
                List<OrderDetailsEntity> listItem = order.getOrderDetailsList();
                listItem.add(new OrderDetailsEntity(product, newItem.getQuantity()));
                order.setOrderDetailsList(listItem);
                order.setTotalPrice(total(listItem));
                orderRepository.save(order);
                return order;
            }
        }
        return null;
    }

    private Double total(List<OrderDetailsEntity> listItem){
        Double totolPrice = 0.0;
        for (OrderDetailsEntity item:listItem) {
            totolPrice += item.getProduct_detail().getPrice() * item.getQuantity();
        }
        return totolPrice;
    }
}
