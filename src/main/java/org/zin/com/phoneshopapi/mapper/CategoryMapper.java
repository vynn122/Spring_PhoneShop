package org.zin.com.phoneshopapi.mapper;

import org.zin.com.phoneshopapi.dto.CategoryRequest;
import org.zin.com.phoneshopapi.dto.CategoryResponse;
import org.zin.com.phoneshopapi.entity.Category;

public interface CategoryMapper {
    CategoryResponse toEntity(Category category);
    Category toEntity(CategoryRequest dto);
}
