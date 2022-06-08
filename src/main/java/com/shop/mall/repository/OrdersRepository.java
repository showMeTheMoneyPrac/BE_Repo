package com.shop.mall.repository;

import com.shop.mall.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    List<Orders> findAllByMemberId(Long memberId);

    @Modifying
    @Transactional
    void deleteAllByMember_Id(Long memberId);

}
