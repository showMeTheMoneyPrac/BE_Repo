package com.shop.mall.repository;

import com.shop.mall.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    boolean existsByMemberIdAndProductId(Long memberId,Long productId);

    boolean existsByIdAndMemberId(Long reviewId, Long memberId);

    Review findByMemberIdAndProductId(Long memberId,Long productId);
}
