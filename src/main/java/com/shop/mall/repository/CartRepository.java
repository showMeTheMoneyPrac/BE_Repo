package com.shop.mall.repository;

import com.shop.mall.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByMember_Id(Long memberId);
}
