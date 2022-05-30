package com.shop.mall.repository;

import com.shop.mall.domain.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail,Long>{
    @Modifying
    @Transactional
    @Query("delete from OrdersDetail o where o.id=:ordersDetailId")
    void deleteById(Long ordersDetailId);

    @Modifying
    @Transactional
    void deleteByOrders_Id(Long ordersId);
}
