package com.shop.mall.repository;

import com.shop.mall.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    List<Orders> findAllByMemberId(Long memberId);
}
