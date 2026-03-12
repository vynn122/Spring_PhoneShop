package org.zin.com.phoneshopapi.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;

    private Double totalPrice;

    private Double discount;

    private Double finalPrice;

    private List<OrderItemResponse> items;

}
