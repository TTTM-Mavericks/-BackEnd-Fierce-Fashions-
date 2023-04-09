package com.ff.entity;

import com.ff.entity.enum_pkg.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status_Order;

    private Double totalPrice;

    private String note;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetailsEntity> orderDetailsList;

    @OneToOne(mappedBy = "payment_order")
    private PaymentEntity order_payment;

    public OrderEntity(UserEntity userList, Date orderDate, String shipAddress, Status status_Order, Double totalPrice, List<OrderDetailsEntity> orderDetailsList) {
        this.userList = userList;
        this.orderDate = orderDate;
        this.shipAddress = shipAddress;
        this.status_Order = status_Order;
        this.totalPrice = totalPrice;
        this.orderDetailsList = orderDetailsList;
    }
}
