package com.shop.mall.service;


import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.CartRequestDto;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.repository.Cart.CartRepository;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.validator.CartValidator;
import com.shop.mall.validator.MemberValidator;
import com.shop.mall.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ImgRepository imgRepository;

    private final MemberValidator memberValidator;
    private final ProductValidator productValidator;
    private final CartValidator cartValidator;

    public List<CartResponseDto.List> cartLists(String nickname) {
//        List<Cart> cartLists = cartValidator.authorization(memberValidator.authorization(nickname).getId());
//        List<CartResponseDto.List> cartResponseDtoList = new ArrayList<>();
//
//        for (Cart cart : cartLists) {
//            //List<Img> imgLists = imgRepository.findAllByProduct_Id(cartLists.get(i).getProduct().getId());
//            //String imgUrl = imgRepository.findFirstByProductId(cart.getProduct().getId()).getImgUrl();
//
//            //System.out.println("img"+imgUrl);
//            CartResponseDto.List cartResponseDto = CartResponseDto.List.builder()
//                    .cartId(cart.getId())
//                    .category(cart.getProduct().getCategory())
//                    .bill(cart.getBill())
//                    .ea(cart.getEa())
//                    .price(cart.getProduct().getPrice())
//                    .firstImg(cart.getProduct().getFirstImg())
//                    .reviewCnt(cart.getProduct().getReviewCnt())
//                    .title(cart.getProduct().getTitle())
//                    .optionContent(cart.getOptionContent())
//                    .productId(cart.getProduct().getId())
//                    .build();
//            cartResponseDtoList.add(cartResponseDto);
//        }

        List<CartResponseDto.List> list = cartRepository.findAllCartByNickname(nickname);

        return list;
    }

    @Transactional
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

    @Transactional
    public Cart modifyingEa(String nickname, CartRequestDto.Ea dto) {
        memberValidator.authorization(nickname);
        Cart cart = cartValidator.findById(dto.getCartId());
        int onePrice = cart.getBill()/cart.getEa();
        cart.eaUpdate(dto.getEa());
        cart.billUpdate(onePrice*dto.getEa());
        return cart;
    }
}
