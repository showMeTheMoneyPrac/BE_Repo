package com.shop.mall.controller;

import com.shop.mall.domain.Cart;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;

    @GetMapping("/carts") // 11번 api(장바구니 목록) List<CartResponseDto.List>
    public List<Cart> cartLists(@RequestHeader(value = "nickname") String nickname) {
        return cartService.cartLists(nickname);
    }
}
