package com.shop.mall.dto;

import com.shop.mall.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReviewList {
        private Long reviewId;
        private String title;
        private String nickname;
        private String content;
        private String createdAt;

        public static ReviewList reviewListFrom(Review review){
            return ReviewList.builder()
                    .reviewId(review.getId())
                    .title(review.getTitle())
                    .nickname(review.getMember().getNickname())
                    .content(review.getContent())
                    .createdAt(String.valueOf(review.getCreatedAt()))
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Write{
        private Long reviewId;
        private String reviewTitle;
        private String content;
    }

    @Getter
    @Builder
    public static class Delete{
        private String msg;
    }
}
