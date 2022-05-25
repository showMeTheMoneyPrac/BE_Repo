package com.shop.mall.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class OrdersRequestDto {
    @Getter
    @Builder
    public static class orderProductList{
        private List<Long> cartIdList;
        private int totalPrice;
        private String address;
    }

    @Getter
    @Builder
    public static class orderProduct{
        private int ea;
        private int price;
        private String optionContent;
        private String address;

    }
}
