package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

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

}