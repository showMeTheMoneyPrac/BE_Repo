package com.shop.mall.service;


import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Img;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.CartRequestDto;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.repository.Product.ProductRepository;
import com.shop.mall.validator.CartValidator;
import com.shop.mall.validator.MemberValidator;
import com.shop.mall.validator.ProductValidator;
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
    private final ImgRepository imgRepository;

    private final MemberValidator memberValidator;
    private final ProductValidator productValidator;
    private final CartValidator cartValidator;

    public List<CartResponseDto.List> cartLists(String nickname) {
        List<Cart> cartLists = cartValidator.authorization(memberValidator.authorization(nickname).getId());
        List<CartResponseDto.List> cartResponseDtoList = new ArrayList<>();

        for (Cart cart : cartLists) {
            //List<Img> imgLists = imgRepository.findAllByProduct_Id(cartLists.get(i).getProduct().getId());
            String imgUrl = imgRepository.findFirstByProductId(cart.getProduct().getId()).getImgUrl();

            //System.out.println("img"+imgUrl);
            CartResponseDto.List cartResponseDto = CartResponseDto.List.builder()
                    .cartId(cart.getId())
                    .category(cart.getProduct().getCategory())
                    .bill(cart.getBill())
                    .ea(cart.getEa())
                    .price(cart.getProduct().getPrice())
                    .firstImg(imgUrl)
                    .reviewCnt(cart.getProduct().getReviewCnt())
                    .title(cart.getProduct().getTitle())
                    .optionContent(cart.getOptionContent())
                    .productId(cart.getProduct().getId())
                    .build();
            cartResponseDtoList.add(cartResponseDto);
        }

        return cartResponseDtoList;
    }

    public String cartAdd(String nickname, Long productId, CartRequestDto.Add dto) {
        Product product = productValidator.authorization(productId);

        Cart cart = Cart.builder()
                .member(memberValidator.authorization(nickname))
                .bill(dto.getEa() * dto.getPrice())
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
        for (String s : target) {
            cartRepository.deleteById(Long.valueOf(s));
        }
    }
}
