package com.shop.mall.service;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.MemberRegistRequestDto;
import com.shop.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public String memberRegist(MemberRegistRequestDto dto){
        JSONObject obj = new JSONObject();
        if(memberRepository.existsByEmailOrNickname(dto.getEmail(),dto.getNickname())){
            obj.put("msg","이메일 혹은 닉네임이 중복됩니다.");
        }
        Member member = new Member(dto.getEmail(),
                dto.getNickname(),
                dto.getAddress(),
                dto.getPassword(),
                dto.getCash());
        memberRepository.save(member);
        obj.put("msg","회원가입 완료");
        return obj.toString();
    }
}
