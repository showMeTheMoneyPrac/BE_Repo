package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.MemberInfoResponseDto;
import com.shop.mall.dto.MemberLoginRequestDto;
import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    @Transactional
    public void 회원가입_성공() throws Exception{
        //given
        MemberRegistRequestDto dto = MemberRegistRequestDto.builder()
                .email("john3210of@gmail.com")
                .password("passwordA")
                .passwordCheck("passwordA")
                .nickname("정요한2")
                .address("우리집이에요")
                .build();

        Member member2 = new Member(
                "john3210@gmail.com",
                "정요한3",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member2);

        //when
        String msg = memberService.memberRegist(dto);
        //then
        assertEquals(msg,"msg : 회원가입 완료");
    }

    @Test
    @Transactional
    public void 회원가입_이메일중복() throws Exception{
        //given
        Member member = new Member(
                "john3210of@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);

        MemberRegistRequestDto dto = MemberRegistRequestDto.builder()
                .email("john3210of@gmail.com")
                .password("passwordA")
                .passwordCheck("passwordA")
                .nickname("정요한2")
                .address("우리집이에요")
                .build();
        //when
        String msg=memberService.memberRegist(dto);
        //then
        assertEquals(msg,"msg : 이메일 혹은 닉네임 중복");
    }

    @Test
    @Transactional
    public void 회원가입_이름중복() throws Exception{
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);

        MemberRegistRequestDto dto = MemberRegistRequestDto.builder()
                .email("john3210of@gmail.com")
                .password("passwordA")
                .passwordCheck("passwordA")
                .nickname("정요한")
                .address("우리집이에요")
                .build();
        //when
        String msg=memberService.memberRegist(dto);
        //then
        assertEquals(msg,"msg : 이메일 혹은 닉네임 중복");
    }

    @Test
    @Transactional
    public void 회원가입_비밀번호_불일치() throws Exception{
        //given
        MemberRegistRequestDto dto = MemberRegistRequestDto.builder()
                .email("john3210of@gmail.com")
                .password("passwordA")
                .passwordCheck("비밀번호4986")
                .nickname("정요한")
                .address("우리집이에요")
                .build();
        //when
        String msg=memberService.memberRegist(dto);
        //then
        assertEquals(msg,"msg : 패스워드 일치 하지 않음");
    }

    @Test
    @Transactional
    public void 로그인_성공() throws Exception{
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        MemberLoginRequestDto dto = new MemberLoginRequestDto("john3210@gmail.com","passworddd");
        //when
        String name = memberService.memberLogin(dto).getNickname();
        //then
        assertEquals(name,"정요한");
    }
    @Test
    @Transactional
    public void 로그인_실패() throws Exception{
        //given
        Member member = new Member(
                "john3210@gmail.com",
                "정요한",
                "우리집3",
                "passworddd",
                0);
        memberRepository.save(member);
        MemberLoginRequestDto dto = new MemberLoginRequestDto("하이2@gmail.com","passworddd");
        //when
        try{
            memberService.memberLogin(dto);
        }catch(IllegalArgumentException e){
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 유저정보조회_성공() throws Exception{
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
        MemberInfoResponseDto dto = memberService.memberInfo(headerNickname);
        //then
        assertEquals(dto.getAddress(),member.getAddress());
        assertEquals(dto.getCash(),member.getCash());
        assertEquals(dto.getNickname(),member.getNickname());
    }

    @Test
    @Transactional
    public void 유저정보조회_실패() throws Exception{
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
        try{
            memberService.memberInfo(headerNickname);
        }catch (IllegalArgumentException e){
            return;
        }
        //then
        fail("실패의 실패");
    }
    @Test
    @Transactional
    public void 캐시충전_성공() throws Exception{
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
        int totalCash = member.getCash()+chargeCash;
        int afterCharge = memberService.cashCharge("정요한",chargeCash).getTotalCash();
        //then
        assertEquals(totalCash,afterCharge);
        assertEquals(10000,afterCharge);
    }

    @Test
    @Transactional
    public void 캐시충전_실패_id없음() throws Exception{
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
        try{
            memberService.cashCharge("정요한2",chargeCash);
        }catch (IllegalArgumentException e){
            return;
        }
        //then
        fail("실패의 실패");
    }

    @Test
    @Transactional
    public void 주소변경_성공() throws Exception{
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
        memberService.addressChange("정요한",afterAddress);
        //then
        assertEquals(afterAddress,member.getAddress());
    }
    @Test
    @Transactional
    public void 주소변경_실패() throws Exception{
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
        try{
            memberService.addressChange("정요한2",afterAddress);
        }catch (IllegalArgumentException e){
            return;
        }
        //then
        fail("실패의 실패");
    }
}