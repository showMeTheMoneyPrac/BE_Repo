package com.shop.mall.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_detail_id")
    private Long id;

    @Column(name = "option_content")
    private String optionContent;

    @Column
    private int ea;

    @Column
    private int bill;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    public OrdersDetail(String optionContent, int ea, int bill, Orders orders, Product product) {
        this.optionContent = optionContent;
        this.ea = ea;
        this.bill = bill;
        this.orders = orders;
        this.product = product;
    }

    //연관 관계 매핑시 서로 참조 할 수 있도록 해주는 메서드
    public void setOrders(Orders orders){
        this.orders=orders;
        orders.getOrdersDetailList().add(this);
    }
}
