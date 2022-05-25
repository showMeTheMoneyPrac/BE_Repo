package com.shop.mall.service;

import com.shop.mall.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;

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