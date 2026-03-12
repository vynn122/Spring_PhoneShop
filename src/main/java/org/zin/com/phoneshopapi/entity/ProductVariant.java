package org.zin.com.phoneshopapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;

    private String storage;

    private Double price;

    private Integer stock;

    private Integer sold = 0;

    private Double discountValue;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @ManyToOne
    private Product product;
}
