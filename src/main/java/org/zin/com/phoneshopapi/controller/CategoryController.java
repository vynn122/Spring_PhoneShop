package org.zin.com.phoneshopapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zin.com.phoneshopapi.dto.request.CategoryRequest;
import org.zin.com.phoneshopapi.dto.response.CategoryResponse;
import org.zin.com.phoneshopapi.payload.*;
import org.zin.com.phoneshopapi.service.CategoryService;

import java.util.Map;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
        private final CategoryService categoryService;;

        @GetMapping
        public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAll(
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int limit,
                        @RequestParam(required = false) String sort,
                        @RequestParam(required = false) Map<String, String> params) {
                params.remove("page");
                params.remove("limit");
                params.remove("sort");
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success("Category Retrieve Successfully",
                                                categoryService.getAll(page, limit, sort, params)));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<CategoryResponse>> create(
                        @RequestBody @Valid CategoryRequest categoryRequest) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success("Category create Successfully",
                                                categoryService.save(categoryRequest)));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<CategoryResponse>> update(
                        @PathVariable Long id,
                        @RequestBody @Valid CategoryRequest categoryRequest) {

                CategoryResponse response = categoryService.update(id, categoryRequest);

                return ResponseEntity.ok(
                                ApiResponse.success("Category Updated Successfully", response));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
                categoryService.delete(id);
                return ResponseEntity.ok(
                                ApiResponse.success("Category Deleted Successfully", null));
        }

}
