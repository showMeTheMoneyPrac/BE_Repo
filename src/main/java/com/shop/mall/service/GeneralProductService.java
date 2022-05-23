package com.shop.mall.service;


import com.shop.mall.domain.Product;
import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GeneralProductService implements ProductService{

    private final ProductRepository productRepository;

    // 8번 API
    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto.productList> productList(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseDto.productList::productListFrom);
    }

    //9번 API
    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto.productsDetail productsDetail(Long productId) {
        return productRepository.findById(productId).map(ProductResponseDto.productsDetail::productsDetailFrom).orElseThrow(()-> new IllegalArgumentException("해당 상품은 존재하지 않습니다."));
    }

}
