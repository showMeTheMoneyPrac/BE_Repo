package com.shop.mall.dto;

import com.shop.mall.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDto {

    @Getter
    @Builder
    public static class ReviewList{
        private Long reviewId;
        private String title;
        private String nickname;
        private String content;
        private LocalDateTime createdAt;

        public static ReviewList reviewListFrom(Review review){
            return ReviewList.builder()
                    .reviewId(review.getId())
                    .title(review.getTitle())
                    .nickname(review.getMember().getNickname())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .build();
        }
    }
}
