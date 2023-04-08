package com.ff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isEnable;

    // one product belong to many category
    // one category has many product
    @ManyToMany
    @JoinTable(name = "product_cate",
            joinColumns = @JoinColumn(name = "cate_id",
                    referencedColumnName = "id",
                    table = "category"),
            inverseJoinColumns = @JoinColumn (name = "product_id",
                    referencedColumnName = "id",
                    table = "products")
    )
    List<ProductEntity> product_cate;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] image;
}
