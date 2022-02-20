## fftl-lookup-board

### 개요

조회 기능을 사용해 보기 위한 작은 REST API입니다. 간단한 구조의 게시글을 다양한 방법으로 조회 해보는 API를 만들어 보았습니다.

### 사용 기술

Java, Spring Boot, MySQL, Spring Data JPA, gradle, git

### 구현 기능

-   제목으로 조회
-   내용으로 조회
-   작성날짜로 범위로 조회
-   작성자 아이디로 조회

### DB 설계

게시판과 유저만이 존재하는 작은 규모의 게시판입니다. 유저와 게시판은 일대 다 관계를 가지고 있습니다. 게시글 삭제는 boolean 값을 주어 명시적인 삭제를 적용 해주었습니다.

```java
User

Long id;
String username;

List<Board> boards;

```
```java
Board

Long id;
String title;
String content;
LocalDateTime regdate;
int searchCnt;
boolean deleteYn;

User user;

```

### 프로젝트 후기

- 조회 기능을 테스트 해보기 위해서 작은 프로젝트를 진행했습니다. 프로젝트를 진행하며 JpaRepository 에서 제공하는 조회 기능이 Contains, Between 
등 다양하게 제공되고 있다는 사실을 알게 되었습니다.
  
- 프로젝트를 진행하며 바로바로 테스트 코드를 작성해 보았습니다. 이번 프로젝트에서는 통합테스트인 @SpringBootTest를 이용한 테스트를 진행하였는데 모든 Bean을 로드 하는 만큼
테스트 코드를 작성하는데 편리한 점이 있었습니다. 하지만 모든 정보를 가져오기 때문에 이전에 사용했던 @WebMvcTest 에 비해서는 테스트 속도가 느리다는 것도 확인하게 되었습니다. 


