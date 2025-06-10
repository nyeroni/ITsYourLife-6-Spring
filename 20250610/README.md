# 📘 Spring Framework 요약 (2025.06.10)

## 1. Spring MVC 구조 🎭

### 🏗️ 1.1 프로젝트 설정 방식

| 설정 대상        | XML 설정             | Java 설정             |
|------------------|----------------------|------------------------|
| Spring MVC       | servlet-context.xml  | ServletConfig.class    |
| Spring Core      | root-context.xml     | RootConfig.class       |
| MyBatis / DB     | 포함 / 연동          | 포함 / 연동            |

### 🧱 1.2 WebApplicationContext 계층

- MVC 설정: 웹 관련 Bean 관리
- 일반 설정: 비즈니스 로직/데이터 접근 Bean 관리

---

## 2. Spring MVC 💭

### 📐 2.1 계층화 구조

- 개발자는 Servlet/JSP API에 의존하지 않고 비즈니스 로직 구현에 집중
- Spring MVC가 내부적으로 처리

### 🧭 2.2 Model 2 기반 MVC 아키텍처

1. Request → Controller → Logic 처리
2. View로 데이터 전달 → Response

---

## 3. Spring MVC 아키텍처 흐름 🔄

### 🧩 3.1 주요 컴포넌트

- **DispatcherServlet**: 요청 진입점
- **HandlerMapping**: Controller 매핑
- **HandlerAdapter**: Controller 실행
- **ViewResolver**: View 이름 → 실제 View 변환
- **Model**: View로 전달할 데이터
- **View**: 최종 결과 렌더링

### ⚙️ 3.2 상세 구성 요소

| 컴포넌트         | 역할 요약 |
|------------------|-----------|
| **Filter**         | 전역 필터링 (로그인 등) |
| **DispatcherServlet** | 모든 요청 처리 시작 |
| **HandlerMapping** | 요청 URL → Controller 연결 |
| **HandlerAdapter** | Controller 메서드 호출 |
| **HandlerInterceptor** | 전/후처리 (인증, 로깅 등) |
| **Controller**    | 요청 처리, Service 호출 |
| **Service**       | 비즈니스 로직 수행 |
| **Repository**    | DB 접근 (CRUD) |
| **DB**            | 실제 데이터 저장 |
| **Model**         | View에 전달할 데이터 |
| **View**          | 화면 출력 (JSP, Thymeleaf 등) |

---

## ✨ 핵심 요약

- 🧭 **Front Controller 패턴**: 모든 요청 → DispatcherServlet이 분배
- 🔨 **개발자는 Controller/Service/Repository만 구현**
- ⚙️ 설정 방식: XML / Java Config (최근에는 Java Config 선호)

---

## ✅ Spring MVC 장점

- 🎯 **관심사의 분리**: 역할별 명확한 구조
- 💡 **느슨한 결합**: DI 기반 컴포넌트 연결
- 🔁 **재사용성**: 모듈 단위 재사용 가능
- 🧪 **테스트 용이성**: 단위 테스트 가능

## 4. Controller 역할 및 처리 흐름 🚀

* HTTP 요청을 받아 비즈니스 로직을 수행하고 View에 데이터를 전달
* MVC 구성요소:

    * **Model**: 데이터 및 비즈니스 로직
    * **View**: JSP, Thymeleaf 등 UI 처리
    * **Controller**: 요청 처리 및 Model/View 연결

> 처리 흐름: Client → DispatcherServlet → HandlerMapping → HandlerAdapter → Controller → ViewResolver → View

---

## 5. 프로젝트 기본 구조 🛠️

* 템플릿: SpringLegacy
* 기본 패키지: `org.scoula`
* 서버: Tomcat 9.x

### 📁 패키지 예시

```
src/main/java/org.scoula/
  ├── config
  ├── controller
  ├── exception
  └── ex03/controller, dto
```

---

## 6. 주요 어노테이션 📝

| 어노테이션                         | 설명                 |
| ----------------------------- | ------------------ |
| `@Controller`                 | 웹 요청 처리 클래스 지정     |
| `@RequestMapping`             | URL 매핑 (클래스/메서드)   |
| `@GetMapping`, `@PostMapping` | HTTP 메서드 매핑 전용     |
| `@RequestParam`               | 단일 파라미터 수집         |
| `@ModelAttribute`             | DTO 바인딩 및 Model 저장 |
| `@ResponseBody`               | 객체 → JSON 응답 처리    |

---

## 7. 파라미터 수집 방식 🎯

* **DTO 바인딩**: 클래스 필드와 요청 파라미터 이름이 일치하면 자동 매핑
* **@RequestParam**: 개별 파라미터 수집 (기본값/필수 설정 가능)
* **List/Array 바인딩**: 동일한 파라미터 다수 전달 시 자동 수집
* **@DateTimeFormat**: 날짜 포맷 지정 바인딩 지원

---

## 8. Model 사용법 🚚

* `model.addAttribute("key", value)` 로 View에 데이터 전달
* 기본 자료형 전달 시 `@ModelAttribute("key")` 사용 필요

```java
@GetMapping("/")
public String home(Model model) {
    model.addAttribute("name", "홍길동");
    return "index";
}
```

---

## 9. 리턴 타입 정리 ⚡

| 타입               | 설명                                |
| ---------------- | --------------------------------- |
| `String`         | JSP 이름 반환 (forward 또는 redirect)   |
| `void`           | URL과 동일한 JSP 자동 매핑                |
| DTO/VO           | JSON 응답으로 자동 변환 (`@ResponseBody`) |
| `ResponseEntity` | 상태코드 + 바디 제어 가능 (API 응답용)         |

---

## 10. 파일 업로드 📁

* 설정: `MultipartResolver` Bean 등록 + Dispatcher 설정
* `MultipartFile` 타입으로 파일 수신 및 저장 가능

```java
@PostMapping("/upload")
public void upload(ArrayList<MultipartFile> files) {
    for (MultipartFile file : files) file.transferTo(...);
}
```

---

## 11. 전역 예외 처리 🔧

* `@ControllerAdvice` + `@ExceptionHandler` 조합 사용
* 예외 유형별로 메서드 분리 가능 (ex: IllegalArgumentException, 404)

```java
@ExceptionHandler(Exception.class)
public String handleEx(Exception ex, Model model) {
    model.addAttribute("exception", ex);
    return "error_page";
}
```

---

## 12. 핵심 클래스 및 설정 ✨

| 구성 요소                | 설명                  |
| -------------------- | ------------------- |
| `Model`              | View로 데이터 전달        |
| `RedirectAttributes` | 리다이렉트 시 데이터 전달      |
| `MultipartFile`      | 업로드된 파일 래퍼 객체       |
| `ResponseEntity`     | HTTP 상태/헤더/바디 직접 제어 |
| `@EnableWebMvc`      | Spring MVC 활성화 설정   |
| `@ComponentScan`     | Bean 자동 등록 범위 설정    |
| `ViewResolver`       | 뷰 이름 → JSP 매핑 설정    |

---

> 🔁 **Spring MVC 핵심 흐름**: DispatcherServlet → HandlerMapping → Controller → ViewResolver → View

> 🧩 **구현 포인트**: Controller/Service/Repository 만 작성하면 나머지 인프라는 Spring이 처리
