package com.shop.mall.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name="img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Img(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Img(String imgUrl, Product product) {
        this.imgUrl = imgUrl;
        this.product = product;
    }
}
