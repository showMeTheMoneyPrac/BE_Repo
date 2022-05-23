package com.shop.mall.controller;

import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    //8번 API 메인 페이지 상품 데이터(pageable)
    @GetMapping("/products")
    public List<ProductResponseDto.ProductList> findAllProduct(@PageableDefault(size = 8) Pageable pageable) {
        return productService.productList(pageable).getContent();
    }

    //9번 API 상품 상세 데이터
    @GetMapping("/products/{productId}")
    public ProductResponseDto.ProductsDetail productDetail(@PathVariable Long productId){
        return productService.productsDetail(productId);
    }

    //10번 API 상품 카테고리 및 검색
//    @GetMapping("/products")
//    public List<ProductResponseDto.ProductList> findConditionsProduct(@PageableDefault(size = 8, sort = "createdAt") Pageable pageable, @RequestParam(value = "sort") String sort, @RequestParam(value = "category") String category, @RequestParam(value = "searchKeyword") String searchKeyword){
//        return productService.conditionProductList(pageable,)
//    }

}