package com.shop.mall.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long id;

    @Column(name = "option_content")
    private String optionContent;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
