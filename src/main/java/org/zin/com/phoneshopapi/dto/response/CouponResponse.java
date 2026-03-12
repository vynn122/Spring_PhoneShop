package org.zin.com.phoneshopapi.dto.response;

import org.zin.com.phoneshopapi.entity.DiscountType;

import lombok.Data;

@Data
public class CouponResponse {
    private Long id;

    private String code;

    private Double value;

    private DiscountType discountType;

    private Double minOrderAmount;

    private Double maxDiscount;

}
