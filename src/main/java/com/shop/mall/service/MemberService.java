package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.*;
import com.shop.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member authorization(String nickname){
        // 반복되는 어쏘리제이션을 따로 빼서 관리해보자
        return memberRepository.findByNickname(nickname).orElseThrow(
                ()->new IllegalArgumentException("not found nickname")
        );
    }

    @Transactional
    public String memberRegist(MemberRequestDto.Regist dto){
        if (!dto.getPassword().equals(dto.getPasswordCheck())){
            return "msg : 패스워드 일치 하지 않음";
        }
        if(memberRepository.existsByEmailOrNickname(dto.getEmail(),dto.getNickname())){
            return "msg : 이메일 혹은 닉네임 중복";
        }
        Member member = new Member(dto.getEmail(),
                dto.getNickname(),
                dto.getAddress(),
                dto.getPassword(),
                0);
        memberRepository.save(member);
        return "msg : 회원가입 완료";
    }

    //로그인
    public MemberResponseDto.Login memberLogin(MemberRequestDto.Login dto) {
        Member member = memberRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword()).orElseThrow(
                ()->new IllegalArgumentException("not found member")
        );
        return new MemberResponseDto.Login(member.getNickname());
    }

    //정보 보기
    public MemberResponseDto.Info memberInfo(String nickname) {
        return new MemberResponseDto.Info(authorization(nickname).getNickname(),
                authorization(nickname).getAddress(),authorization(nickname).getCash());
    }

    @Transactional
    public MemberResponseDto.Cash cashCharge(String nickname, int chargeCash) {
        int totalCash = authorization(nickname).charge(chargeCash);
        return new MemberResponseDto.Cash(totalCash);
    }

    @Transactional
    public MemberResponseDto.Address addressChange(String nickname, String afterAddress) {
        authorization(nickname).addressUpdate(afterAddress);
        return new MemberResponseDto.Address(afterAddress);
    }

    @Transactional
    public MemberResponseDto.Name nameChange(String nickname, String afterName) {
        authorization(nickname).nameUpdate(afterName);
        return new MemberResponseDto.Name(afterName);
    }

    @Transactional
    public String memberDelete(String nickname) {
        memberRepository.deleteByNickname(nickname).orElseThrow(
                ()->new IllegalArgumentException("not found nickname")
        );
        return "msg : 회원 탈퇴 완료";
    }
}
