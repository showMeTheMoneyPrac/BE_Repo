package com.shop.mall.controller;

import com.shop.mall.dto.*;
import com.shop.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/regist") //1번 api(회원 가입)
    public String memberRegist(@RequestBody MemberRequestDto.Regist dto) {
        return memberService.memberRegist(dto);
    }

    @PostMapping("/members/login") //2번 api(로그인)
    public MemberResponseDto.Login memberLogin(@RequestBody MemberRequestDto.Login dto) {
        return memberService.memberLogin(dto);
    }

    @GetMapping("/members/info") //3번 api(유저 상세 정보)
    public MemberResponseDto.Info memberInfo(@RequestHeader(value = "nickname") String nickname) {
        return memberService.memberInfo(nickname);
    }

    @PatchMapping("/members/cash") //4번 api(캐시 충전)
    public MemberResponseDto.Cash cashCharge(@RequestHeader(value = "nickname") String nickname,
                                            @RequestBody MemberRequestDto.Cash dto) {
        return memberService.cashCharge(nickname, dto.getChargeCash());
    }

    @PatchMapping("/members/address") //5번 api(주소 변경)
    public MemberResponseDto.Address addressChange(@RequestHeader(value = "nickname") String nickname,
                                                  @RequestBody MemberRequestDto.Address dto) {
        return memberService.addressChange(nickname, dto.getAfterAddress());
    }

    @PatchMapping("/members/nickname") //6번 api(닉네임 변경)
    public MemberResponseDto.Name nameChange(@RequestHeader(value = "nickname") String nickname,
                                            @RequestBody MemberRequestDto.Name dto) {
        return memberService.nameChange(nickname, dto.getAfterNickname());
    }

    @DeleteMapping("/members") //7번 api(회원 탈퇴)
    public String memberDelete(@RequestHeader(value = "nickname") String nickname){
        return memberService.memberDelete(nickname);
    }
}
