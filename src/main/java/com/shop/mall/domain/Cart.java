package com.shop.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "option_content")
    private String optionContent;

    @Column
    private int ea;

    @Column
    private int bill;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Cart(String option, int ea, int bill, Member member, Product product) {
        this.optionContent = option;
        this.ea = ea;
        this.bill = bill;
        this.member = member;
        this.product = product;
    }

    public void eaUpdate(int ea){
        this.ea = ea;
    }

    public void billUpdate(int bill){
        this.bill = bill;
    }
}
