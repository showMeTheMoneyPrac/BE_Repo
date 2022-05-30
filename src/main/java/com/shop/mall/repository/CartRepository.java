package com.shop.mall.repository;

import com.shop.mall.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByMember_Id(Long memberId);

    @Modifying
    @Transactional
    void deleteAllByMember_Id(Long memberId);
}
