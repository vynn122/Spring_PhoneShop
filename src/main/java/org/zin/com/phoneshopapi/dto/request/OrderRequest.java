package org.zin.com.phoneshopapi.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private String couponCode;

    private List<OrderItemRequest> items;

}
