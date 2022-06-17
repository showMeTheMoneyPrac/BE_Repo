package com.shop.mall.controller;

import com.shop.mall.dto.ReviewRequestDto;
import com.shop.mall.dto.ReviewResponseDto;
import com.shop.mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    //18번 API 리뷰 작성
    @PostMapping("/reviews/{orderDetailId}")
    public ReviewResponseDto.Write writeReview(@RequestHeader(value = "Authorization") String nickname, @PathVariable Long orderDetailId,
                                               @RequestBody ReviewRequestDto.Write dto) {
        return reviewService.writeReview(nickname, orderDetailId, dto);
    }

    //29번 API 리뷰 수정
    @PutMapping("/reviews/{reviewId}")
    public ReviewResponseDto.Write updateReview(@RequestHeader(value = "Authorization") String nickname,@PathVariable Long reviewId,
                                                @RequestBody ReviewRequestDto.Write dto){
        return reviewService.updateReview(nickname,reviewId,dto);
    }

    //20번 API 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ReviewResponseDto.Delete deleteReview(@RequestHeader(value = "Authorization") String nickname,@PathVariable Long reviewId){
        return reviewService.deleteReview(nickname,reviewId);
    }
}

