package com.shop.mall.domain;


import lombok.Getter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member2 extends Timestamped{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String address;

    @Column
    private String password;

    @Column
    private int cash;

    @OneToMany(mappedBy = "member")
    private List<Orders2> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart2> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review2> reviewList = new ArrayList<>();
}
