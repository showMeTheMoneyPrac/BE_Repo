package com.shop.mall.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Product2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<Cart2> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Orders2Detail> ordersDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review2> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Product2Option> productOptionList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Img2> imgList = new ArrayList<>();
}
