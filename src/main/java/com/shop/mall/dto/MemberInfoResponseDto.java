package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberInfoResponseDto {
    String nickname;
    String address;
    int cash;
}
