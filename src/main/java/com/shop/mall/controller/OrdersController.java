package com.shop.mall.controller;

import com.shop.mall.dto.OrdersRequestDto;
import com.shop.mall.dto.OrdersResponseDto;
import com.shop.mall.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersService ordersService;

    //15번 API
    @GetMapping("/orders")
    public OrdersResponseDto.ordersTotalList findAllOrders(@RequestHeader(value = "Authorization") String nickname){
        return ordersService.findAllOrders(nickname);
    }

    //16번 API
    @PostMapping("/orders")
    public String orderProductList(@RequestHeader(value = "Authorization") String nickname, @RequestBody OrdersRequestDto.orderProductList dto) {
        return ordersService.orderProductList(nickname,dto);
    }

    //17번 API
    @PostMapping("/orders/{productId}")
    public String orderProduct(@RequestHeader(value = "Authorization") String nickname, @PathVariable Long productId,@RequestBody OrdersRequestDto.orderProduct dto) {
        return ordersService.orderProduct(nickname,productId,dto);
    }



}
