package com.shop.mall.controller;

import com.shop.mall.dto.OrdersResponseDto;
import com.shop.mall.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/orders")
    @ResponseBody
    public List<OrdersResponseDto.ordersList> findAllOrders(@RequestHeader(value = "Authorization") String nickname){
        return ordersService.findAllOrders(nickname);

    }

}
