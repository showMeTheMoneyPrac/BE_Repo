package com.shop.mall.service;

import com.shop.mall.domain.*;
import com.shop.mall.dto.ReviewRequestDto;
import com.shop.mall.dto.ReviewResponseDto;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.repository.OrdersDetailRepository;
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
    OrdersDetailRepository ordersDetailRepository;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MemberValidator memberValidator;
    @Autowired
    OrdersDetailValidator ordersDetailValidator;


    @Test
    @Transactional
    @DisplayName("리뷰 작성 성공")
    void 리뷰_작성_성공() throws Exception {
        //given
        //member 생성
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        //product 생성
        List<Img> imgList = new ArrayList<>();
        Img img = new Img("img_url");
        imgList.add(img);
        Product product = Product.builder()
                .imgList(imgList)
                .reviewCnt(0)
                .build();
        productRepository.save(product);


        //orderDetails 생성
        OrdersDetail ordersDetail = OrdersDetail.builder()
                .product(product)
                .build();
        Long orderDetailsId = ordersDetailRepository.save(ordersDetail).getId();


        ReviewRequestDto.Write dto = ReviewRequestDto.Write.builder()
                .reviewTitle("리뷰 제목입니다.")
                .content("리뷰 내용입니다.")
                .build();

        //when
        ReviewResponseDto.Write reviewResponse = reviewService.writeReview(member.getNickname(), orderDetailsId, dto);

        //then
        assertEquals("리뷰 제목입니다.", reviewResponse.getReviewTitle());
        assertEquals("리뷰 내용입니다.", reviewResponse.getContent());
        assertEquals(1, product.getReviewCnt());

    }

    @Test
    @Transactional
    @DisplayName("중복 리뷰 작성 실패")
    void 중복_리뷰_작성_실패() throws Exception {
        //given
        //member 생성
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        //product 생성
        List<Img> imgList = new ArrayList<>();
        Img img = new Img("img_url");
        imgList.add(img);
        Product product = Product.builder()
                .imgList(imgList)
                .reviewCnt(0)
                .build();
        productRepository.save(product);

        //orderDetails 생성
        OrdersDetail ordersDetail = OrdersDetail.builder()
                .product(product)
                .build();

        Long orderDetailsId = ordersDetailRepository.save(ordersDetail).getId();

        ReviewRequestDto.Write dto = ReviewRequestDto.Write.builder()
                .reviewTitle("리뷰 제목입니다.")
                .content("리뷰 내용입니다.")
                .build();

        Review review = Review.builder()
                .title(dto.getReviewTitle())
                .content(dto.getContent())
                .member(member)
                .product(product)
                .build();

        reviewRepository.save(review);

        //when
        try {
            ReviewResponseDto.Write reviewResponse = reviewService.writeReview(member.getNickname(), orderDetailsId, dto);
        } catch (IllegalArgumentException e) {
            return;
        }
        //then
        fail();

    }

    @Test
    @Transactional
    @DisplayName("리뷰 수정 성공")
    void 리뷰_수정_성공() throws Exception {
        //given
        //member 생성
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        Review review = Review.builder()
                .title("리뷰 제목입니다.")
                .content("리뷰 내용입니다.")
                .member(member)
                .build();

        Long reviewId = reviewRepository.save(review).getId();

        ReviewRequestDto.Write dto = ReviewRequestDto.Write.builder()
                .reviewTitle("바뀐 리뷰 제목입니다.")
                .content("바뀐 리뷰 내용입니다.")
                .build();
        //when
        ReviewResponseDto.Write write = reviewService.updateReview(member.getNickname(), reviewId, dto);

        //then
        assertEquals(reviewId, write.getReviewId());
        assertEquals("바뀐 리뷰 제목입니다.", write.getReviewTitle());
        assertEquals("바뀐 리뷰 내용입니다.", write.getContent());

    }

    @Test
    @Transactional
    @DisplayName("리뷰 수정 실패")
    void 리뷰_수정_실패() throws Exception {
        //given
        //member 생성
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        Review review = Review.builder()
                .title("리뷰 제목입니다.")
                .content("리뷰 내용입니다.")
                .member(member)
                .build();

        //Long reviewId = reviewRepository.save(review).getId();

        ReviewRequestDto.Write dto = ReviewRequestDto.Write.builder()
                .reviewTitle("바뀐 리뷰 제목입니다.")
                .content("바뀐 리뷰 내용입니다.")
                .build();
        //when
        try {
            ReviewResponseDto.Write write = reviewService.updateReview(member.getNickname(), 1000L, dto);
        } catch (IllegalArgumentException e) {
            return;
        }
        //then
        fail();

    }

    @Test
    @Transactional
    @DisplayName("리뷰 삭제 성공")
    void 리뷰_삭제_성공() {
        //given
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        Review review = Review.builder()
                .title("리뷰 제목입니다.")
                .content("리뷰 내용입니다.")
                .member(member)
                .build();

        Long reviewId = reviewRepository.save(review).getId();

        //when
        ReviewResponseDto.Delete delete = reviewService.deleteReview(member.getNickname(),reviewId);

        //then
        assertEquals("리뷰 삭제에 성공하였습니다.",delete.getMsg());
    }

    @Test
    @Transactional
    @DisplayName("리뷰 삭제 실패")
    void 리뷰_삭제_실패() {
        //given
        Member member = new Member(
                "seanlee0701@naver.com",
                "이승수",
                "장충동",
                "passworddsd",
                0);
        memberRepository.save(member);

        Review review = Review.builder()
                .title("리뷰 제목입니다.")
                .content("리뷰 내용입니다.")
                .member(member)
                .build();

        Long reviewId = reviewRepository.save(review).getId();

        //when
        try {
            ReviewResponseDto.Delete delete = reviewService.deleteReview(member.getNickname(), reviewId + 1);
        }catch (ErrorCodeException e){
            return;
        }

        //then
        fail();
    }
}