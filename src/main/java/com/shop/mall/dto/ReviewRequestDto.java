package com.shop.mall.dto;

import lombok.Builder;
import lombok.Getter;

public class ReviewRequestDto {

    @Getter
    @Builder
    public static class Write{
        private String reviewTitle;
        private String content;
    }
}
