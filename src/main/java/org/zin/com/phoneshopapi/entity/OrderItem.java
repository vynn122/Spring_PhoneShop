package org.zin.com.phoneshopapi.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private Double price;

    @ManyToOne
    private ProductVariant variant;

    @ManyToOne
    private Order order;
}
