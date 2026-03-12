package org.zin.com.phoneshopapi.dto.request;

import org.zin.com.phoneshopapi.entity.DiscountType;

import lombok.Data;

@Data
public class CouponRequest {

    private String code;

    private Double value;

    private DiscountType discountType;

    private Double minOrderAmount;

    private Double maxDiscount;

}
