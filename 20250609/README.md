# 📘 Spring Framework 요약 (2025.06.09)

## 1. Spring 기본 개념 정리

### ✅ 핵심 용어 정리

| 개념                                 | 설명                           |
| ---------------------------------- | ---------------------------- |
| IoC (Inversion of Control)         | 객체의 제어권을 개발자가 아닌 컨테이너가 갖는 개념 |
| DI (Dependency Injection)          | 의존 객체를 직접 생성하지 않고 주입받는 방식    |
| Bean                               | Spring Container가 관리하는 객체    |
| AOP (Aspect Oriented Programming)  | 공통 관심사를 모듈화하여 코드 분리          |
| PSA (Portable Service Abstraction) | 외부 기술에 종속되지 않도록 추상화 제공       |

---

## 2. 스프링 컨텍스트와 빈 등록

### 🔹 @Configuration + @Bean

```java
@Configuration
public class ProjectConfig {
    @Bean
    Parrot parrot() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    Person person(Parrot parrot) {
        Person p = new Person();
        p.setName("Ella");
        p.setParrot(parrot);
        return p;
    }
}
```

### 🔹 @Component + @ComponentScan

```java
@Component
public class Parrot {}

@Configuration
@ComponentScan(basePackages = "org.scoula")
public class ProjectConfig {}
```

### 🔹 의존성 주입 방법 비교

| 방법        | 설명                     | 권장도   |
| --------- | ---------------------- | ----- |
| 생성자 주입    | final 필드 사용 가능, 테스트 용이 | ⭐⭐⭐⭐⭐ |
| 필드 주입     | 코드 간단, 테스트 어려움         | ⭐⭐    |
| Setter 주입 | 선택적 주입에 유용             | ⭐⭐⭐   |

### 🔹 @Autowired 사용 예시

```java
@Component
public class Person {
    @Autowired
    private Parrot parrot;
}
```

### 🔹 @Qualifier 사용 예시

```java
@Bean
public Parrot parrot1() {...}

@Bean
public Parrot parrot2() {...}

@Bean
public Person person(@Qualifier("parrot2") Parrot parrot) {...}
```

---

## 3. 설정 클래스 구성

### 📁 RootConfig.java

* 서비스, DAO 등 비즈니스 로직 설정

```java
@Configuration
@ComponentScan(basePackages = {"org.scoula"})
public class RootConfig {}
```

### 🌐 ServletConfig.java

* Web 계층 설정 (@EnableWebMvc, ViewResolver 등)

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.scoula.controller"})
public class ServletConfig implements WebMvcConfigurer {
    ...
}
```

### 🚀 WebConfig.java

* 웹 애플리케이션의 시작점 (web.xml 대체)

```java
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() { return new Class[] { RootConfig.class }; }
    protected Class<?>[] getServletConfigClasses() { return new Class[] { ServletConfig.class }; }
    protected String[] getServletMappings() { return new String[] { "/" }; }
}
```

---

## 4. 프로젝트 디렉토리 구조

```
src/main/
├── java/org.scoula/
│   ├── config/
│   └── controller/
├── resources/
├── webapp/
│   └── WEB-INF/views/
```

---

## 5. 로그 설정 (Log4j2)

### 🔧 log4j2.xml 설정 요약

```xml
<Configuration>
  <Appenders>
    <Console name="console" target="SYSTEM_OUT">
      <PatternLayout pattern=" %-5level %c(%M:%L) - %m%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="INFO">
      <AppenderRef ref="console"/>
    </Root>
  </Loggers>
</Configuration>
```

---

## 6. 의존성 주입 테스트 (@Spring Test)

### 🧪 JUnit5 + Spring 설정 예시

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class RestaurantTest {
    @Autowired
    private Restaurant restaurant;

    @Test
    void getChef() {
        assertNotNull(restaurant);
        log.info(restaurant);
        log.info(restaurant.getChef());
    }
}
```

---

## 📌 주요 어노테이션 정리

| 어노테이션                 | 역할                   |
| --------------------- | -------------------- |
| @Configuration        | 설정 클래스 지정            |
| @ComponentScan        | 컴포넌트 자동 검색           |
| @Component            | 일반 빈 등록              |
| @Bean                 | 메서드 기반 빈 등록          |
| @Autowired            | 의존성 자동 주입            |
| @Qualifier            | 동일 타입 빈 중 선택 지정      |
| @EnableWebMvc         | Spring MVC 설정 활성화    |
| @Log4j2               | 로그 객체 자동 생성 (Lombok) |
| @ExtendWith           | Spring 테스트 지원 활성화    |
| @ContextConfiguration | 테스트 설정 지정            |

