package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.MemberRequestDto;
import com.shop.mall.dto.MemberResponseDto;
import com.shop.mall.exception.ErrorCode;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    public void 회원가입_성공() throws Exception {
        //given
        MemberRequestDto.Regist dto = MemberRequestDto.Regist.builder()
                .email("john3210of@gmail.co2m")
                .password("passwordA")
                .passwordCheck("passwordA")
                .nickname("정요한2")
                .address("우리집이에요")
                .build();

        //when
        String msg = memberService.memberRegist(dto);
        //then
        assertEquals(msg, "msg : 회원가입 완료");
    }

    @Test
    @Transactional
    public void 회원가입_이메일중복() throws Exception {
        //given
        Member member = new Member(
                "john3210of@gmail.com1",
                "정요한1",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);

        MemberRequestDto.Regist dto = MemberRequestDto.Regist.builder()
                .email("john3210of@gmail.com1")
                .password("passwordA")
                .passwordCheck("passwordA")
                .nickname("정요한2")
                .address("우리집이에요")
                .build();
        //when
        try{
            memberService.memberRegist(dto);
        }catch (ErrorCodeException e){
            assertEquals(e.getErrorCode(),ErrorCode.USERNAME_DUPLICATE);
            return;
        }


    }

    @Test
    @Transactional
    public void 회원가입_이름중복() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);

        MemberRequestDto.Regist dto = MemberRequestDto.Regist.builder()
                .email("john3210of@gmail.com")
                .password("passwordA")
                .passwordCheck("passwordA")
                .nickname("정요한")
                .address("우리집이에요")
                .build();
        //when
        try{
            memberService.memberRegist(dto);
        }catch (ErrorCodeException e){
            assertEquals(e.getErrorCode(),ErrorCode.USERNAME_DUPLICATE);
            return;
        }

    }

    @Test
    @Transactional
    public void 회원가입_비밀번호_불일치() throws Exception {
        //given
        MemberRequestDto.Regist dto = MemberRequestDto.Regist.builder()
                .email("john3210of@gmail.com")
                .password("passwordA")
                .passwordCheck("비밀번호4986")
                .nickname("정요한")
                .address("우리집이에요")
                .build();
        //when
        try{
            memberService.memberRegist(dto);
        }catch (ErrorCodeException e){
            assertEquals(e.getErrorCode(),ErrorCode.PW_NOT_MATCH_PWCHECK);
            return;
        }

        //then

    }

    @Test
    @Transactional
    public void 로그인_성공() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        MemberRequestDto.Login dto = new MemberRequestDto.Login("john3210@gmail.com", "passworddd");
        //when
        String name = memberService.memberLogin(dto).getNickname();
        //then
        assertEquals(name, "정요한");
    }

    @Test
    @Transactional
    public void 로그인_실패() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        MemberRequestDto.Login dto = new MemberRequestDto.Login("하이2@gmail.com", "passworddd");
        //when
        try {
            memberService.memberLogin(dto);
        } catch (ErrorCodeException e) {
            assertEquals(e.getErrorCode(),ErrorCode.LOGIN_NOT_MATCH);
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 유저정보조회_성공() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        String headerNickname = "정요한";
        //when
        MemberResponseDto.Info dto = memberService.memberInfo(headerNickname);
        //then
        assertEquals(dto.getAddress(), member.getAddress());
        assertEquals(dto.getCash(), member.getCash());
        assertEquals(dto.getNickname(), member.getNickname());
    }

    @Test
    @Transactional
    public void 유저정보조회_실패() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        String headerNickname = "정요한아니양";
        //when
        try {
            memberService.memberInfo(headerNickname);
        } catch (ErrorCodeException e) {
            assertEquals(e.getErrorCode(),ErrorCode.MEMBER_NOT_EXIST);
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 캐시충전_성공() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        int chargeCash = 10000;
        //when
        int totalCash = member.getCash() + chargeCash;
        int afterCharge = memberService.cashCharge("정요한", chargeCash).getTotalCash();
        //then
        assertEquals(totalCash, afterCharge);
        assertEquals(10000, afterCharge);
    }

    @Test
    @Transactional
    public void 캐시충전_실패_id없음() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        int chargeCash = 10000;
        //when
        try {
            memberService.cashCharge("정요한2", chargeCash);
        } catch (ErrorCodeException e) {
            assertEquals(e.getErrorCode(),ErrorCode.MEMBER_NOT_EXIST);
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 주소변경_성공() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        String afterAddress = "이사한 우리집";
        //when
        memberService.addressChange("정요한", afterAddress);
        //then
        assertEquals(afterAddress, member.getAddress());
    }

    @Test
    @Transactional
    public void 주소변경_실패() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        String afterAddress = "이사한 우리집";
        //when
        try {
            memberService.addressChange("정요한2", afterAddress);
        } catch (ErrorCodeException e) {
            assertEquals(e.getErrorCode(),ErrorCode.MEMBER_NOT_EXIST);
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 닉네임변경_성공() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        String afterName = "변경한 이름";
        //when
        memberService.nameChange("정요한", afterName);
        //then
        assertEquals(afterName, member.getNickname());
    }

    @Test
    @Transactional
    public void 닉네임변경_실패() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);

        Member member2 = new Member(
                "john@gmail.com",
                "요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        memberRepository.save(member2);
        String afterName = "요한";
        //when
        try {
            memberService.nameChange("정요한", afterName);
        } catch (ErrorCodeException e) {
            assertEquals(e.getErrorCode(),ErrorCode.USERNAME_DUPLICATE2);
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 회원탈퇴_성공() throws Exception {
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한3",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);

        //when
        String msg = memberService.memberDelete("정요한3");
        //then
        assertEquals(msg, "msg : 회원 탈퇴 완료");
    }

    @Test
    @Transactional
    public void 회원탈퇴_연관목록삭제성공() throws Exception{
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한3",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);



        //when
        String msg = memberService.memberDelete("정요한3");

    }
}