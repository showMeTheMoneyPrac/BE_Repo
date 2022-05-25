package com.shop.mall.controller;

import com.shop.mall.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersService ordersService;
성
}
