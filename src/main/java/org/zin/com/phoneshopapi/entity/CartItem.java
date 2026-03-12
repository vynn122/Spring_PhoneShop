package org.zin.com.phoneshopapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    private ProductVariant variant;

    @ManyToOne
    private Cart cart;
}
