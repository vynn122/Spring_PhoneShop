package org.zin.com.phoneshopapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private Double value;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Double minOrderAmount;

    private Double maxDiscount;

    private Integer usageLimit;

    private Integer usedCount = 0;

    private Boolean active = true;

    private LocalDateTime startDate;

    private LocalDateTime expiryDate;

    private LocalDateTime createdAt = LocalDateTime.now();

}
