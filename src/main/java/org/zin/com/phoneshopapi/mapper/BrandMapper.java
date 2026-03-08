package org.zin.com.phoneshopapi.mapper;


import org.mapstruct.Mapper;
import org.zin.com.phoneshopapi.dto.BrandRequest;
import org.zin.com.phoneshopapi.dto.BrandResponse;
import org.zin.com.phoneshopapi.entity.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity(BrandRequest dto);
    BrandResponse toDto(Brand brand);




}
