package com.shop.mall.repository.Product;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.mall.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


import static com.shop.mall.domain.QImg.img;
import static com.shop.mall.domain.QProduct.product;
import static org.aspectj.util.LangUtil.isEmpty;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductResponseDto.ProductList> searchByRecent(Long lastId, String category, String searchKeyword) {
        return getSearchByRecent(lastId, category, searchKeyword);
        //return null;
    }

//    @Override
//    public List<ProductResponseDto.ProductList> searchByCost(Long lastId, String category, String searchKeyword) {
//        return getSearchByCost(lastId, category, searchKeyword);
//    }

//    @Override
//    public List<ProductResponseDto.ProductList> searchByReviewCnt(Long lastId, String category, String searchKeyword) {
//        return getSearchByReview(lastId,category,searchKeyword);
//    }

    //searchByRecent
    private List<ProductResponseDto.ProductList> getSearchByRecent(Long lastId, String category, String searchKeyword) {
        //List<ProductResponseDto.ProductList> productLists =
        return jpaQueryFactory
                .select(Projections.bean(ProductResponseDto.ProductList.class, product.id, product.title, product.category, product.reviewCnt, product.detail, product.price, img.imgUrl))
                .from(product)
                .innerJoin(img)
                .on(product.id.eq(img.product.id))
                .groupBy(img.product.id)
                .where(
                        booleanSearchKeyword(searchKeyword),
                        booleanCategory(category),
                        ltProductId(lastId)
                )
                .orderBy(product.id.desc())
                .limit(20)
                .fetch();


//        Long count = jpaQueryFactory
//                .select(product.count())
//                .from(product)
//                .where(
//                        booleanSearchKeyword(searchKeyword),
//                        booleanCategory(category)
//                )
//                .fetchOne();
//
//        System.out.println("count" + count);
//        return new PageImpl<>(productLists,pageable,count);
    }

    //searchByCost
//    private Page<ProductResponseDto.ProductList> getSearchByCost(Long lastId, String category, String searchKeyword) {
//        List<ProductResponseDto.ProductList> productLists = jpaQueryFactory
//                .select(Projections.bean(ProductResponseDto.ProductList.class, product.id, product.title, product.category, product.reviewCnt, product.detail, product.price, img.imgUrl))
//                .from(product)
//                .innerJoin(img)
//                .on(product.id.eq(img.product.id))
//                .groupBy(img.product.id)
//                .where(
//                        booleanSearchKeyword(searchKeyword),
//                        booleanCategory(category)
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(product.price.desc())
//                .fetch();
//
//        Long count = jpaQueryFactory
//                .select(product.count())
//                .from(product)
//                .where(
//                        booleanSearchKeyword(searchKeyword),
//                        booleanCategory(category)
//                )
//                .fetchOne();
//        return new PageImpl<>(productLists,pageable,count);
//    }
//
//    //searchByReviewCnt
//    private Page<ProductResponseDto.ProductList> getSearchByReview(Pageable pageable, String category, String searchKeyword) {
//        List<ProductResponseDto.ProductList> productLists = jpaQueryFactory
//                .select(Projections.bean(ProductResponseDto.ProductList.class, product.id, product.title, product.category, product.reviewCnt, product.detail, product.price, img.imgUrl))
//                .from(product)
//                .innerJoin(img)
//                .on(product.id.eq(img.product.id))
//                .groupBy(img.product.id)
//                .where(
//                        booleanSearchKeyword(searchKeyword),
//                        booleanCategory(category)
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(product.reviewCnt.desc())
//                .fetch();
//
//        Long count = jpaQueryFactory
//                .select(product.count())
//                .from(product)
//                .where(
//                        booleanSearchKeyword(searchKeyword),
//                        booleanCategory(category)
//                )
//                .fetchOne();
//
//
//        return new PageImpl<>(productLists,pageable,count);
//    }

    //동적 쿼리를 위한 문들
    private BooleanExpression booleanSearchKeyword(String searchTitle) {
        return isEmpty(searchTitle) ? null : product.title.contains(searchTitle);
    }

    private BooleanExpression booleanCategory(String category) {
        return isEmpty(category) ? null : product.category.eq(category);
    }

    private BooleanExpression ltProductId(Long productId) {
        if (productId == null) {
            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
        }

        return product.id.lt(productId);
    }


}
