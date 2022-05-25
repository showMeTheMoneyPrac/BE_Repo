package com.shop.mall.service;

import com.shop.mall.domain.Img;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.ReviewRequestDto;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.repository.Product.ProductRepository;
import com.shop.mall.repository.ReviewRepository;
import com.shop.mall.validator.MemberValidator;
import com.shop.mall.validator.OrdersDetailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberValidator memberValidator;
    @Autowired
    OrdersDetailValidator ordersDetailValidator;

    @Test
    @Transactional
    @DisplayName("리뷰 작성 성공")
    void 리뷰_작성_성공() throws Exception {
        //given
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        List<Img> imgList = new ArrayList<>();
        Img img = new Img("img_url");
        imgList.add(img);
        productRepository.save(new Product(imgList));


        ReviewRequestDto.Write dto = ReviewRequestDto.Write.builder()
                .reviewTitle("리뷰 제목입니다")
                .content("리뷰 내용입니다.")
                .build();





    }

    @Test
    @Transactional
    @DisplayName("리뷰 수정 성공")
    void 리뷰_수정_성공() throws Exception {

    }

    @Test
    @Transactional
    @DisplayName("리뷰 삭제 성공")
    void 리뷰_삭제_성공() {

    }
}