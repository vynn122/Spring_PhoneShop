package org.zin.com.phoneshopapi.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;

    private String description;
    private Long brandId;

    private Long categoryId;

    private List<String> colors;

    private List<String> storages;

    private List<MultipartFile> images;

}
