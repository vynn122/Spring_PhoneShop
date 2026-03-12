package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.zin.com.phoneshopapi.entity.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    default String map(ProductImage image) {
        return image.getImageUrl();
    }
}
