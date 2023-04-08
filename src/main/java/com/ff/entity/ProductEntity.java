package com.ff.entity;

import com.ff.entity.enum_pkg.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Long quantity;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] image;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private Status status_product;
    private Double rate;

    @OneToMany(mappedBy = "product_comment")
    List<CommentProductEntity> comments;

    @ManyToMany(mappedBy = "product_cate")
    List<CategoryEntity> categoryList;

    @OneToMany(mappedBy = "product_detail")
    List<OrderDetailsEntity> details;
}
