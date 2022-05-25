package com.shop.mall.service;


import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Member;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final MemberValidator memberValidator;

    public List<Cart> cartLists(String nickname) {
        return cartRepository.findAllByMember_Id(memberValidator.authorization(nickname).getId());
    }
}
