package org.zin.com.phoneshopapi.dto.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private String productName;

    private String color;

    private String storage;

    private Double price;

    private Integer quantity;

}
