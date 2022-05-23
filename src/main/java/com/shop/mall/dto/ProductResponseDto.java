package com.shop.mall.dto;

import com.shop.mall.domain.Img;
import com.shop.mall.domain.Product;
import com.shop.mall.domain.ProductOption;
import com.shop.mall.domain.Review;
import lombok.*;

import java.util.List;

public class ProductResponseDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class productList {
        private Long productId;
        private String title;
        private String category;
        private int reviewCnt;
        private String summary;
        private int price;
        private String firstImg;

        public static productList productListFrom(Product product){
            return productList.builder()
                    .productId(product.getId())
                    .title(product.getTitle())
                    .category(product.getCategory())
                    .reviewCnt(product.getReviewCnt())
                    .summary(product.getDetail().substring(1))
                    .price(product.getPrice())
                    .firstImg(product.getImgList().get(0).getImgUrl())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class productsDetail{
        private Long productId;
        private String title;
        private String category;
        private int reviewCnt;
        private String detail;
        private int price;
        private List<ProductOption> optionList;
        private List<Img> imgList;
        private List<Review> reviewList;

        public static productsDetail productsDetailFrom(Product product){
            return productsDetail.builder()
                    .productId(product.getId())
                    .title(product.getTitle())
                    .category(product.getCategory())
                    .reviewCnt(product.getReviewCnt())
                    .detail(product.getDetail().substring(1))
                    .price(product.getPrice())
                    .optionList(product.getProductOptionList())
                    .imgList(product.getImgList())
                    .reviewList(product.getReviewList())
                    .build();
        }
    }
}
