package com.shop.mall.service;

import com.shop.mall.domain.Product;
import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    // 8번 API
    public Page<ProductResponseDto.ProductList> productList(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseDto.ProductList::productListFrom);
    }


    //9번 API
    public ProductResponseDto.ProductsDetail productsDetail(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다"));
        ProductResponseDto.ProductsDetail productsDetail = ProductResponseDto.ProductsDetail.productsDetailFrom(product);
        return productsDetail;
    }


}
