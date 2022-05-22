package com.shop.mall.service;


import com.shop.mall.domain.Member2;
import com.shop.mall.repository.MemberJpaRepository;
import com.shop.mall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;

    //1번 API 회원 가입
    @Transactional
    public Long register(Member2 member) { // 위 줄이 controller에서 Dto형태로 들어올 것이고 validat에서 걸러줘야할 거임 그 다음에 builder 패턴으로 member 엔티티 만들어주고 save하면 됨
        validateDuplicateMemberRegister(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    //회원가입 시 제약조건 설정
    private void validateDuplicateMemberRegister(Member2 member) {
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
    public String login(Member2 member){
        Member2 loginMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        if (loginMember == null){
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        if(!Objects.equals(loginMember.getPassword(), member.getPassword())){
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }

        return loginMember.getNickname();
    }

    private void validateDuplicateLogin(Member2 member, Member2 loginMember){
        if (loginMember == null){
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        if(!Objects.equals(loginMember.getPassword(), member.getPassword())){
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }

    }

    //3번 API 사용자 정보 확인
    public String memberInfo(Member2 member){
        Optional<Member2> memberInfoByNickname = Optional.ofNullable(memberJpaRepository.findByNickname(member.getNickname()));
        if(!memberInfoByNickname.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        return memberInfoByNickname.get().getNickname();
    }

    //4번 API 캐시 충전
    public int chargeCash(Member2 member2, int plusCash){
        memberJpaRepository.chargeCash(member2);
        return member2.getCash();
    }

    //5번 API 주소 변경
    public String changeAddress(String nickname, String address){
        memberJpaRepository.changeAddress(nickname,address);
        return address;
    }

    //6번 API 닉네임 변경
    public String changeNickname(String nickname, String afterNickname){
        memberJpaRepository.changeNickname(nickname,afterNickname);
        return afterNickname;
    }

    //7번 API 회원 탈퇴
    public String deleteMember(String nickname){
        memberRepository.deleteByNickname(nickname);
        return "회원탈퇴 완료";
    }

}
