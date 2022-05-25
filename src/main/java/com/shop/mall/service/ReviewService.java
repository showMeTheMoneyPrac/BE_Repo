package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.domain.Review;
import com.shop.mall.dto.ReviewRequestDto;
import com.shop.mall.dto.ReviewResponseDto;
import com.shop.mall.repository.ReviewRepository;
import com.shop.mall.validator.MemberValidator;
import com.shop.mall.validator.OrdersDetailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberValidator memberValidator;
    private final OrdersDetailValidator ordersDetailValidator;


    public ReviewResponseDto.Write writeReview(String nickname,Long orderDetailId,ReviewRequestDto.Write dto){
        //1번 닉네임으로 유저 존재 확인
        Member member = memberValidator.authorization(nickname);
        //2번 orderDetailId로 유무 확인
        Product product = ordersDetailValidator.authorization(orderDetailId).getProduct();
        //3번 orderDetailId의 productId와 memberId로 이미 리뷰가 존재하는지 확인
        Boolean isExistReview = reviewRepository.existsByMemberIdAndProductId(member.getId(),product.getId());

        if(isExistReview){
            throw new IllegalArgumentException("이미 작성된 리뷰가 존재합니다");
        }

        Review review = Review.builder()
                .title(dto.getReviewTitle())
                .content(dto.getContent())
                .member(member)
                .product(product)
                .build();

        Long reviewId = reviewRepository.save(review).getId();

        //리뷰를 작성하면 product테이블의 reviewCnt가 늘어난다.
        product.reviewCntUpdate();

        return ReviewResponseDto.Write.builder()
                .reviewId(reviewId)
                .reviewTitle(dto.getReviewTitle())
                .content(dto.getContent())
                .build();
    }

    public ReviewResponseDto.Write updateReview(String nickname,Long reviewId,ReviewRequestDto.Write dto){
        //1번 닉네임으로 유저 존재 확인
        Member member = memberValidator.authorization(nickname);
        //reviewId로 존재 유무 확인
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("not found By Id"));

        review.reviewUpdate(dto.getReviewTitle(),dto.getContent());

        return ReviewResponseDto.Write.builder()
                .reviewId(reviewId)
                .reviewTitle(dto.getReviewTitle())
                .content(dto.getContent())
                .build();
    }


}