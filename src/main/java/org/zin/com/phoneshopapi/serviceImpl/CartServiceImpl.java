package org.zin.com.phoneshopapi.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.dto.request.CartItemRequest;
import org.zin.com.phoneshopapi.dto.response.CartItemResponse;
import org.zin.com.phoneshopapi.dto.response.CartResponse;
import org.zin.com.phoneshopapi.entity.Cart;
import org.zin.com.phoneshopapi.entity.CartItem;
import org.zin.com.phoneshopapi.entity.ProductVariant;
import org.zin.com.phoneshopapi.entity.User;
import org.zin.com.phoneshopapi.exception.NotFoundException;
import org.zin.com.phoneshopapi.mapper.CartMapper;
import org.zin.com.phoneshopapi.repository.CartItemRepository;
import org.zin.com.phoneshopapi.repository.CartRepository;
import org.zin.com.phoneshopapi.repository.UserRepository;
import org.zin.com.phoneshopapi.repository.VariantRepository;
import org.zin.com.phoneshopapi.service.CartService;
import org.zin.com.phoneshopapi.utils.AuthUtil;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final VariantRepository variantRepository;

    private final CartMapper cartMapper;


//    private User getCurrentUser(){
//        return userRepository.findById(1L).orElseThrow(()-> new NotFoundException("Uer not found"));
//    }
    private User getCurrentUser() {

        String email = AuthUtil.getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

    }



    private Cart getUserCart(){
        User user = getCurrentUser();
        return cartRepository.findByUser(user).orElseGet(()->{
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepository.save(cart);
        });
    }

    @Override
    public CartResponse getCart() {
        Cart cart = getUserCart();
        List<CartItemResponse> items = cart.getItems()
                .stream()
                .map(cartMapper::toDto)
                .toList();

        items.forEach(item ->
                item.setTotalPrice(item.getPrice() * item.getQuantity())
        );

        double total = items.stream()
                .mapToDouble(CartItemResponse::getTotalPrice)
                .sum();

        CartResponse response = new CartResponse();
        response.setCartId(cart.getId());
        response.setItems(items);
        response.setTotalPrice(total);

        return response;
    }

    @Override
    public CartResponse addToCart(CartItemRequest request) {
        Cart cart = getUserCart();

        ProductVariant variant = variantRepository
                .findById(request.getVariantId())
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        CartItem item = cartItemRepository
                .findByCartIdAndVariantId(cart.getId(), variant.getId())
                .orElse(null);

        if (item == null) {
            item = new CartItem();
            item.setCart(cart);
            item.setVariant(variant);
            item.setQuantity(request.getQuantity());
        } else {
            item.setQuantity(item.getQuantity() + request.getQuantity());
        }

        cartItemRepository.save(item);

        return getCart();
    }

    @Override
    public CartResponse removeFromCart(Long variantId) {
        Cart cart = getUserCart();

        CartItem item = cartItemRepository
                .findByCartIdAndVariantId(cart.getId(), variantId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepository.delete(item);

        return getCart();
    }
}
