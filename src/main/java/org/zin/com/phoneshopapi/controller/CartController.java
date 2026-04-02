package org.zin.com.phoneshopapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zin.com.phoneshopapi.dto.request.CartItemRequest;
import org.zin.com.phoneshopapi.dto.response.CartResponse;
import org.zin.com.phoneshopapi.service.CartService;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }

    @PostMapping
    public CartResponse addToCart(@RequestBody CartItemRequest request) {
        return cartService.addToCart(request);
    }

    @DeleteMapping("/{variantId}")
    public CartResponse removeFromCart(@PathVariable Long variantId) {
        return cartService.removeFromCart(variantId);
    }

}