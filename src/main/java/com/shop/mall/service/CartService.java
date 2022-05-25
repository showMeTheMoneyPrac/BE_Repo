package com.shop.mall.service;


import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final MemberValidator memberValidator;

    public List<CartResponseDto.List> cartLists(String nickname) {
        List<Cart> cartLists = cartRepository.findAllByMember_Id(memberValidator.authorization(nickname).getId());

        return cartLists.stream().map(cart -> CartResponseDto.List.builder()
                .cartId(cart.getId())
                .category(cart.getProduct().getCategory())  //이부분 계속 캐싱이 되어있나?
                .bill(cart.getBill())
                .ea(cart.getEa())
                .price(cart.getProduct().getPrice())
                .firstImg(cart.getProduct().getImgList().get(0).getImgUrl())
                .reviewCnt(cart.getProduct().getReviewCnt())
                .title(cart.getProduct().getTitle())
                .optionContent(cart.getOptionContent())
                .productId(cart.getProduct().getId())
                .build()).collect(Collectors.toList());
    }
}
