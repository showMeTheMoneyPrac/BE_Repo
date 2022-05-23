//package com.shop.mall.service;
//
//import com.shop.mall.domain.Member;
//import com.shop.mall.repository.MemberJpaRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//
//    @Autowired MemberService memberService;
//    @Autowired MemberJpaRepository memberJpaRepository;
//    @Autowired EntityManager em;
//
//    @Test
//    @DisplayName("회원가입")
//    public void 회원가입() throws Exception {
//        //given
//        Member member = new Member("seanlee0701@naver.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//
//        //when
//        Long savedId = memberService.register(member);
//
//        //then
//        em.flush(); //영속성 컨택스트에 있는 내용을 쿼리로 날린다.
//        assertEquals(member, memberJpaRepository.findOne(savedId));
//    }
//
//    @Test
//    @DisplayName("중복_이메일_회원_예외")
//    public void 중복_이메일_회원_예외() throws Exception {
//        //given
//        Member member1 = new Member("seanlee0701@naver.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//
//        Member member = new Member("seanlee0701@naver.com","seanlee","서울특별시 장충단로 5길","4321",20000);
//
//        //when
//        memberService.register(member1);
//
//        try {
//            memberService.register(member); //예외가 발생해야함
//        }catch (IllegalStateException e){
//            return;
//        }
//
//        //then
//        fail("예외가 발생해야한다 이 글이 보인다면 TEST 실패이다.");
//
//    }
//
//    @Test
//    @DisplayName("중복_닉네임_회원_예외")
//    public void 중복_닉네임_회원_예외() throws Exception {
//        //given
//        Member member1 = new Member("seanlee0701@daum.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//
//
//        Member member = new Member("seanlee0701@naver.com","seanlee0701","서울특별시 장충단로 6길","4321",20000);
//
//        //when
//        memberService.register(member1);
//
//        try {
//            memberService.register(member); //예외가 발생해야함
//        }catch (IllegalStateException e){
//            return;
//        }
//
//        //then
//        fail("예외가 발생해야한다 이 글이 보인다면 TEST 실패이다.");
//    }
//
//
//    @Test
//    @DisplayName("로그인_성공")
//    void 로그인_성공() {
//        //given
//        Member member1 = new Member("seanlee0701@daum.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//        memberService.register(member1);
//
//        //when
//        String loginNickname = memberService.login(member1);
//
//        //then
//        assertEquals(loginNickname,member1.getNickname());
//
//    }
//
//    @Test
//    void 로그인_이메일_실패() throws Exception {
//        //given
//        Member member1 = new Member("seanlee0701@daum.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//        memberService.register(member1);
//
//        Member member = new Member("seanlee0701@naver.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//
//        //when
//        try {
//            memberService.login(member); //예외가 발생해야함
//        }catch (IllegalArgumentException e){
//            return;
//        }
//
//        //then
//        fail("예외가 발생해야한다 이 글이 보인다면 TEST 실패이다.");
//    }
//
//    @Test
//    void 로그인_비밀번호_실패() throws Exception {
//        //given
//        Member member1 = new Member("seanlee0701@daum.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//        memberService.register(member1);
//
//        Member member = new Member("seanlee0701@daum.com","seanlee0701","서울특별시 장충단로 4길","4321",10000);
//
//        //when
//        try {
//            memberService.login(member); //예외가 발생해야함
//        }catch (IllegalStateException e){
//            return;
//        }
//
//        //then
//        fail("예외가 발생해야한다 이 글이 보인다면 TEST 실패이다.");
//    }
//
//    @Test
//    void 회원정보_성공() {
//        //given
//        Member member1 = new Member("seanlee0701@daum.com","seanlee0701","서울특별시 장충단로 4길","1234",10000);
//        memberService.register(member1);
//
//
//
//    }
//
//    @Test
//    void 회원정보_실패() throws Exception {
//        //given
//
//        //when
//
//
//        //then
//
//    }
//
//    @Test
//    void 캐시_충전_성공() {
//    }
//
//    @Test
//    void 캐시_충전_실패() throws Exception {
//        //given
//
//        //when
//
//
//        //then
//
//    }
//
//
//    @Test
//    void 주소_변경_성공() throws Exception {
//        //given
//
//        //when
//
//
//        //then
//
//    }
//
//    @Test
//    void 주소_변경_실패() throws Exception {
//        //given
//
//        //when
//
//
//        //then
//
//    }
//    @Test
//    void 닉네임_변경_성공() throws Exception {
//        //given
//
//        //when
//
//
//        //then
//
//    }
//
//    @Test
//    void 닉네임_변경_실패() throws Exception {
//        //given
//
//        //when
//
//
//        //then
//
//    }
//}