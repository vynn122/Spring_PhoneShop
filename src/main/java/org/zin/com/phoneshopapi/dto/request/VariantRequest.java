package org.zin.com.phoneshopapi.dto.request;

import org.zin.com.phoneshopapi.entity.DiscountType;

import lombok.Data;

@Data
public class VariantRequest {
    private Long variantId;

    private Double price;

    private Integer stock;

    private Double discountValue;

    private DiscountType discountType;

    private String color;
    private String storage;

}
