package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ordersList{
        private Long ordersId;
        private String address;
        private int totalPrice;
        private LocalDateTime createdAt;
        private List<OrdersDetailResponseDto.ordersDetailList> ordersDetailList;
    }
}
