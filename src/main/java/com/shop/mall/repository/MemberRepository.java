package com.shop.mall.repository;

import com.shop.mall.domain.Member2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member2,Long> {
    //회원가입 시 중복되는 email이 존재하는지 확인
    Boolean existsByEmail(String email);

    //회원가입 시 중복되는 nickname이 존재하는지 확인
    Boolean existsByNickname(String nickname);

    //로그인 시 이메일 확인
    Optional<Member2> findByEmail(String email);

    //회원 탈퇴
    void deleteByNickname(String nickname);
}
