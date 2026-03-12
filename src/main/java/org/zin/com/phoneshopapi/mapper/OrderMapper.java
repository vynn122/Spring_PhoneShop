package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.zin.com.phoneshopapi.dto.response.OrderResponse;
import org.zin.com.phoneshopapi.entity.Order;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface OrderMapper {

    OrderResponse toDTO(Order order);
}
