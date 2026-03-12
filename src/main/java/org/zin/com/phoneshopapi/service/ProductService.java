package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.ProductRequest;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest dto);

    ProductResponse getProduct(Long id);
}
