package com.shop.mall.controller;


import com.shop.mall.domain.Img;
import com.shop.mall.domain.Product;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.repository.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DummyController {
    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;

    @PostMapping("/dummy")
    public String dummyMake(){
        for(int i = 0 ; i<10000;i++){
            Product product = Product.builder()
                    .reviewCnt(0)
                    .price(100)
                    .detail("asdf")
                    .category("aa")
                    .title("dummy")
                    .build();

            List<Img> imgList = new ArrayList<>();
            Img img = new Img("123",product);
            imgList.add(img);

            productRepository.save(product);
            imgRepository.save(img);
        }
        return "더미생성 완료";
    }
}
