package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.zin.com.phoneshopapi.dto.CategoryRequest;
import org.zin.com.phoneshopapi.dto.CategoryResponse;
import org.zin.com.phoneshopapi.entity.Category;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toDto(Category category);
    Category toEntity(CategoryRequest dto);

    void updateEntityFromRequest(CategoryRequest dto, @MappingTarget Category category);

}
