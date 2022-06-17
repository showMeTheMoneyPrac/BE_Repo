package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberResponseDto {

    @AllArgsConstructor
    @Getter
    public static class Address {
        private String afterAddress;
    }

    @AllArgsConstructor
    @Getter
    public static class Cash {
        private int totalCash;
    }

    @AllArgsConstructor
    @Getter
    public static class Info {
        private String nickname;
        private String address;
        private int cash;
    }

    @AllArgsConstructor
    @Getter
    public static class Login {
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    public static class Name {
        private String afterNickname;
    }
}
