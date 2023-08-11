# 스프링 부트 (Spring Boot)

ISP-공인 IP-사설IP(192.168.0.0)

Client → request → Server

request ⇒ URL

Server →response→Client

response

**Statefull VS Stateless**

Statefull : 한 번 연결되면 session이 유지되면서 서비스 종료 시 까지 연결이 계속 유지되는 방식

(Socket)

Stateless : 데이터를 전송 할 때만 연결하고 응답 후 연결을 끊어 버리는 방식(HTTP)

웹서버(Web Server)와 웹 애플리케이션 서버(Web Application Server)

**웹 서버** : 정적인 리소스(HTML,CSS,JS) 요청 에 대한 처리를 하는 서버(Apache,Nginx)

**웹 애플리케이션 서버(WAS)** : 애플리케이션의 로직(동적 HTML,서블릿,JSP) 을 수행하는 동적인 요청에 대한 처리(Tomcat,Web Logic,…)

http : 80port ,https : 443port

클라이언트 → 웹서버(80) → WAS(8080)

## 서블릿

자바의 데이터베이스 기술(JDBC)

자바의 웹 기술 - 서블릿servlet : 자바코드로 html 생성(HTML생성이 어려움)

JSP(java server page) : html문서에서 자바코드를 사용하는 것 → html생성은 편하지만 비즈니스 로직까지 역할을 담당하게 됨

Client → WAS→ Request,Resposne객체를 만들어서 servlet호출 → 개발자가 request객체에서 요청 정보를 꺼내서 처리→ response객체에서 응답 내용을 저장해서 클라이언트에 저장 → client(웹브라이저) 응답 내용을 해석하여 브라우저에 표시

## MVC 패턴 : Model,View,Controller로 역할을 나누어서 개발

MVC 프레임워크

## 스프링 주요 개념

- loC(Inversion of Control):제어 역전

```java
제어의 역전
//싱글톤 패턴

```

- DI(Dependency Injection) : 의존성 주입

```java
의존성 주입
Class A{}

Class B{
	private A a;

	public void setA(A a){
		this.a=a;
	}

	B b = new B();
	b.setA(new A());
}
```

- AOP(Aspect oriented Programming) : 관점 지향 프로그래밍

### 스프링 프로젝트 생성

![Untitled](<%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%87%E1%85%AE%E1%84%90%E1%85%B3%20(Spring%20Boot)%20eabbc46102e345df87c31921f06be77e/Untitled.png>)

JSP를 쓰려면 War파일로 생성해야함.

![Untitled](<%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%87%E1%85%AE%E1%84%90%E1%85%B3%20(Spring%20Boot)%20eabbc46102e345df87c31921f06be77e/Untitled%201.png>)

HTTP Status Code

200번 : 정상처리, 400번대 : 클라이언트가 잘못 요청, 500번대: 서버 처리중 오류발생

**스프링이 시작되면 @component붙은 class들을 검사**

스프링 빈(bean)의 생성

1. 컴포넌트 스캔에 의한 자동생성

   -@Conponent : 스프링 빈으로 자동 등록

2. 자바 코드로 직접 빈을 등록

   -@Configuration:클래스 레벨에 등록,스프링 컨테이너를 생성

   -@Bean : 메소드 레벨에 등록,스프링 컨테이너의 빈으로 등록

### @RequestMapping

-requestMapping은 HTTP방식을 가리지 않는다(GET,POST,PUT,DELETE)

![Untitled](<%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%87%E1%85%AE%E1%84%90%E1%85%B3%20(Spring%20Boot)%20eabbc46102e345df87c31921f06be77e/Untitled%202.png>)

-요청값은 배열로 받을 수 있다.

### Log

-스프링 logger 라이브러리를 불러옴

import org.slf4j.Logger;

![Untitled](<%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%87%E1%85%AE%E1%84%90%E1%85%B3%20(Spring%20Boot)%20eabbc46102e345df87c31921f06be77e/Untitled%203.png>)

### 경로변수(PathVariable)

ex) localhost:9000/users/user1 = localhost:9000/users?user_id=user1( ? 이후는 쿼리문)

---

### @RequestParam

![Untitled](<%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%87%E1%85%AE%E1%84%90%E1%85%B3%20(Spring%20Boot)%20eabbc46102e345df87c31921f06be77e/Untitled%204.png>)

@RequestParam으로 요청 파라미터를 읽을 수 있다. 요청 파라미터의 key와 메소드의 변수명이 다르면 value or name속성을 지정하여 변수명을 맞추어야 한다.

파라미터를 받지 않아도 될 경우 required속성을(기본은 true)를 false로 줄 수 있다.

요청값이 없을 경우 defaultValue을 설정할 수 있다.

---

### @ModelAttribute 어노테이션을 통한 파라미터 바인딩

바인딩

1. MemberData객체를 생성
2. 요청 파라미터의 이름과 MemberData클래스의 field가 같은 것을 찾고
   setter를 호출하여 field의 값을 넣는다.
3. **타입이 맞지 않으면 BindException이 발생한다.**

### HttpServletRequest : HTTP요청 메세지를 파싱

- HTTP메소드
- URL
- 쿼리 파라미터 ex) localhost:8080?id=wed&pass=1234
- 프로토콜 ex) 80
- 헤더
- 바디 form파라미터

### HTTP요청데이터

-요청방식

- GET:쿼리 파라미터,메시지 바디 없이 URL에 데이터를 포함하여 전송
- POST:메세지 바디에 파라미터를 담아서 전송

-HTTP message body

- TEXT,JSON,XML,등이 주로 사용
- 보통은 JSON형식으로 많이 사용

**JSON : JavaScript Object Notation**

-데이터 전송 포맷

-{”key”:”value”,”key”:”value”}

---

1.요청 방식

- @RequestMapping, @GetMapping , @PostMapping

  2.요청 파라미터

- @RequestParam , @ModelAttribute

  3.HTTP Body에 데이터를 직접 담아서 전달

- @RequestBody

  4.응답객체

- HttpServletResponse : HTTP응답 메세지를 파싱하는 객체
- 구성 : TTP응답 코드,헤더,바디
- 응답방식
  1)단순 텍스트 2) HTML 3) JSON,XML등
