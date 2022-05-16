package com.shop.mall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Cart2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name="option_content")
    private String optionContent;

    @Column
    private int ea;

    @Column
    private int bill;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member2 member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product2 product;
}
