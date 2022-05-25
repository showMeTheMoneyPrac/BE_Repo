package com.shop.mall.service;


import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.CartRequestDto;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.repository.Product.ProductRepository;
import com.shop.mall.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final MemberValidator memberValidator;
    private final ProductRepository productRepository;

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

    public String cartAdd(String nickname, Long productId, CartRequestDto.Add dto) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()->new IllegalArgumentException("없는 상품 번호 입니다")
        );

        Cart cart = Cart.builder()
                .member(memberValidator.authorization(nickname))
                .bill(dto.getEa()*dto.getPrice())
                .ea(dto.getEa())
                .option(dto.getOption())
                .product(product)
                .build();

        cartRepository.save(cart);
        return "msg : 담기 완료";
    }

    public void cartDelete(String nickname, String array) {
        memberValidator.authorization(nickname);
        String[] target = array.split(","); //문자열로 받아서 리스트로 전환
        for (String s : target) cartRepository.deleteById(Long.valueOf(s));
    }
}
