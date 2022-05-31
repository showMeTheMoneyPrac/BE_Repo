package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.domain.Review;
import com.shop.mall.dto.ReviewRequestDto;
import com.shop.mall.dto.ReviewResponseDto;
import com.shop.mall.repository.ReviewRepository;
import com.shop.mall.validator.MemberValidator;
import com.shop.mall.validator.OrdersDetailValidator;
import com.shop.mall.validator.ReviewValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberValidator memberValidator;
    private final OrdersDetailValidator ordersDetailValidator;
    private final ReviewValidator reviewValidator;

    @Transactional
    public ReviewResponseDto.Write writeReview(String nickname, Long orderDetailId, ReviewRequestDto.Write dto) {
        //1번 닉네임으로 유저 존재 확인
        Member member = memberValidator.authorization(nickname);

        //2번 orderDetailId로 유무 확인
        Product product = ordersDetailValidator.authorization(orderDetailId).getProduct();

        //3번 orderDetailId의 productId와 memberId로 이미 리뷰가 존재하는지 확인
        reviewValidator.isExistByMemberAndProduct(member.getId(),product.getId());

        Review review = Review.builder()
                .title(dto.getReviewTitle())
                .content(dto.getContent())
                .member(member)
                .product(product)
                .build();

        Long reviewId = reviewRepository.save(review).getId();

        //리뷰를 작성하면 product테이블의 reviewCnt가 늘어난다.
        product.reviewCnt();

        return ReviewResponseDto.Write.builder()
                .reviewId(reviewId)
                .reviewTitle(dto.getReviewTitle())
                .content(dto.getContent())
                .build();
    }

    @Transactional
    public ReviewResponseDto.Write updateReview(String nickname, Long reviewId, ReviewRequestDto.Write dto) {
        //1번 닉네임으로 유저 존재 확인
        memberValidator.authorization(nickname);

        //reviewId로 존재 유무 확인
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("not found By Id"));

        review.reviewUpdate(dto.getReviewTitle(), dto.getContent());

        return ReviewResponseDto.Write.builder()
                .reviewId(reviewId)
                .reviewTitle(dto.getReviewTitle())
                .content(dto.getContent())
                .build();
    }

    @Transactional
    public ReviewResponseDto.Delete deleteReview(String nickname, Long reviewId) {
        Long memberId = memberValidator.authorization(nickname).getId();

        reviewValidator.isExistByIdANDMember(reviewId,memberId);

        reviewRepository.deleteById(reviewId);

        return ReviewResponseDto.Delete.builder()
                .msg("리뷰 삭제에 성공하였습니다.")
                .build();
    }


}
