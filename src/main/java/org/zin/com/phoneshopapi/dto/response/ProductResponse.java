package org.zin.com.phoneshopapi.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;

    private String name;

    private String description;
    private String brand;
    private String category;

    private List<String> images;

    private List<VariantResponse> variants;
}
