# 📘 Spring Framework 학습 정리 (2025.06.09)

## 1. Spring Framework 소개

### 1.1. Spring Framework란?

- **자바 플랫폼을 위한 오픈 소스 애플리케이션 프레임워크**
- **동적 웹 사이트 개발**을 위한 다양한 기능 제공
- **전자정부 표준 프레임워크의 기반 기술**
- 국내 공공기관 프로젝트에서 많이 사용됨

> 🔗 [Spring 공식 사이트 바로가기](https://spring.io/)

> 💡 **Spring 이란 이름의 유래**  
> EJB의 복잡함을 해결하기 위해 Rod Johnson이 개발한 프레임워크로,  
> '겨울을 지나 봄을 맞이한다'는 의미에서 "Spring"이라 명명됨.

---

### 1.2. Spring Framework의 특징

#### ✅ 오픈소스 기반
- 활발한 커뮤니티, 지속적 진화

#### ✅ 엔터프라이즈 개발 지원
- 유연한 아키텍처, 완전한 Java 지원

#### ✅ 프레임워크 설계 원칙
- 호환성, 유연성, 선택의 자유, 늦은 결정

#### ✅ 코드 품질
- 잘 정리된 문서, 순환 의존 없는 구조

---

## 2. 스프링 컨텍스트에 빈 등록하기

### 2.1 `@Bean` 애너테이션 사용

1. `@Configuration` 구성 클래스 작성
2. 빈을 리턴하는 메서드에 `@Bean` 애너테이션 추가
3. `AnnotationConfigApplicationContext`로 컨텍스트 생성

```java
@Configuration
public class ProjectConfig {
    @Bean
    Parrot parrot() {
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }
}
```
```java
var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
Parrot p = context.getBean(Parrot.class);
```

### 2.2 동일 타입 빈이 여러 개인 경우
```java
@Bean(name = "miki")
Parrot parrot2() {
    var p = new Parrot();
    p.setName("Miki");
    return p;
}
```
```java
Parrot p = context.getBean("miki", Parrot.class);
```

### 3. 스테레오타입 애너테이션 활용
✅ @Component + @ComponentScan
```java
@Component
public class Parrot {
    ...
}
```
```java
@Configuration
@ComponentScan(basePackages = "org.scoula.domain")
public class ProjectConfig3 {}
```

### 4. 인스턴스 생성 후 로직 실행: @PostConstruct
```java
@PostConstruct
public void init() {
    this.name = "Kiki";
}
```
- @Component로 등록된 빈에도 생성 후 초기화 작업을 설정할 수 있음

### 5. Spring Framework 주요 특징 🌟
| 항목                        | 설명                                                   |
|---------------------------|--------------------------------------------------------|
| **경량 컨테이너**             | POJO 기반, 필요한 기능만 선택 사용                          |
| **IoC (제어 반전)**           | 객체의 생명주기를 스프링이 관리                              |
| **DI (의존성 주입)**          | 설정을 통해 필요한 객체 자동 주입                            |
| **AOP (관점 지향 프로그래밍)** | 공통 기능(로깅, 보안 등) 분리 가능                           |
| **Spring MVC**             | MVC 패턴을 활용한 웹 애플리케이션 개발                      |
| **PSA (서비스 추상화)**        | 다양한 기술 스택과의 호환성 보장                            |
