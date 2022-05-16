package com.shop.mall.service;


import com.shop.mall.domain.Member2;
import com.shop.mall.repository.MemberJpaRepository;
import com.shop.mall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;

    //1번 API 회원 가입
    @Transactional
    public Long register(Member2 member) { // 위 줄이 controller에서 Dto형태로 들어올 것이고 validat에서 걸러줘야할 거임 그 다음에 builder 패턴으로 member 엔티티 만들어주고 save하면 됨
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member2 member) {
        //EXCEPTION
        Boolean findByEmailMember = memberRepository.existsByEmail(member.getEmail());
        if(findByEmailMember){
            throw new IllegalStateException("이미 존재하는 Email입니다.");
        }

        Boolean findByNicknameMember = memberRepository.existsByNickname(member.getNickname());
        if(findByNicknameMember){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    //2번 API 로그인
    public String Login(Member2 member){
        Member2 LoginMember = memberJpaRepository.findByEmail(member.getEmail());
        if (LoginMember == null){
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
        if(!Objects.equals(LoginMember.getPassword(), member.getPassword())){
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }

        return LoginMember.getNickname();
    }

    //3번 API 사용자 정보 확인
    public String memberInfo(Member2 member){
        Member2 memberInfoByNickname = memberJpaRepository.findByNickname(member.getNickname());
        if(memberInfoByNickname==null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        return memberInfoByNickname.getNickname();
    }

    //4번 API 캐시 충전
    public int cahrgeCash(Member2 member2, int plusCash){
        memberJpaRepository.chargeCash(member2);
        return member2.getCash();
    }

}
