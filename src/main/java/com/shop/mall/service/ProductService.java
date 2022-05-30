package com.shop.mall.service;

import com.shop.mall.domain.Product;
import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.exception.ErrorCode;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.Product.ProductRepository;
import com.shop.mall.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.shop.mall.dto.ProductResponseDto.ProductList.productListFrom;
import static com.shop.mall.exception.ErrorCode.PRODUCT_NOT_EXIST;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    // 8번 API
    public List<ProductResponseDto.ProductList> productList(Long lastId) {
        List<Product> listProducts = productRepository.findAllByLastId(lastId);
        List<ProductResponseDto.ProductList> productsList = new ArrayList<>();
        for (Product listProduct : listProducts) {
            productsList.add(productListFrom(listProduct));
        }

        return productsList;
    }


    //9번 API
    public ProductResponseDto.ProductsDetail productsDetail(Long productId) {
        Product product = productValidator.authorization(productId);
        return ProductResponseDto.ProductsDetail.productsDetailFrom(product);
    }


    //10번 API
    public List<ProductResponseDto.ProductList> conditionProductList(Long lastId, String sort, String category, String searchKeyword){
        List<ProductResponseDto.ProductList> productLists = null;
        switch (sort){
            case "cost":
                //productLists = productRepository.searchByCost(lastId,category,searchKeyword);
                break;

            case "review":
                //productLists = productRepository.searchByReviewCnt(lastId,category,searchKeyword);
                break;

            default:
                productLists = productRepository.searchByRecent(lastId,category,searchKeyword);
                break;
        }

        return productLists;
    }





}
