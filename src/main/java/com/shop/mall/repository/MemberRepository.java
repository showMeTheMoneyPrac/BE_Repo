package com.shop.mall.repository;

import com.shop.mall.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByEmailOrNickname(String email, String nickname);
    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<Member> findByNickname(String nickname);

}
