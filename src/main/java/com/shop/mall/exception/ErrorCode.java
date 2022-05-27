package com.shop.mall.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request - 잘못된 요청 (REQUEST 가 정확한 정보가 아님).
    // 403 Forbidden - 해당 요청에 대한 권한이 없음.
    // 404 Not Found - 해당 RESOURCE 를 찾을 수 없음 (경로가 없는 경우).

    //회원가입 시
    USERNAME_DUPLICATE(HttpStatus.BAD_REQUEST, "400_Register_1", "이미 존재하는 이메일 혹은 닉네임 입니다."),
    PW_NOT_MATCH_PWCHECK(HttpStatus.BAD_REQUEST,"400_Register_2", "패스워드와 패스워드 확인이 일치하지 않습니다."),

    // 로그인 관련 에러 모음
    LOGIN_NOT_MATCH(HttpStatus.BAD_REQUEST, "400_Login_1", "로그인 정보가 정확하지 않습니다. 다시 입력해주십시요."),

    // 회원 존재 유무
    MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST,"400_Member_1","해당 닉네임을 찾을 수 없습니다."),

    // 상품 존재 유무
    PRODUCT_NOT_EXIST(HttpStatus.BAD_REQUEST,"400_Product_1","해당 상품을 찾을 수 없습니다."),

    // 리뷰 존재 유무
    REVIEW_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"400_Review_1","이미 리뷰가 작성 되어있습니다."),
    REVIEW_NOT_EXIST(HttpStatus.BAD_REQUEST,"400_Review_2","작성된 리뷰가 존재하지 않습니다."),

    // 장바구니 존재 유무
    CART_NOT_EXIST(HttpStatus.BAD_REQUEST,"400_Cart_1","장바구니에 존재하지 않습니다."),

    // 구매 목록 존재 여부
    ORDERS_NOT_EXIST(HttpStatus.BAD_REQUEST,"400_Orders_1","구매목록이 존재하지 않습니다."),

    // 구매 상품 존재 여부
    ORDERSDETAIL_NOT_EXIST(HttpStatus.BAD_REQUEST,"400_OrdersDetail_1","구매상품이 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
