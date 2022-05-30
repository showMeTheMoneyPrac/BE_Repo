package com.shop.mall.validator;

import com.shop.mall.domain.Member;
import com.shop.mall.dto.MemberRequestDto;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shop.mall.exception.ErrorCode.*;

@Component // 선언하지 않으면 사용할 수 없다!!!!!
@RequiredArgsConstructor
public class MemberValidator {
    private final MemberRepository memberRepository;

    public Member authorization(String nickname){
        // 반복되는 어쏘리제이션을 따로 빼서 관리해보자
        return memberRepository.findByNickname(nickname).orElseThrow(
                () -> new ErrorCodeException(MEMBER_NOT_EXIST)
        );
    }

    public void registerDto(MemberRequestDto.Regist dto){
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new ErrorCodeException(PW_NOT_MATCH_PWCHECK);
        }
        if (memberRepository.existsByEmailOrNickname(dto.getEmail(), dto.getNickname())) {
            throw new ErrorCodeException(USERNAME_DUPLICATE);
        }
    }
}
