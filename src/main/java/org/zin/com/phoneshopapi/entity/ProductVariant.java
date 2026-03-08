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
    private int stock;

    private Double discountValue;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Double getFinalPrice(){
        if(discountValue == null || discountType == null) return price;

        if(discountType == DiscountType.PERCENTAGE){
            return price - (price * discountValue / 100);
        }
        return price - discountValue;
    }
}
