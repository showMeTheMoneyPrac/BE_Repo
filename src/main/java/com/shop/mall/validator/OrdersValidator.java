package com.shop.mall.validator;

import com.shop.mall.domain.Orders;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shop.mall.exception.ErrorCode.ORDERS_NOT_EXIST;

@Component // 선언하지 않으면 사용할 수 없다!!!!!
@RequiredArgsConstructor
public class OrdersValidator {
    private final OrdersRepository ordersRepository;
    
    public Orders findById(Long ordersId){
        return ordersRepository.findById(ordersId).orElseThrow(() -> new ErrorCodeException(ORDERS_NOT_EXIST));
    }
}
