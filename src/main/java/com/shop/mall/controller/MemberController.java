package com.shop.mall.controller;

import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/regist")
    public String memberRegist(@RequestBody MemberRegistRequestDto memberRegistRequestDto){
        return memberService.memberRegist(memberRegistRequestDto);
    }

}
