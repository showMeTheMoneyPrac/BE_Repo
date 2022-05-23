package com.shop.mall.controller;

import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductResponseDto.productList> findAllProduct(@PageableDefault(size = 8) Pageable pageable) {
        return productService.productList(pageable).getContent();
    }

    @GetMapping("/products/{productId}")
    public ProductResponseDto.productsDetail ProductDetail(@PathVariable Long productId){
        return productService.productsDetail(productId);
    }

}