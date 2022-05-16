package com.shop.mall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Review2 extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;


    @Column
    private String title;

    @Column
    private String content;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member2 member;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product2 product;

}
