package com.ff.entity;

import com.ff.entity.enum_pkg.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;

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
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    public CategoryEntity(String name) {
        this.name = name;
        this.image = null;
        this.status = Status.ACTIVE;
    }
}
