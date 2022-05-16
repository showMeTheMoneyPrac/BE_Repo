package com.shop.mall.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Img2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name="img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product2 product;
}
