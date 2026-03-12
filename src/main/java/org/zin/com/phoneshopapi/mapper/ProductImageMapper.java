package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.zin.com.phoneshopapi.entity.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    // default String map(ProductImage image) {
    // return image.getImageUrl();
    // }
    @Named("toImageUrl")
    default String map(ProductImage image) {
        if (image == null)
            return null;
        return image.getImageUrl();
    }

    @Named("toProductImage")
    default ProductImage map(String imageUrl) {
        if (imageUrl == null)
            return null;
        ProductImage image = new ProductImage();
        image.setImageUrl(imageUrl);
        return image;
    }
}
