package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zin.com.phoneshopapi.dto.request.ProductRequest;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;
import org.zin.com.phoneshopapi.entity.Product;
import org.zin.com.phoneshopapi.entity.ProductImage;

@Mapper(componentModel = "spring", uses = { ProductImageMapper.class, VariantMapper.class })
public interface ProductMapper {
    @Mapping(source = "brand.name", target = "brand")
    @Mapping(source = "category.name", target = "category")
    @Mapping(target = "images", qualifiedByName = "toImageUrl")
    ProductResponse toDTO(Product product);

    // @Mapping(target = "images", qualifiedByName = "toProductImage")
    @Mapping(target = "images", ignore = true)
    Product toEntity(ProductRequest dto);

}
