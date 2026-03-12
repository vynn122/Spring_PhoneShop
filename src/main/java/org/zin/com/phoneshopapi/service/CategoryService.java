package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.CategoryRequest;
import org.zin.com.phoneshopapi.dto.response.CategoryResponse;
import org.zin.com.phoneshopapi.payload.PageResponse;

import java.util.Map;

public interface CategoryService {
    /**
     * Create a new category.
     *
     * @param dto the category request data
     * @return the created category response
     */
    CategoryResponse save(CategoryRequest dto);

    /**
     * Get category detail by id.
     *
     * @param id the category id
     * @return category detail response
     * @throws NotFoundException if category does not exist
     */

    CategoryResponse getById(Long id);

    /**
     * Get all categories with pagination and optional filtering.
     *
     * @param page    the page number (0-based)
     * @param size    the page size
     * @param sort    the sorting criteria (e.g. "name,asc")
     * @param filters the filtering criteria as a map of field to value
     * @return paginated list of category responses matching the criteria
     */
    PageResponse<CategoryResponse> getAll(int page, int size, String sort, Map<String, String> filters);

    /**
     * Update an existing category by id.
     *
     * @param id  the category id
     * @param dto the category request data
     * @return the updated category response
     * @throws NotFoundException if category does not exist
     */
    CategoryResponse update(Long id, CategoryRequest dto);

    /**
     * Delete Category by Id
     * 
     * @param ids the categoryId
     * 
     * @throws NotFoundException if category does not exist
     */
    void delete(Long ids);
}
