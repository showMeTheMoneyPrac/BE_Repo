package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CartResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class List {
        private Long cartId;

        private Long productId;

        private String title;
        private String category;
        private String firstImg;
        private String optionContent;
        private int reviewCnt;
        private int price;
        private int ea;
        private int bill;
    }
}
