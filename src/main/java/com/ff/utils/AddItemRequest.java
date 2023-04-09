package com.ff.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {
    private Long orderId;
    private Long productId;
    private Long quantity;
}
