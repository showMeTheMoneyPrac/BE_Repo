package com.shop.mall.repository;

import com.shop.mall.dto.CartResponseDto;

import java.util.List;

public interface CartRepositoryCustom {
    List<CartResponseDto.List> findAllCartByNickname(String nickname);
}
