package org.zin.com.phoneshopapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zin.com.phoneshopapi.dto.request.ProductRequest;
import org.zin.com.phoneshopapi.dto.request.VariantRequest;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;
import org.zin.com.phoneshopapi.dto.response.VariantResponse;
import org.zin.com.phoneshopapi.payload.ApiResponse;
import org.zin.com.phoneshopapi.service.ProductService;
import org.zin.com.phoneshopapi.service.VariantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final VariantService variantService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @ModelAttribute ProductRequest dto) {

        ProductResponse response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product created successfully", response));
    }

    @PostMapping("/{productId}/variants")
    public ResponseEntity<ApiResponse<VariantResponse>> createVariant(
            @PathVariable Long productId,
            @RequestBody VariantRequest dto) {

        VariantResponse response = variantService.createVariant(productId, dto);

        return ResponseEntity.ok(
                ApiResponse.success("Variant created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(
            @PathVariable Long id) {

        ProductResponse response = productService.getProduct(id);

        return ResponseEntity.ok(
                ApiResponse.success("Product retrieved successfully", response));
    }

}
