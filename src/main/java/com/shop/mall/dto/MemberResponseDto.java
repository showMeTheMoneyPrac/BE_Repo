package com.shop.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberResponseDto {



    @AllArgsConstructor
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
        String nickname;
        String address;
        int cash;
    }

    @AllArgsConstructor
    @Getter
    public static class Login {
        String nickname;
    }

    @Getter
    @AllArgsConstructor
    public static class Name {
        String afterNickname;
    }
}
