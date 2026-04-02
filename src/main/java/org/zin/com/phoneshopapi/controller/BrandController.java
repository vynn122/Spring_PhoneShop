package org.zin.com.phoneshopapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zin.com.phoneshopapi.dto.request.BrandRequest;
import org.zin.com.phoneshopapi.dto.response.BrandResponse;
import org.zin.com.phoneshopapi.payload.ApiResponse;
import org.zin.com.phoneshopapi.payload.PageResponse;
import org.zin.com.phoneshopapi.service.BrandService;

import java.util.Map;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long id) {
        BrandResponse response = brandService.getBrandById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BrandResponse>> create(@RequestBody @Valid BrandRequest brandRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Brand created Successfully", brandService.save(brandRequest)));
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BrandResponse>>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Map<String, String> filters) {

        filters.remove("page");
        filters.remove("limit");
        filters.remove("sort");

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Brand retrieve successfully",
                brandService.getAll(page, limit, sort, filters)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PageResponse<BrandResponse>>> deleteBrand(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Brand successfully deleted", null));
    }

}
