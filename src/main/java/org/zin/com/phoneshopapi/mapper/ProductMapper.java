package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zin.com.phoneshopapi.dto.request.ProductRequest;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;
import org.zin.com.phoneshopapi.entity.Product;
import org.zin.com.phoneshopapi.entity.ProductImage;

@Mapper(componentModel = "spring", uses = { ProductImageMapper.class, VariantMapper.class })
public interface ProductMapper {

    /**
     * Convert a Product entity to a ProductResponse DTO, mapping the brand and
     * category names, and converting the list of ProductImage entities to a list of
     * image URLs.
     * 
     * @param product the Product entity to convert
     * @return a ProductResponse DTO containing the product data from the entity
     * 
     */
    @Mapping(source = "brand.name", target = "brand")
    @Mapping(source = "category.name", target = "category")
    @Mapping(target = "images", qualifiedByName = "toImageUrl")
    ProductResponse toDTO(Product product);

    // @Mapping(target = "images", qualifiedByName = "toProductImage")
    /**
     * Convert a ProductRequest DTO to a Product entity, ignoring the images field
     * since it requires special handling to convert the list of image URLs to a
     * list of
     * ProductImage entities.
     * 
     * @param dto the ProductRequest DTO containing the product data to convert
     * @return a Product entity populated with data from the DTO
     */
    @Mapping(target = "images", ignore = true)
    Product toEntity(ProductRequest dto);

}
