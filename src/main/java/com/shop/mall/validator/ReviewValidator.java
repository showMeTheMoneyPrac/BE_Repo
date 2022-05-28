package com.shop.mall.validator;

import com.shop.mall.domain.Review;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shop.mall.exception.ErrorCode.REVIEW_ALREADY_EXIST;
import static com.shop.mall.exception.ErrorCode.REVIEW_NOT_EXIST;

@Component // 선언하지 않으면 사용할 수 없다!!!!!
@RequiredArgsConstructor
public class ReviewValidator {
    private final ReviewRepository reviewRepository;

    public Boolean isExistByMemberAndProduct(Long memberId, Long productId) {
        boolean isExistReview = reviewRepository.existsByMemberIdAndProductId(memberId, productId);
        if (isExistReview) {
            throw new ErrorCodeException(REVIEW_ALREADY_EXIST);
        }
        return true;
    }

    public Review authorization(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new ErrorCodeException(REVIEW_NOT_EXIST)
        );
    }

    public Boolean isExistByIdANDMember(Long reviewId, Long memberId) {
        boolean isExistReview = reviewRepository.existsByIdAndMemberId(reviewId, memberId);
        if (!isExistReview) {
            throw new ErrorCodeException(REVIEW_NOT_EXIST);
        }
        return true;
    }

}
