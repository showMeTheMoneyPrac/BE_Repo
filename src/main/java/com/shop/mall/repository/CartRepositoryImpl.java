package com.shop.mall.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shop.mall.domain.QMember.member;
import static com.shop.mall.domain.QCart.cart;
import static com.shop.mall.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CartResponseDto.List> findAllCartByNickname(String nickname) {
        return getAllCartByNickname(nickname);
    }

    private List<CartResponseDto.List> getAllCartByNickname(String nickname){
        return jpaQueryFactory
                .select(Projections.constructor(CartResponseDto.List.class,cart.id.as("cartId"),product.id.as("productId"),product.title,product.category,product.firstImg,cart.optionContent,product.reviewCnt,product.price,cart.ea,cart.bill))
                .from(cart)
                .innerJoin(product)
                .on(cart.product.id.eq(product.id))
                .innerJoin(member)
                .on(cart.member.id.eq(member.id))
                .where(member.nickname.eq(nickname))
                .orderBy(cart.id.asc())
                .fetch();

    }


}
