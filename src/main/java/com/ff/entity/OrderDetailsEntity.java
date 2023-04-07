package com.ff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderDetail")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // one line in order belong to one product
    // one product belong to many orders
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    ProductEntity product_detail;

    // one line in order belong to one order
    // one order has many orders
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    OrderEntity orders;

    private Long quantity;
}
