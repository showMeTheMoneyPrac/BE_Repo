package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


public class MemberRequestDto {

    @Getter
    @Builder
    public static class Regist {
        private String email;
        private String nickname;
        private String address;
        private String password;
        private String passwordCheck;
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
        private int chargeCash;
        private int leftCash;
    }

    @Getter
    @AllArgsConstructor
    public static class Login{
        private String email;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Name {
        private String beforeNickname;
        private String afterNickname;
    }
}
