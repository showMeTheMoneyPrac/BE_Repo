package com.shop.mall.validator;


import com.shop.mall.domain.Cart;
import com.shop.mall.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // 선언하지 않으면 사용할 수 없다!!!!!
@RequiredArgsConstructor
public class CartValidator {
    private final CartRepository cartRepository;

    public List<Cart> authorization(Long memberId){
        return cartRepository.findAllByMember_Id(memberId);
    }
}
