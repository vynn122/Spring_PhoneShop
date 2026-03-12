package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.CategoryRequest;
import org.zin.com.phoneshopapi.dto.response.CategoryResponse;
import org.zin.com.phoneshopapi.payload.PageResponse;

import java.util.Map;

public interface CategoryService {
    CategoryResponse save(CategoryRequest dto);

    CategoryResponse getById(Long id);

    PageResponse<CategoryResponse> getAll(int page, int size, String sort, Map<String, String> filters);

    CategoryResponse update(Long id, CategoryRequest dto);

    void delete(Long ids);
}
