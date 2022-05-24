package com.shop.mall.repository.Product;

import com.shop.mall.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<ProductResponseDto.ProductList> searchByRecent(Pageable pageable, String category, String searchKeyword);
    Page<ProductResponseDto.ProductList> searchByCost(Pageable pageable,String category,String searchKeyword);
    Page<ProductResponseDto.ProductList> searchByReviewCnt(Pageable pageable,String category,String searchKeyword);
}
