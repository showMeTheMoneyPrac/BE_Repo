package com.shop.mall.repository;

import com.shop.mall.domain.Member2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member2,Long> {

    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickname);
}
