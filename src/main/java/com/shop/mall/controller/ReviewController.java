package com.shop.mall.controller;

import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.dto.ReviewRequestDto;
import com.shop.mall.dto.ReviewResponseDto;
import com.shop.mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    //19번 API 리뷰 작성
    @PostMapping("/reviews/{orderDetailId}")
    public ReviewResponseDto.Write writeReview(@RequestHeader(value = "Authorization") String nickname, @PathVariable Long orderDetailId, @RequestBody ReviewRequestDto.Write dto) {
        return reviewService.writeReview(nickname, orderDetailId, dto);
    }
}

