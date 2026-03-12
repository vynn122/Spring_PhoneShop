package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.zin.com.phoneshopapi.dto.request.BrandRequest;
import org.zin.com.phoneshopapi.dto.response.BrandResponse;
import org.zin.com.phoneshopapi.entity.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    //
    // Brand toEntity(BrandRequest dto);
    // BrandResponse toDto(Brand brand);

    // Create entity from request DTO
    Brand fromRequest(BrandRequest dto);

    // Convert entity to response DTO
    BrandResponse toResponse(Brand brand);

    // Update existing entity from request DTO (for PUT/PATCH)
    void updateEntityFromRequest(BrandRequest dto, @MappingTarget Brand brand);

}
