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
    public void save(Member2 member) {
        em.persist(member);
    }

    //PK로 찾기
    public Member2 findOne(Long id){
        return em.find(Member2.class,id);
    }


    //email을 통해 Member Entity를 가져오기 위한 설정
    public Member2 findByEmail(String email) {
        try {
            return em.createQuery("select m from Member2 m where m.email =: email", Member2.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    //닉네임을 통해 Member Entity를 가져오기 위한 설정
    public Member2 findByNickname(String nickname) {
        return em.createQuery("select m from Member2 m where m.nickname =:nickname", Member2.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    //충전 금액 저장
    public void chargeCash(Member2 member) {
        em.createQuery("update Member2 m set m.cash =:cash where m.nickname=:nickname")
                .setParameter("cash", member.getCash())
                .setParameter("nickname", member.getNickname());
    }

    //주소 변경
    public void changeAddress(String nickname, String address){
        em.createQuery("update Member2 m set m.address=:address where m.nickname=:nickname")
                .setParameter("address", address)
                .setParameter("nickname", nickname);
    }

    //닉네임 변경
    public void changeNickname(String nickname, String afterNickname){
        em.createQuery("update Member2 m set m.nickname=:afterNickname where m.nickname=:nickname")
                .setParameter("afterNickname", afterNickname)
                .setParameter("nickname", nickname);
    }
}
