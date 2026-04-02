package org.zin.com.phoneshopapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String method;

    private String status;

    private LocalDateTime paidAt;

    @OneToOne
    private Order order;

}