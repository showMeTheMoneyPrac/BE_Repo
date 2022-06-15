# Backend_Repo

##  📅 프로젝트 기간

-2022년 5월 11일 ~ 2022년 6월 3일

<br>

## 🌱 기술 스택


<span><img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=Amazon AWS&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white"></span>
<img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=white">  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white"> <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">  <img src="https://img.shields.io/badge/Nginx-6DB33F?style=for-the-badge&logo=Nginx&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img alt="NGrinder" src ="https://img.shields.io/badge/ngrinder-ffa500.svg?&style=for-the-badge&logo=NGrinder&logoColor=white"/>
  
    
<br>    


## 🏄‍ 팀원
 <div align="center">
  
|이승수|정요한|
|:--------:|:--------:|
|<img src="https://avatars.githubusercontent.com/u/84774696?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/94155128?v=4" width=200>|
|🔨Backend|🔨Backend|
|<a href="https://github.com/leeseungsoo0701">Github-Link</a>|<a href="https://github.com/John3210of">Github-Link</a>|
</div>

<br>
<br>



## 프로젝트 구성
### 백엔드 아키텍처
 <div align="center">
 <img src="https://user-images.githubusercontent.com/59475849/160796220-c55b19f4-7f08-4095-8686-1a5ea2725eb8.png" width="900" height="600">
</div>

<br>

## ⚙️ 개발 환경
- **Server** : AWS EC2(Ubuntu 18.82 LTS)  

- **Framework** : Springboot(2.6.4)

- **Database** : Mysql (AWS RDS,8.0.27)  

- **ETC** : AWS S3, Redis

## 
## 📝 ERD
![drawSQL-export-2022-06-09_23_31](https://user-images.githubusercontent.com/84774696/172872428-6d70e3d8-3999-4287-ae31-597636d25bf7.png)
<br>
<br>
  

##
## 💡 문제 해결과정
-  #### 로그인 너란 친구란 JWT - <a href="https://github.com/HangHae99ProjectTeam10/SharePod-Server/wiki/WIKI,-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%84%88%EB%9E%80-%EC%B9%9C%EA%B5%AC%EB%9E%80-JWT" >WIKI</a>
-  #### 프로젝트 규칙: Github Flow ✅ VS Git Flow - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/Github-Flow-%E2%9C%85--VS-Git-Flow" >WIKI</a>
-  #### 요구사항 구체화: 요구사항 상세 확인서 - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EC%83%81%EC%84%B8-%ED%99%95%EC%9D%B8%EC%84%9C" >WIKI</a>
-  #### ERD: DataBase 설계  - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/DataBase-%EC%84%A4%EA%B3%84(%EC%B4%88%EA%B8%B0-%EC%9A%94%EA%B5%AC-%EB%B6%84%EC%84%9D,-%EA%B8%B0%EB%8A%A5-%EB%AA%A9%EB%A1%9D,-%EA%B0%9C%EC%B2%B4%EC%99%80-%EC%86%8D%EC%84%B1,-%EA%B0%9C%EC%B2%B4%EC%99%80-%EA%B4%80%EA%B3%84,DB-%EC%8A%A4%ED%82%A4%EB%A7%88)" >WIKI</a>
-  #### TEST Code 기반 개발: TDD - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/TDD" >WIKI</a>
-  #### 스케일 업: Entity -> DTO - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/Entity-%E2%86%92-DTO" >WIKI</a>
-  #### 스케일 업: 동적쿼리(QueryDsl) - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/%EB%8F%99%EC%A0%81-%EC%BF%BC%EB%A6%AC-(QueryDsl)" >WIKI</a>
-  #### 리팩토링: DB 역정규화  - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/DB-%EC%97%AD%EC%A0%95%EA%B7%9C%ED%99%94" >WIKI</a>
-  #### 캐싱: Redis를 이용한 단순 캐싱 - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/Cache" >WIKI</a>
-  #### 부하테스트: NGrinder - <a href="https://github.com/showMeTheMoneyPrac/BE_Repo/wiki/Ngrinder" >WIKI</a>
<br>
<br>


## 📬Commit Rule
> 수정한 종류에 따라 커밋 메시지를 선택

|메시지명|설명|
|---|---|
|feat|새로운 기능 추가 관련|
|fix|코드 수정, 버그 개선|
|test|테스트 코드, 리팩토링 테스트 코드 추가|
|refactor|코드 리팩토링(기능향상)|
|chore|빌드 업무 수정, 패키지 매니저 수정|
|docs|문서 수정(md, git관련 파일, 이미지파일 수정)|
|style|코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우|


### 🎨이모지(Emoji)

- 🎨 : 코드의 형식 / 구조를 개선 할 때  
- 📰 : 새 파일을 만들 때  
- 📝 : 사소한 코드 또는 언어를 변경 할 때  
- 🐎 : 성능을 향상 시킬 때  
- 📚 : 문서를 쓸 때  
- 🚑 : 버그를 고칠 때
- 🔥 : 코드 또는 파일을 제거 할 때
- 🚜 : 파일 구조를 변경 할 때
- 🔨 : 코드를 리팩토링 할 때  
- ☔️ : 테스트를 추가 할 때  
- 🔬 : 코드 범위를 추가 할 때   
- 🤝 : 파일을 병합 할 때  

### 📢관련 이슈  
> 작성한 커밋과 관련된 이슈 번호를 매핑  
```
< 예시 >
[feat] 📰 add api #1
```

### 🔐보안 관련

- **(중요)** 어떠한 KEY값이나 DB 접속 정보가 포함된 커밋을 날리지 않는다.

<br>

## 🌳Branch / PR / Issue 규칙

### Branch

- `main` 브랜치에서는 버젼 배포 외에는 작업하지 않는다.
- 브랜치 이름은 `feature-1` 이런 식으로 이슈의 번호를 명시해서 생성한다.
- `devlop` 브랜치에는 이슈단위로 기능이 구현한후에 코드리뷰후 PR한다.
- 테스트 브랜치나 더이상 안쓰는 브랜치는 삭제한다.

### Pull Request

- `develop` 브랜치에만 merge한다.
- 자신이 계획한 기능이 완료됐을 경우에만 PR 작성
- 팀원과 협의 후 PR을 작성하며 독자적으로 PR 생성 후 merge하지 않는다. 

### Issue

- 앞으로 할 일이나 버그 등을 기록한다.

<br>
