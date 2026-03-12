package org.zin.com.phoneshopapi.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class CartResponse {
    private Long cartId;

    private List<CartItemResponse> items;

    private Double totalPrice;
}
