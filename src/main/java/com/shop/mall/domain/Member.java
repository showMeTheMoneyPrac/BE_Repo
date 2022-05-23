package com.shop.mall.domain;


import com.shop.mall.dto.MemberCashRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamped{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    @Column
    private String address;

    @Column
    private String password;

    @Column
    private int cash;

    @OneToMany(mappedBy = "member")
    private List<Orders> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviewList = new ArrayList<>();

    //회원 가입 Test를 위한 코드
    public Member(String email, String nickname, String address, String password, int cash){
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.password = password;
        this.cash = cash;
    }

    public int charge(int chargeCash){
        this.cash = chargeCash + this.cash;
        return this.cash;
    }

}
