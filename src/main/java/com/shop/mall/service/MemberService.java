package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.MemberLoginRequestDto;
import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public String memberRegist(MemberRegistRequestDto dto){
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

    public String memberLogin(MemberLoginRequestDto dto) {
        Member member = memberRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword()).orElseThrow(
                ()->new IllegalArgumentException("not found")
        );
        JSONObject obj = new JSONObject();
        obj.put("nickname",member.getNickname());  //dto로 바꿔야대넹
        return obj.toString();
    }
}
