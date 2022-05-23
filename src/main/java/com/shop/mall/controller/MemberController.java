package com.shop.mall.controller;

import com.shop.mall.dto.MemberInfoResponseDto;
import com.shop.mall.dto.MemberLoginRequestDto;
import com.shop.mall.dto.MemberLoginResponseDto;
import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/regist") //1번 api
    public String memberRegist(@RequestBody MemberRegistRequestDto dto){
        return memberService.memberRegist(dto);
    }

    @PostMapping("/members/login") //2번 api
    public MemberLoginResponseDto memberLogin(@RequestBody MemberLoginRequestDto dto){
        return memberService.memberLogin(dto);
    }

    @GetMapping("/members/info") //3번 api
    public MemberInfoResponseDto memberInfo(@RequestHeader(value = "nickname") String nickname){
        return memberService.memberInfo(nickname);
    }
}
