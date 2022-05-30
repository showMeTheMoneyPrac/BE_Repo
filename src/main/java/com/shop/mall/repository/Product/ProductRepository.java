package com.shop.mall.repository.Product;

import com.shop.mall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,ProductRepositoryCustom {
    @Query(nativeQuery = true, value = "select * from product pd where pd.product_id > :lastId ORDER BY pd.product_id asc limit 20")
    List<Product> findAllByLastId(Long lastId);

//    @Query(nativeQuery = true, value = "select count(pd.product_id) from product pd")
//    int findProductCount();
}
