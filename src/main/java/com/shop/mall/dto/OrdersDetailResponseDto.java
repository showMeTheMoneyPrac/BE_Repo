package com.shop.mall.dto;

import com.shop.mall.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrdersDetailResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ordersDetailList{
        private Long orderDetailId;
        private Long productId;
        private String title;
        private String category;
        private String firstImg;
        private String optionContent;
        private int price;
        private int ea;
        private int bill;
        private Review review;
    }
}
