package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.CartItemRequest;
import org.zin.com.phoneshopapi.dto.response.CartResponse;

public interface CartService {
    CartResponse getCart();

    CartResponse addToCart(CartItemRequest request);

    CartResponse removeFromCart(Long variantId);
}
