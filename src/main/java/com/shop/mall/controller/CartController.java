package com.shop.mall.controller;

import com.shop.mall.domain.Cart;
import com.shop.mall.dto.CartRequestDto;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;

    @GetMapping("/carts") // 11번 api(장바구니 목록)
    public List<CartResponseDto.List> cartLists(@RequestHeader(value = "Authorization") String nickname) {
        return cartService.cartLists(nickname);
    }

    @PostMapping("/carts/{productId}") //12번 api(장바구니 담기)
    public Long cartAdd(@RequestHeader(value = "Authorization") String nickname, @PathVariable Long productId,
                          @RequestBody CartRequestDto.Add dto) {
        return cartService.cartAdd(nickname, productId, dto);
    }

    @DeleteMapping("/carts/{Array}") //13번 api(장바구니 삭제)
    public List<CartResponseDto.List> cardDelete(@RequestHeader(value = "Authorization") String nickname,
                                                 @PathVariable String Array) {
        cartService.cartDelete(nickname, Array);
        return cartService.cartLists(nickname);
    }

    @PatchMapping("/carts") //21번 장바구니 수량 변경
    public Cart modifyingEa(@RequestHeader(value = "Authorization") String nickname,
                            @RequestBody CartRequestDto.Ea dto) {
        return cartService.modifyingEa(nickname, dto);
    }
}
