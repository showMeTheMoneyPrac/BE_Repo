package com.shop.mall.service;

import com.shop.mall.domain.Img;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.Product.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void request() {
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100000);

        Member member2 = new Member(
                "john310@gmail.com",
                "정요한2",
                "우리집2",
                "passworddd",
                0);

        List<Img> imgList = new ArrayList<>();
        Img img = new Img("test");
        imgList.add(img);

        Product product = Product.builder()
                .category("신발")
                .detail("tt")
                .build();
    }


    @Test
    public void 장바구니_목록_조회성공() throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 장바구니_목록_조회실패() throws Exception{
        //given

        //when

        //then
    }
}