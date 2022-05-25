package com.shop.mall.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String category;

    @Column
    private String detail;

    @Column
    private int price;

    @Column
    @Setter
    private int reviewCnt;

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

    public Product(List<Img> imgList) {
        this.imgList = imgList;
    }

    public Product(List<Img> imgList, List<ProductOption> productOptionList, List<Review> reviewList) {
        this.imgList = imgList;
        this.productOptionList = productOptionList;
        this.reviewList = reviewList;
    }

}
