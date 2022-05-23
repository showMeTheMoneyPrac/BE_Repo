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
                .email("하이@gmail.com")
                .password("비밀번호486")
                .passwordCheck("비밀번호486")
                .nickname("내이름2")
                .address("내주소")
                .build();

        Member member2 = new Member(
                "하이1@gmail.com",
                "내이름3",
                "주소1",
                "비번2",
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
                "하이@gmail.com",
                "내이름",
                "주소1",
                "비번2",
                0);
        memberRepository.save(member);

        MemberRegistRequestDto dto = MemberRegistRequestDto.builder()
                .email("하이@gmail.com")
                .password("비밀번호486")
                .passwordCheck("비밀번호486")
                .nickname("내이름2")
                .address("내주소")
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
                "하이1@gmail.com",
                "내이름",
                "주소1",
                "비번2",
                0);
        memberRepository.save(member);

        MemberRegistRequestDto dto = MemberRegistRequestDto.builder()
                .email("하이@gmail.com")
                .password("비밀번호486")
                .passwordCheck("비밀번호486")
                .nickname("내이름")
                .address("내주소")
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
                .email("하이@gmail.com")
                .password("비밀번호486")
                .passwordCheck("비밀번호4986")
                .nickname("내이름")
                .address("내주소")
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
                "하이1@gmail.com",
                "내이름",
                "주소1",
                "비번2",
                0);
        memberRepository.save(member);
        MemberLoginRequestDto dto = new MemberLoginRequestDto("하이1@gmail.com","비번2");
        //when
        String name = memberService.memberLogin(dto).getNickname();
        //then
        assertEquals(name,"내이름");
    }
    @Test
    @Transactional
    public void 로그인_실패() throws Exception{
        //given
        Member member = new Member(
                "하이1@gmail.com",
                "내이름",
                "주소1",
                "비번2",
                0);
        memberRepository.save(member);
        MemberLoginRequestDto dto = new MemberLoginRequestDto("하이2@gmail.com","비번2");
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
                "하이1@gmail.com",
                "내이름",
                "주소1",
                "비번2",
                0);
        memberRepository.save(member);
        String headerNickname = "내이름";
        //when
        MemberInfoResponseDto dto = memberService.memberInfo(headerNickname);
        //then
        assertEquals(dto.getAddress(),member.getAddress());
        assertEquals(dto.getCash(),member.getCash());
        assertEquals(dto.getNickname(),member.getNickname());
    }
}