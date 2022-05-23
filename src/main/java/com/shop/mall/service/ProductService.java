package com.shop.mall.service;

import com.shop.mall.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    public Page<ProductResponseDto.productList> productList(Pageable pageable);

    public ProductResponseDto.productsDetail productsDetail(Long productId);
}
