package com.shop.mall.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    public ProductOption(String optionContent) {
        this.optionContent = optionContent;
    }
}
