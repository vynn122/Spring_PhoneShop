package org.zin.com.phoneshopapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductVariant variant;

    private Integer quantity;

    private Double price;

    @ManyToOne
    private Order order;
}
