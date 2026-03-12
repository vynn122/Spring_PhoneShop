package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.zin.com.phoneshopapi.dto.response.CartResponse;
import org.zin.com.phoneshopapi.entity.Cart;

@Mapper(componentModel = "spring", uses = { CartItemMapper.class })
public interface CartMapper {
    CartResponse toDTO(Cart cart);

}
