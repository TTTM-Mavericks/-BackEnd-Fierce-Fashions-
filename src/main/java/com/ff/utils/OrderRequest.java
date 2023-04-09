package com.ff.utils;

import com.ff.entity.OrderDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String username;
    private String shipAddress;
    private List<OrderDetailsEntity> listItem;
}
