package com.shop.mall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.mall.domain.Img;
import com.shop.mall.domain.Product;
import com.shop.mall.domain.ProductOption;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.shop.mall.dto.ReviewResponseDto.ReviewList.reviewListFrom;

public class ProductResponseDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductList {
        @JsonProperty(value="productId")
        private Long id;

        private String title;
        private String category;
        private int reviewCnt;

        @JsonProperty(value = "summary")
        private String detail;

        private int price;

        @JsonProperty(value = "firstImg")
        private String firstImg;

        public static ProductList productListFrom(Product product) {
            return ProductList.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .category(product.getCategory())
                    .reviewCnt(product.getReviewCnt())
                    .detail(product.getDetail())
                    .price(product.getPrice())
                    .firstImg(product.getImgList().get(0).getImgUrl())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class ProductsDetail {
        private Long productId;
        private String title;
        private String category;
        private int reviewCnt;
        private String detail;
        private int price;
        private String firstImg;
        private List<String> optionList;
        private List<String> imgList;
        private List<ReviewResponseDto.ReviewList> reviewList;

        public static ProductsDetail productsDetailFrom(Product product) {
            List<String> optionList = new ArrayList<>();
            List<String> imgList = new ArrayList<>();
            List<ReviewResponseDto.ReviewList> reviewList = new ArrayList<>();

//            product.getProductOptionList()
//                    .stream()
//                    .map(ProductOption::getOptionContent)
//                    .forEach(optionList::add);

            for (int i=0; i<product.getProductOptionList().size();i++){
                optionList.add(product.getProductOptionList().get(i).getOptionContent());
            }

//            product.getImgList()
//                    .stream()
//                    .map(Img::getImgUrl)
//                    .forEach(imgList::add);

            for(int i=0; i<product.getImgList().size();i++){
                imgList.add(product.getImgList().get(i).getImgUrl());
            }

            for (int i=0; i<product.getReviewList().size(); i++){
                reviewList.add(reviewListFrom(product.getReviewList().get(i)));
            }


            return ProductsDetail.builder()
                    .productId(product.getId())
                    .title(product.getTitle())
                    .category(product.getCategory())
                    .reviewCnt(product.getReviewCnt())
                    .detail(product.getDetail())
                    .price(product.getPrice())
                    .firstImg(product.getFirstImg())
                    .optionList(optionList)
                    .imgList(imgList)
                    .reviewList(reviewList)
                    .build();
        }
    }

}
