package com.shop.mall.repository.Product;

import com.shop.mall.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductResponseDto.ProductList> searchByRecent(Long lastId, String category, String searchKeyword);
    //List<ProductResponseDto.ProductList> searchByCost(Long lastId, String category, String searchKeyword);
    //List<ProductResponseDto.ProductList> searchByReviewCnt(Long lastId,String category,String searchKeyword);
}
