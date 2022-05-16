package com.shop.mall.repository;

import com.shop.mall.domain.Member2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    //회원 가입 시 저장
    public void save(Member2 member){
        em.persist(member);
    }

    //회원을 PK값으로 검색하는 로직
    public Member2 findOne(Long id){
        return em.find(Member2.class, id);
    }


    //회원의 존재 유무를 판단하기 위한 이메일 검증
    public Member2 findByEmail(String email){
        try {
            return em.createQuery("select m from Member2 m where m.email =: email", Member2.class)
                    .setParameter("email",email)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


    //회원의 닉네임 중복 확인 및 사용자 정보 확인
    public Member2 findByNickname(String nickname){
        try {
            return em.createQuery("select m from Member2 m where m.nickname =:nickname", Member2.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    //충전 금액 저장
    public void chargeCash(Member2 member){
        em.createQuery("update Member2 m set m.cash =:cash where m.nickname=:nickname")
                .setParameter("cash",member.getCash())
                .setParameter("nickname",member.getNickname());
    }







}
