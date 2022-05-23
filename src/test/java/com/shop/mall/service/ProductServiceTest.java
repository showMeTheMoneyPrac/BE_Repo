package com.shop.mall.service;

import com.shop.mall.domain.Img;
import com.shop.mall.domain.Product;
import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;
    @Autowired ImgRepository imgRepository;

    @Test
    @DisplayName("8번 API 첫번째 페이징 성공")
    public void productFirstList() throws Exception {
        //given
        for (int i=0;i<15;i++){
            List<Img> imgList = new ArrayList<>();
            Img img = new Img("img_url"+i);
            imgList.add(img);
            productRepository.save(new Product(imgList));
        }

        //when
        PageRequest pageable= PageRequest.of(0,8, Sort.Direction.DESC, "id");
        Page<ProductResponseDto.ProductList> productPages = productRepository.findAll(pageable).map(ProductResponseDto.ProductList::productListFrom);
        
        //then
        List<ProductResponseDto.ProductList> content = productPages.getContent();

        assertEquals(8, content.size());
        assertEquals(0, productPages.getNumber());
        assertEquals(2,productPages.getTotalPages());
        assertTrue(productPages.isFirst());
        assertTrue(productPages.hasNext());
    }

    @Test
    void productsDetail() {
    }
}