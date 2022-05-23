package com.shop.mall.domain;

import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    @Test
    @Transactional
    public void 회원가입() throws Exception{
        //given
        MemberRegistRequestDto memberRegistRequestDto = MemberRegistRequestDto.builder()
                .email("하이@gmail.com")
                .password("비밀번호486")
                .nickname("내이름")
                .address("내주소")
                .cash(0)
                .build();

        //when
        Member member = new Member(memberRegistRequestDto.getEmail(),
                memberRegistRequestDto.getNickname(),
                memberRegistRequestDto.getAddress(),
                memberRegistRequestDto.getPassword(),
                memberRegistRequestDto.getCash());
        memberRepository.save(member);

        //then
        assertEquals(member.getAddress(),memberRegistRequestDto.getAddress());
        assertEquals(member.getCash(),0);
        assertNotEquals(member.getCash(),2);
        assertEquals(member.getPassword(),memberRegistRequestDto.getPassword());
        assertEquals(member.getNickname(),memberRegistRequestDto.getNickname());
        assertEquals(member.getEmail(),memberRegistRequestDto.getEmail());
    }

    @Test
    @Transactional
        public void 회원가입_중복() throws Exception{
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
                    .nickname("내이름")
                    .address("내주소")
                    .cash(0)
                    .build();

            //when


            memberService.memberRegist(dto);


            //then
        }

}