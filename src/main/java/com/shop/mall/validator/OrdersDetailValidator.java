package com.shop.mall.validator;

import com.shop.mall.domain.Member;
import com.shop.mall.domain.OrdersDetail;
import com.shop.mall.repository.OrdersDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component // 선언하지 않으면 사용할 수 없다!!!!!
@RequiredArgsConstructor
public class OrdersDetailValidator {
    private final OrdersDetailRepository ordersDetailRepository;

    public OrdersDetail authorization(Long orderDetailId){
        // 반복되는 어쏘리제이션을 따로 빼서 관리해보자
        return ordersDetailRepository.findById(orderDetailId).orElseThrow(
                ()->new IllegalArgumentException("not found Id")
        );
    }
}
