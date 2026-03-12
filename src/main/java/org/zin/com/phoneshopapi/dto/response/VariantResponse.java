package org.zin.com.phoneshopapi.dto.response;

import org.zin.com.phoneshopapi.entity.DiscountType;

import lombok.Data;

@Data
public class VariantResponse {
    private Long id;

    private String color;

    private String storage;

    private Double price;

    private Double finalPrice;

    private Integer stock;

    private Integer sold;

    private Double discountValue;

    private DiscountType discountType;

}
