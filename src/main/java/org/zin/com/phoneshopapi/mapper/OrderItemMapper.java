package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zin.com.phoneshopapi.dto.response.OrderItemResponse;
import org.zin.com.phoneshopapi.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "variant.product.name", target = "productName")
    @Mapping(source = "variant.color", target = "color")
    @Mapping(source = "variant.storage", target = "storage")
    OrderItemResponse toDTO(OrderItem item);
}
