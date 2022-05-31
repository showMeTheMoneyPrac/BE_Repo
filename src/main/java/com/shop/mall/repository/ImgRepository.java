package com.shop.mall.repository;

import com.shop.mall.domain.Img;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<Img, Long> {
    List<Img> findAllByProduct_Id(Long productId);
    Img findByProduct_Id(Long productId);

    Img findFirstByProductId(Long productId);
}
