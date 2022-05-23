package com.shop.mall.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrdersDetail> ordersDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptionList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Img> imgList = new ArrayList<>();
}
