package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


public class MemberRequestDto {

    @Getter
    @Builder
    public static class Regist {
        String email;
        String nickname;
        String address;
        String password;
        String passwordCheck;
    }

    @Getter
    @AllArgsConstructor
    public static class Address{
        private String beforeAddress;
        private String afterAddress;
    }

    @Getter
    @AllArgsConstructor
    public static class Cash{
        int chargeCash;
        int leftCash;
    }

    @Getter
    @AllArgsConstructor
    public static class Login{
        String email;
        String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Name {
        String beforeNickname;
        String afterNickname;
    }
}
