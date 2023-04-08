package com.ff.entity;

import com.ff.entity.enum_pkg.TypeOfPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeOfPayment type;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity payment_order;

    @OneToOne(mappedBy = "invoice_payment")
    private InvoiceEntity invoice;
}
