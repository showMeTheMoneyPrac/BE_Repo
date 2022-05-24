package com.shop.mall.service;

import com.shop.mall.domain.*;
import com.shop.mall.dto.ProductResponseDto;
import com.shop.mall.repository.ImgRepository;
import com.shop.mall.repository.Product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
        //assertEquals(3,productPages.getTotalPages());
        assertTrue(productPages.isFirst());
        assertTrue(productPages.hasNext());
    }

    @Test
    @DisplayName("9번 API 상품 상세보기")
    public void productsDetail() throws Exception {
        //given
        Member member = new Member("seanlee0701@naver.com","seanlee0701","중구","password",1000);

        List<Img> imgList = new ArrayList<>();
        Img img = new Img("first_img_url");
        imgList.add(img);

        List<ProductOption> productOptionList = new ArrayList<>();
        ProductOption productOption = new ProductOption("first_product_option");
        productOptionList.add(productOption);

        List<Review> reviewList = new ArrayList<>();
        Review review = new Review(member);
        reviewList.add(review);

        Long productId = productRepository.save(new Product(imgList,productOptionList,reviewList)).getId();

        //when
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다"));
        ProductResponseDto.ProductsDetail productsDetail = ProductResponseDto.ProductsDetail.productsDetailFrom(product);


        //then
        assertEquals(productId,productsDetail.getProductId());
        assertEquals(productOptionList.get(0).getOptionContent(),productsDetail.getOptionList().get(0));
        assertEquals(imgList.get(0).getImgUrl(),productsDetail.getImgList().get(0));
        assertEquals(reviewList.size(),productsDetail.getReviewList().size());

    }

    @Test
    @DisplayName("10번 API searchProduct_제약조건 없음")
    public void searchProduct_전체보기() throws Exception {
        //given
        for (int i=0;i<15;i++){
            List<Img> imgList = new ArrayList<>();
            Img img = new Img("img_url"+i);
            imgList.add(img);
            productRepository.save(new Product(imgList));
        }

        //when
        PageRequest pageable= PageRequest.of(0,8);
        Page<ProductResponseDto.ProductList> productPages = productRepository.searchByRecent(pageable,null,null);

        //then
        List<ProductResponseDto.ProductList> content = productPages.getContent();

        assertEquals(8, content.size());
        assertEquals(0, productPages.getNumber());
        //assertEquals(3,productPages.getTotalPages());
        assertTrue(productPages.isFirst());
        assertTrue(productPages.hasNext());
    }


    @Test
    @DisplayName("10번 API searchProduct_제약조건_price순")
    public void searchProduct_제약조건_price순() throws Exception {
        //given
        for (int i=0;i<15;i++){
            List<Img> imgList = new ArrayList<>();
            Img img = new Img("img_url"+i);
            imgList.add(img);
            productRepository.save(new Product(imgList));
        }

        //when
        PageRequest pageable= PageRequest.of(0,8);
        Page<ProductResponseDto.ProductList> productPages = productRepository.searchByCost(pageable,null,null);

        //then
        List<ProductResponseDto.ProductList> content = productPages.getContent();

        assertEquals(8, content.size());
        assertEquals(0, productPages.getNumber());
        //assertEquals(3,productPages.getTotalPages());
        assertTrue(productPages.isFirst());
        assertTrue(productPages.hasNext());
    }

    @Test
    @DisplayName("10번 API searchProduct_제약조건_검색어")
    public void searchProduct_제약조건_검색어() throws Exception {
        //given
        for (int i=0;i<15;i++){
            List<Img> imgList = new ArrayList<>();
            Img img = new Img("img_url"+i);
            imgList.add(img);
            productRepository.save(new Product(imgList));
        }

        //when
        PageRequest pageable= PageRequest.of(0,8);
        Page<ProductResponseDto.ProductList> productPages = productRepository.searchByRecent(pageable,null,"무지");

        //then
        List<ProductResponseDto.ProductList> content = productPages.getContent();

        assertEquals(8, content.size());
        assertEquals(0, productPages.getNumber());
        //assertEquals(3,productPages.getTotalPages());
        assertTrue(productPages.isFirst());
        assertTrue(productPages.hasNext());
    }




}