package com.shop.mall.repository;

import com.shop.mall.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByEmailOrNickname(String email, String nickname);
}
