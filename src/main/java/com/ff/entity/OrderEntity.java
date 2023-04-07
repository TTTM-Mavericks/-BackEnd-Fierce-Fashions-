package com.ff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // one user has many orders
    // one order belong to one user
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userList;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date shipDate;

    private String shipAddress;

    private int orderStatus;
    // 1 Completed
    // 2 Processed
    // 3 Cancelled

    private Double totalPrice;

    private String note;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetailsEntity> orderDetailsList;

    @OneToOne(mappedBy = "payment_order")
    private PaymentEntity order_payment;
}
