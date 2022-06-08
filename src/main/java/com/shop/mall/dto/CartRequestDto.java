package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartRequestDto {

    @Getter
    @AllArgsConstructor
    public static class Add {
        private String option;
        private int price;
        private int ea;
    }

    @Getter
    @AllArgsConstructor
    public static class Ea {
        private Long cartId;
        private int ea;
    }
}
