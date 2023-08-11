# 스프링부트 2

정적 리소스 : 고정된 HTML,CSS,JS,이미지,동영상

동적 리소스 : 템플릿 엔진으로 동적으로 HTML을 생성한 것(thymeleaf)

-CSR(**Client** Side Rendering)

- 자바스크립트등을 사용해 HTML동적으로 생성해서 사용
- 주로 동적인 화면을 구성하는데 사용(페이지에서 바로 적용)
- React,Vue.js등이 있음

-SSR(**Server** Side Rendering)

- 서버에서 HTML을 만들어서 사용
- 주로 정적인 화면을 구성하는데 사용
- **JSP,타임리프** 등이 있음.

url_ex)

회원가입 : localhost:9000/member/join

회원수정 : localhost:9000/member/update

### SESSION

-사용자가 웹브라우저를 닫거나 웹서버가 종료될때까지 유지됨

### Thymeleaf

**1)타입리프 URL링크**

![Untitled21](https://github.com/GHLee1/devfox_study/assets/97584550/4ee66f40-5db7-444a-9381-a030aee7bb42)

![Untitled 22](https://github.com/GHLee1/devfox_study/assets/97584550/e7eb006a-eb8d-445f-9e1c-e39044422946)
![Untitled 23](https://github.com/GHLee1/devfox_study/assets/97584550/05c90eea-8716-4dc9-8283-55738bde30e5)

**Spring.package**

- controller : 컨트롤러 클래스 → URL매핑,유효성 체크
- model : 비즈니스 모델 → VO-로직x ,DTO(DATA Transform Object) ,Entity(VO+비지니스 로직)
- service : 비즈니스 로직,트랜잭션 관리
- repository : DAO

MyBatis(UI→Manager → DAO →DB)

WEB(VIEW→Controller→Repository)

**Forward방식**

웹브라우저→요청→컨트롤러→메소드1→메소드2→응답→웹브라우저

-응답페이지까지 request객체가 유지된다

**redirect방식**

앱브라우저 → 요청 → 컨트롤러→메소드1→응답→웹브라우저→요청→컨트롤러→메소드2→응답→웹브라우저

-클라이언트에 응답을 보내고 클라이언트가 다시 요청을 보내기 때문에 새로운 request객체가 만들어진다

**Scope**

-page : 페이지 내에서 생성되고 페이지를 벗어나면 종료되는 데이터

-request: 요청을 보내고 응답을 받기까지 유지되는 데이터

-session: 웹브라우저나 서버를 종료하기 전 까지 유지되는 데이터

-application: 서버를 종료하기 전 까지 유지되는 데이터

게시글 읽기 : /read?id=query

<**a** th:href=_"@{/read(id=${board.id})}">_

타임리프에서 () 는 파라미터값으로 들어감

-상대경로 ~ ex localhost:/read/join

![Untitled 24](https://github.com/GHLee1/devfox_study/assets/97584550/08949131-046b-4b22-8a78-66957e391aaf)

-절대경로 /~ ex localhost:/join

![Untitled 25](https://github.com/GHLee1/devfox_study/assets/97584550/b112951d-287d-460a-99d5-cc12ad6736c0)

(bindingResult ) argument를쓰면 argument받을 때 오류가 나도 메소드안으로 진입을 하게 해줌

**@Transaction**

**BindingResult**

### 검증 어노테이션 (~Form)

- @Size : 문자열의 길이
- @NotNull : null불가
- @Not Empty : null,""불가
- @NotBlank : null,""불가
- @Past:과거 날짜만 가능
- @PastOrPresent : 오늘을 포함한 과거 날짜만 가능
- @Future: 미래 날짜만 가능
- @FutureOrPresent : 오늘을 포함한 미래 날짜만 가능
- @Pattern : 정규식 사용
- @Max : 최대값
- @Min : 최소값
- @Valid : 해당 Obhect의 Validation을 실행

### 로그인처리

1.쿠키를 이용한 로그인

- 쿠키 : 웹브라우저와 서버의 도메인 사이에 생성된 데이터로 **클라이언트 사이드**에 저장

![Untitled 26](https://github.com/GHLee1/devfox_study/assets/97584550/a659a854-9247-4ca8-a94d-43c0229b4e73)

2.세션을 이용한 로그인(세션을 써야 보안성 측면에서 좋다.)

- 세션 : 웹브라우저와 서버 사이에 생성된 데이터로 **서버 사이드**에 저장
