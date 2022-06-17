package com.shop.mall.service;

import com.shop.mall.domain.Cart;
import com.shop.mall.domain.Img;
import com.shop.mall.domain.Member;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.CartRequestDto;
import com.shop.mall.dto.CartResponseDto;
import com.shop.mall.exception.ErrorCode;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.Cart.CartRepository;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    public void 장바구니_담기_성공(){
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
        assertEquals(msg,"장바구니 담기 성공");
        assertEquals(msg1,"장바구니 담기 성공");
        assertEquals(msg2,"장바구니 담기 성공");
        assertEquals(msg3,"장바구니 담기 성공");
    }

    @Test
    public void 장바구니_담기_실패_id불일치(){
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
        }catch (ErrorCodeException e){
            assertEquals(e.getErrorCode(), ErrorCode.MEMBER_NOT_EXIST);
            return;
        }

        //then
        fail("실패의 실패");
    }

    @Test
    public void 장바구니_목록_조회성공(){
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
    public void 장바구니_리스트_삭제(){
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

        Cart cart = new Cart("option",3,1000,member1,product1);
        Cart cart2 = new Cart("option",3,1000,member1,product1);
        cartRepository.save(cart);
        cartRepository.save(cart2);
        Long cartAddId = cart.getId();
        Long cartAddId2 = cart2.getId();
        String cartAddIdToString = cartAddId.toString();
        String cartAddId2ToString = cartAddId2.toString();


        //when
        cartService.cartDelete("정요한1",cartAddIdToString+","+cartAddId2ToString);

    }

    @Test
    public void 장바구니_수량_수정(){
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

        Cart cart = new Cart("option",3,1000,member1,product1);
        cartRepository.save(cart);
        Long cartAddId = cart.getId();
        CartRequestDto.Ea ea = new CartRequestDto.Ea(cartAddId,3);


        //when
        Cart modifyingEa = cartService.modifyingEa(member1.getNickname(),ea);

        //then
        assertEquals(modifyingEa.getEa(),ea.getEa());

    }
}

