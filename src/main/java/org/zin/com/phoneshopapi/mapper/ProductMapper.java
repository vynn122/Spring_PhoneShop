package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;
import org.zin.com.phoneshopapi.entity.Product;

@Mapper(componentModel = "spring", uses = { ProductImageMapper.class })
public interface ProductMapper {
    // @Mapping(source = "brand.name", target = "brand")
    // @Mapping(source = "category.name", target = "category")
    // ProductResponse toDTO(Product product);

    // Product toEntity(ProductRequest dto);

    // void updateEntityFromDTO(ProductRequest dto,
    // @MappingTarget Product product);

    @Mapping(source = "brand.name", target = "brand")
    @Mapping(source = "category.name", target = "category")
    ProductResponse toDTO(Product product);

}
