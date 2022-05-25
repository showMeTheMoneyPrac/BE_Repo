package com.shop.mall.service;

import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Img;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.CartRequestDto;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.repository.Product.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ImgRepository imgRepository;

    @Test
    public void 장바구니_담기_성공() throws Exception{
        //given
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100000);
        memberRepository.save(member1);

        Member member2 = new Member(
                "john310@gmail.com",
                "정요한2",
                "우리집2",
                "passworddd",
                0);
        memberRepository.save(member2);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .title("타이틀")
                .build();
        productRepository.save(product1);

        Product product2 = Product.builder()
                .category("카테고리")
                .detail("설명2")
                .reviewCnt(0)
                .price(40000)
                .title("타이틀2")
                .build();
        productRepository.save(product2);

        Product product3 = Product.builder()
                .category("카테고리2")
                .detail("설명3")
                .reviewCnt(0)
                .price(50000)
                .title("타이틀3")
                .build();
        productRepository.save(product3);

        List<Img> imgList1 = new ArrayList<>();
        Img img1 = new Img("test1",product1);
        imgList1.add(img1);
        imgRepository.save(img1);

        List<Img> imgList2 = new ArrayList<>();
        Img img2 = new Img("test2",product2);
        imgList2.add(img2);
        imgRepository.save(img2);

        List<Img> imgList3 = new ArrayList<>();
        Img img3 = new Img("test3",product3);
        imgList3.add(img3);
        imgRepository.save(img3);

        CartRequestDto.Add dto = new CartRequestDto.Add("옵션",20000,3);
        CartRequestDto.Add dto1 = new CartRequestDto.Add("옵션1",30000,1);
        CartRequestDto.Add dto2 = new CartRequestDto.Add("옵션2",40000,2);
        CartRequestDto.Add dto3 = new CartRequestDto.Add("옵션3",60000,4);

        //when
        String msg = cartService.cartAdd(member1.getNickname(),product1.getId(), dto);
        String msg1 = cartService.cartAdd(member1.getNickname(),product2.getId(), dto1);
        String msg2 = cartService.cartAdd(member2.getNickname(),product1.getId(), dto2);
        String msg3 = cartService.cartAdd(member2.getNickname(),product3.getId(), dto3);

        //then
        assertEquals(msg,"msg : 담기 완료");
        assertEquals(msg1,"msg : 담기 완료");
        assertEquals(msg2,"msg : 담기 완료");
        assertEquals(msg3,"msg : 담기 완료");
    }

    @Test
    public void 장바구니_담기_실패_id불일치() throws Exception{
        //given
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100000);
        memberRepository.save(member1);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .title("타이틀")
                .build();
        productRepository.save(product1);

        List<Img> imgList1 = new ArrayList<>();
        Img img1 = new Img("test1",product1);
        imgList1.add(img1);
        imgRepository.save(img1);
        CartRequestDto.Add dto = new CartRequestDto.Add("옵션",20000,3);
        //when
        try{
            cartService.cartAdd("없는아이디",product1.getId(), dto);
        }catch (IllegalArgumentException e){
            return;
        }

        //then
        fail("실패의 실패");
    }

    @Test
    public void 장바구니_목록_조회성공() throws Exception{
        //given
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100000);
        memberRepository.save(member1);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .title("타이틀")
                .build();
        productRepository.save(product1);

        List<Img> imgList1 = new ArrayList<>();
        Img img1 = new Img(
                "test1",
                product1
        );
        imgRepository.save(img1);
        imgList1.add(img1);

        Cart cart = Cart.builder()
                .ea(2)
                .bill(40000)
                .option("라지")
                .member(member1)
                .product(product1)
                .build();
        cartRepository.save(cart);

        Cart cart2 = Cart.builder()
                .ea(3)
                .bill(60000)
                .option("아주라지")
                .member(member1)
                .product(product1)
                .build();
        cartRepository.save(cart2);

        List<Cart> cartLists2 = new ArrayList<>();
        cartLists2.add(cart);
        cartLists2.add(cart2);

        CartRequestDto.Add dto = new CartRequestDto.Add("라지",20000,2);
        CartRequestDto.Add dto1 = new CartRequestDto.Add("아주라지",20000,3);

        //when
        cartService.cartAdd(member1.getNickname(),product1.getId(), dto);
        cartService.cartAdd(member1.getNickname(),product1.getId(), dto1);

        List<CartResponseDto.List> cartLists = cartService.cartLists(member1.getNickname());


        //then
        for(int i=0;i<cartLists2.size();i++){
            assertEquals(cartLists.get(i).getProductId(),cartLists2.get(i).getProduct().getId());
            assertEquals(cartLists.get(i).getEa(),cartLists2.get(i).getEa());
            assertEquals(cartLists.get(i).getBill(),cartLists2.get(i).getBill());
            assertEquals(cartLists.get(i).getOptionContent(),cartLists2.get(i).getOptionContent());
        }

    }

    @Test
    public void 장바구니_목록_조회실패() throws Exception{
        //given

        //when

        //then
    }


    public void inputData() {

        //이렇게 하는게 맞는지 찾아볼것.
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100000);
        memberRepository.save(member1);

        Member member2 = new Member(
                "john310@gmail.com",
                "정요한2",
                "우리집2",
                "passworddd",
                0);
        memberRepository.save(member2);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .title("타이틀")
                .build();
        productRepository.save(product1);

        Product product2 = Product.builder()
                .category("카테고리")
                .detail("설명2")
                .reviewCnt(0)
                .price(40000)
                .title("타이틀2")
                .build();
        productRepository.save(product2);

        Product product3 = Product.builder()
                .category("카테고리2")
                .detail("설명3")
                .reviewCnt(0)
                .price(50000)
                .title("타이틀3")
                .build();
        productRepository.save(product3);

        List<Img> imgList1 = new ArrayList<>();
        Img img1 = new Img("test1",product1);
        imgList1.add(img1);
        imgRepository.save(img1);

        List<Img> imgList2 = new ArrayList<>();
        Img img2 = new Img("test2",product2);
        imgList2.add(img2);
        imgRepository.save(img2);

        List<Img> imgList3 = new ArrayList<>();
        Img img3 = new Img("test3",product3);
        imgList3.add(img3);
        imgRepository.save(img3);

        Cart cart = Cart.builder()
                .ea(2)
                .bill(40000)
                .option("라지")
                .member(member1)
                .product(product1)
                .build();
        cartRepository.save(cart);


        Cart cart2 = Cart.builder()
                .ea(3)
                .bill(50000)
                .option("아주라지")
                .member(member1)
                .product(product1)
                .build();
        cartRepository.save(cart2);

    }
}

