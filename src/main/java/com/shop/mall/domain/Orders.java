package com.shop.mall.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @Column
    private String address;

    @Column
    private int totalPrice;


    @OneToMany(mappedBy = "orders")
    private List<OrdersDetail> ordersDetailList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;






}
