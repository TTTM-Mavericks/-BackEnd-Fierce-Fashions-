package com.ff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ff.entity.OrderEntity;
import com.ff.entity.UserEntity;
import com.ff.service.OrderService;
import com.ff.service.UserService;
import com.ff.utils.AddItemRequest;
import com.ff.utils.OrderRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/login")
    public ResponseEntity<UserEntity> checkLogin(@PathParam("key") String key, @PathParam("password") String password) {
        UserEntity user = userService.checkLogin(key, password);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/updateInfor")
    public ResponseEntity<UserEntity> updateInfor(@RequestBody String json) throws JsonProcessingException {
        UserEntity user = objectMapper.readValue(json, UserEntity.class);
        if(user != null){
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping("/makeAnOrder")
    public ResponseEntity<OrderEntity> makeOrder(@RequestBody String json) throws JsonProcessingException {
        OrderRequest orderDetail = objectMapper.readValue(json, OrderRequest.class);
        return new ResponseEntity<>(orderService.makeOrder(orderDetail), HttpStatus.OK);
    }

    @PostMapping("/addNewItem")
    public ResponseEntity<OrderEntity> addNewItem(@RequestBody String json) throws JsonProcessingException {
        AddItemRequest newItem = objectMapper.readValue(json, AddItemRequest.class);
        return new ResponseEntity<>(orderService.addNewItem(newItem), HttpStatus.OK);
    }
}
