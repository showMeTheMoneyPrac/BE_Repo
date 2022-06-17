package com.shop.mall.repository.Product;

import com.shop.mall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>,ProductRepositoryCustom {
}
