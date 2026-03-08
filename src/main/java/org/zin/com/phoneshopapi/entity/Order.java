package org.zin.com.phoneshopapi.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Double totalPrice;

    private String status;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
