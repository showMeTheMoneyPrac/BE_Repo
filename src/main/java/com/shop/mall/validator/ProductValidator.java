package com.shop.mall.validator;

import com.shop.mall.domain.Product;
import com.shop.mall.exception.ErrorCodeException;
import com.shop.mall.repository.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shop.mall.exception.ErrorCode.PRODUCT_NOT_EXIST;

@Component // 선언하지 않으면 사용할 수 없다!!!!!
@RequiredArgsConstructor
public class ProductValidator {
    private final ProductRepository productRepository;

    public Product authorization(Long productId){
        return productRepository.findById(productId).orElseThrow(
                ()->new ErrorCodeException(PRODUCT_NOT_EXIST)
        );
    }
}
