package com.shop.mall.service;

import com.shop.mall.domain.*;
import com.shop.mall.dto.OrdersRequestDto;
import com.shop.mall.dto.OrdersResponseDto;
import com.shop.mall.repository.Cart.CartRepository;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.repository.MemberRepository;
import com.shop.mall.repository.OrdersDetailRepository;
import com.shop.mall.repository.OrdersRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrdersServiceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ImgRepository imgRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrdersDetailRepository ordersDetailRepository;


    @Test
    public void 구매목록_리스트_반환_성공() {
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
                .price(500)
                .firstImg("first_img")
                .title("타이틀")
                .build();
        productRepository.save(product1);

        List<Img> imgList1 = new ArrayList<>();
        Img img1 = new Img("test1",product1);
        imgList1.add(img1);
        imgRepository.save(img1);

        //when
        //시나리오 : 한번 주문에 2가지 detail이 있다
        Orders orders = new Orders("주소",3000,member1);
        ordersRepository.save(orders);

        OrdersDetail ordersDetail1 = new OrdersDetail("option_content",2,1000,orders,product1);
        OrdersDetail ordersDetail2 = new OrdersDetail("option_content",4,2000,orders,product1);
        ordersDetailRepository.save(ordersDetail1);
        ordersDetailRepository.save(ordersDetail2);

        ordersDetail1.setOrders(orders);
        ordersDetail2.setOrders(orders);


        OrdersResponseDto.ordersTotalList ordersTotalList = ordersService.findAllOrders("정요한1");
        int bill = ordersTotalList.getOrdersList().get(0).getTotalPrice();

        //then
        assertEquals(ordersTotalList.getOrdersList().get(0).getOrdersId(),orders.getId());
        assertEquals(bill,3000);

    }

    @Test
    public void orderProductList_성공() {
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
                .firstImg("first_img")
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

        Orders orders = new Orders("주소",1000,member1);
        ordersRepository.save(orders);

        OrdersDetail ordersDetail = new OrdersDetail("option_content",3,1000,orders,product1);
        ordersDetailRepository.save(ordersDetail);
        List<Long> list = new ArrayList<>();
        list.add(cart.getId());
        list.add(cart2.getId());
        OrdersRequestDto.orderProductList orderProductList = new OrdersRequestDto.orderProductList(list,10000,"address");

        //when
        String ordersTotalList = ordersService.orderProductList("정요한1",orderProductList);

        //then
        assertEquals(ordersTotalList,"msg : 구매 리스트 성공");
    }


    @Test
    public void orderProductList_잔액_부족() {
        //given
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100);
        memberRepository.save(member1);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .firstImg("first_img")
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

        Orders orders = new Orders("주소",1000,member1);
        ordersRepository.save(orders);

        OrdersDetail ordersDetail = new OrdersDetail("option_content",3,1000,orders,product1);
        ordersDetailRepository.save(ordersDetail);
        List<Long> list = new ArrayList<>();
        list.add(cart.getId());
        list.add(cart2.getId());
        OrdersRequestDto.orderProductList orderProductList = new OrdersRequestDto.orderProductList(list,10000,"address");

        //when
        String ordersTotalList = ordersService.orderProductList("정요한1",orderProductList);

        //then
        assertEquals(ordersTotalList,"msg: 잔액이 부족합니다");
    }



    @Test
    public void orderProduct_성공() {
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
                .firstImg("first_img")
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

        Orders orders = new Orders("주소",1000,member1);
        ordersRepository.save(orders);

        OrdersDetail ordersDetail = new OrdersDetail("option_content",3,1000,orders,product1);
        ordersDetailRepository.save(ordersDetail);

        OrdersRequestDto.orderProduct orderProduct = new OrdersRequestDto.orderProduct(3,1000,"optionContent","address");

        //when
        String ordersTotalList = ordersService.orderProduct("정요한1",product1.getId(),orderProduct);

        //then
        assertEquals(ordersTotalList,"msg : 즉시 구매 성공");
    }


    @Test
    public void orderProduct_실패() {
        //given
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100);
        memberRepository.save(member1);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .firstImg("first_img")
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

        Orders orders = new Orders("주소",1000,member1);
        ordersRepository.save(orders);

        OrdersDetail ordersDetail = new OrdersDetail("option_content",3,1000,orders,product1);
        ordersDetailRepository.save(ordersDetail);

        OrdersRequestDto.orderProduct orderProduct = new OrdersRequestDto.orderProduct(3,1000,"optionContent","address");

        //when
        String ordersTotalList = ordersService.orderProduct("정요한1",product1.getId(),orderProduct);

        //then
        assertEquals(ordersTotalList,"msg: 잔액이 부족합니다");
    }
    @Test
    public void deleteOrders_성공() {
        //given
        Member member1 = new Member(
                "john3210@gmail.com",
                "정요한1",
                "우리집1",
                "passworddd",
                100);
        memberRepository.save(member1);

        Product product1 = Product.builder()
                .category("카테고리")
                .detail("설명1")
                .reviewCnt(0)
                .price(20000)
                .firstImg("first_img")
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

        Orders orders = new Orders("주소",1000,member1);
        ordersRepository.save(orders);

        OrdersDetail ordersDetail = new OrdersDetail("option_content",3,1000,orders,product1);
        OrdersDetail ordersDetail2 = new OrdersDetail("option_content",3,1000,orders,product1);
        Long ordersDetailId = ordersDetailRepository.save(ordersDetail).getId();
        Long ordersDetailId2 = ordersDetailRepository.save(ordersDetail2).getId();

        //when
        String ordersTotalList = ordersService.deleteOrders("정요한1", String.valueOf(ordersDetailId));

        //then
        assertEquals(ordersTotalList,"msg : 환불 완료");
    }
}