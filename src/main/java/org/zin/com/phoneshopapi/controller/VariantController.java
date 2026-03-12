package org.zin.com.phoneshopapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zin.com.phoneshopapi.dto.request.VariantRequest;
import org.zin.com.phoneshopapi.dto.response.VariantResponse;
import org.zin.com.phoneshopapi.payload.ApiResponse;
import org.zin.com.phoneshopapi.service.VariantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/variant")
@RequiredArgsConstructor
public class VariantController {
    private final VariantService variantService;

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VariantResponse>> updateVariant(
            @PathVariable Long id,
            @RequestBody VariantRequest dto) {

        VariantResponse response = variantService.updateVariant(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("Variant updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVariant(@PathVariable Long id) {

        variantService.deleteVariant(id);

        return ResponseEntity.ok(
                ApiResponse.success("Variant deleted successfully", null));
    }

    @GetMapping("/{productId}/variants")
    public ResponseEntity<ApiResponse<List<VariantResponse>>> getVariants(
            @PathVariable Long productId) {

        List<VariantResponse> variants = variantService.getVariantsByProduct(productId);

        return ResponseEntity.ok(
                ApiResponse.success("Variants retrieved successfully", variants));
    }

}
