package com.shop.mall.service;

import com.shop.mall.domain.*;
import com.shop.mall.dto.OrdersDetailResponseDto;
import com.shop.mall.dto.OrdersRequestDto;
import com.shop.mall.dto.OrdersResponseDto;
import com.shop.mall.repository.CartRepository;
import com.shop.mall.repository.OrdersDetailRepository;
import com.shop.mall.repository.OrdersRepository;
import com.shop.mall.repository.ReviewRepository;
import com.shop.mall.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ReviewRepository reviewRepository;
    private final CartRepository cartRepository;
    private final OrdersDetailRepository ordersDetailRepository;
    private final MemberValidator memberValidator;

    //15번 API
    public List<OrdersResponseDto.ordersList> findAllOrders(String nickname) {
        Long memberId = memberValidator.authorization(nickname).getId();

        List<Orders> orders = ordersRepository.findAllByMemberId(memberId);

        List<OrdersResponseDto.ordersList> ordersLists = new ArrayList<>();

        for (Orders order : orders) {
            List<OrdersDetailResponseDto.ordersDetailList> ordersDetailLists = new ArrayList<>();
            List<OrdersDetail> ordersDetail = order.getOrdersDetailList();
            for (OrdersDetail detail : ordersDetail) {
                Product product = detail.getProduct();
                Review review = reviewRepository.findByMemberIdAndProductId(memberId, product.getId());

                OrdersDetailResponseDto.ordersDetailList ordersDetailList = OrdersDetailResponseDto.ordersDetailList.builder()
                        .orderDetailId(detail.getId())
                        .productId(product.getId())
                        .title(product.getTitle())
                        .category(product.getCategory())
                        .firstImg(product.getImgList().get(0).getImgUrl())
                        .optionContent(detail.getOptionContent())
                        .price(product.getPrice())
                        .bill(detail.getBill())
                        .ea(detail.getEa())
                        .review(review)
                        .build();

                ordersDetailLists.add(ordersDetailList);
            }

            OrdersResponseDto.ordersList ordersList = OrdersResponseDto.ordersList.builder()
                    .ordersId(order.getId())
                    .address(order.getAddress())
                    .totalPrice(order.getTotalPrice())
                    .createdAt(order.getCreatedAt())
                    .ordersDetailList(ordersDetailLists)
                    .build();
            ordersLists.add(ordersList);
        }

        return ordersLists;
    }

    //16번 API
    @Transactional
    public String orderProduct(String nickname, OrdersRequestDto.orderProduct dto) {
        Member member = memberValidator.authorization(nickname);
        Orders orders = Orders.builder()
                .member(member)
                .address(dto.getAddress())
                .totalPrice(dto.getTotalPrice())
                .build();

        for (int i = 0; i < dto.getCartIdList().size(); i++) {
            Cart cart = cartRepository.findById(dto.getCartIdList().get(i)).orElseThrow(() -> new IllegalArgumentException("카트 상품이 존재하지 않습니다"));
            OrdersDetail ordersDetail = OrdersDetail.builder()
                    .product(cart.getProduct())
                    .ea(cart.getEa())
                    .bill(cart.getBill())
                    .optionContent(cart.getOptionContent())
                    .orders(orders)
                    .build();

            ordersDetailRepository.save(ordersDetail);
            cartRepository.deleteById(dto.getCartIdList().get(i));
        }

        ordersRepository.save(orders);

        return "msg : 구매 리스트 성공";
    }

    //17번 API


}
