package org.zin.com.phoneshopapi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zin.com.phoneshopapi.dto.response.CartItemResponse;
import org.zin.com.phoneshopapi.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {




    @Mapping(source = "variant.id", target = "variantId")
    @Mapping(source = "variant.product.name", target = "productName")
    @Mapping(source = "variant.color", target = "color")
    @Mapping(source = "variant.storage", target = "storage")
    @Mapping(source = "variant.price", target = "price")
    CartItemResponse toDto(CartItem item);
}
