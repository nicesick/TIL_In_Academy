# HttpSession & Spring

- HttpSession 을 이용해서 사용자 정보를 서버에 저장해 놓을 수 있다.

```java
user = biz.get(id);

if (user.getPwd().equals(pwd)) {
    center = "loginok";

    HttpSession session = request.getSession();
    session.setAttribute("loginuser", user);
    
    // 필요한 정보를 session 에 저장할 수 있다.
    
    session.setMaxInactiveInterval(10);
    
    // 세션의 저장 시간을 정할 수도 있다.
}
```

- session 을 지울 때에는 아래처럼 한다.

```java
HttpSession session = request.getSession();

if (session != null) {
    session.invalidate();
    // 로그아웃 할 때에는 세션을 풀어준다.
}
```



## I. Spring

- 더 빨리, 더 쉽게, 더 유지보수 쉽게
- 아키텍쳐 : 무엇을 할지, 어떤 방식으로 할지, 정하는 것



- 세가지 요소(결국 MVC 와 같다)
  - 프레젠테이션 = V = Web
  - 비즈니스 = C = Biz
  - 펄시스턴스 = M = Dao



- 장점(꼭 배워야 할 것)

  - IoC(Inversion of Control) : 해당 클래스를 지칭하는 게 아닌 상황에 맞는 클래스를 지칭받음

  

  - AOP
  - POJO : Servlet 처럼 꼭 규칙을 지켜야 하는 것이 아닌 일반 클래스가 실행된다.



## II. 사용

- www.spring.io
- 여기서 spring library 를 다운 받는다.

- 다운받은 jar 파일들을 Properties -> Build path -> Library 에서 추가한다.



- AbstractApplicationContext 클래스에서 xml 파일을 보면서 Container 를 생성한다.

```java
AbstractApplicationContext factory = 
    new GenericXmlApplicationContext("mytv.xml");
```



- App 이 TV 를 요구하면 이에 맞는 TV 의 레퍼런스를 반환해준다.

```java
TV stv = (TV)factory.getBean("sstv");
```



- marketplace 에서 spring 관련된 plug-in 을 다운받을 수 있다.
  - sts 로 검색



## III. Maven

- 외부 라이브러리 관리 기법

  

## IV. Spring 환경 구축

- spring plug-in 을 깔고나서
- 프로젝트 -> spring -> Add Spring Project Nature
- Maven 을 해서 Spring Library 를 등록시킨다. (pom.xml)
  - Configure -> Convert to Maven Project
  - 사용자 -> .m2 -> repository 에 자료가 다운받아지는데 만약 오류가 나면 다시 지웠다가 깔면 됨

- 다 깔린 후
  - Maven -> Update Project



## V. XML

- src -> others.. -> Spring -> Spring Bean Configuration File
- bean 기능 뿐만 아니라 여러 기능도 사용할 수 있다.

```xml
<bean id="ltv" class="com.sds.LTV"></bean>
<bean id="stv" class="com.sds.STV"></bean>

// bean 을 추가 할 수 있다.
```

```java
AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");

STV stv = (STV) factory.getBean("stv");
// 이렇게 클래스를 받아서 사용한다.
stv.turnOn();

factory.close();
```



- xml 에서 지정된 클래스들은 프로젝트가 시작되면서 할당이 된다.

```
log4j:WARN No appenders could be found for logger (org.springframework.core.io.support.PathMatchingResourcePatternResolver).
log4j:WARN Please initialize the log4j system properly.

LTV Construct
STV Construct
// 시작하면서 이미 됨

LTV On
```

- 만약 이것이 싫다면

```xml
<bean id="ltv" class="com.sds.LTV" lazy-init="true"></bean>
<bean id="stv" class="com.sds.STV" lazy-init="true"></bean>
```

- 이렇게 나중에 할당 할 수 있다.



- 하지만 객체가 하나만 할당 된다.

```java
AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");

TV stv = (TV) factory.getBean("ltv");
stv.turnOn();

TV stv2 = (TV) factory.getBean("ltv");
stv2.turnOn();

if (stv == stv2) {
    System.out.println("same");
    // 같기 때문에 same 이 뜬다.
}

factory.close();
```

- 때문에 여러 개 할당받고 싶을 때에는

```xml
<bean id="ltv" class="com.sds.LTV" lazy-init="true" scope="prototype"></bean>

// prototype 이 되면 여러 개 할당 받게 된다.
<bean id="stv" class="com.sds.STV" lazy-init="true"></bean>
```



- 그럼 만약 클래스 안에 클래스를 지정하고 있다면 어떻게 해야 할까

```java
public class STV implements TV {
    private int size;
    private Speaker speaker;
}
```

- xml 에서 처리해 줄 수 있따!

```xml
<bean id="stv" class="com.sds.STV" lazy-init="true">
    <constructor-arg ref="sp"></constructor-arg>
</bean>
```



- 이렇게 모든 의존성 관계는 xml 로부터 이루어진다!

```xml
<bean id="ltv" class="com.sds.LTV" lazy-init="true" scope="prototype"></bean>
<bean id="stv" class="com.sds.STV" lazy-init="true">
    <constructor-arg index="1" ref="sp"></constructor-arg>
    <constructor-arg index="0" value="65"></constructor-arg>
</bean>
<bean id="sp" class="com.sds.Speaker"></bean>
```



- 다른 방법

```xml
<bean id="ltv" class="com.sds.LTV" lazy-init="true" scope="prototype">
    <property name="size" value="75"></property>
    <property name="speaker" ref="sp"></property>
    
    // getter, setter 를 만든 뒤, property 를 사용할 수 있다.
</bean>
```



- 또 다른 방법 : p 를 이용한
  - namespace 에서 p 를 활성화

```xml
<bean id="ltv" class="com.sds.LTV" lazy-init="true" scope="prototype"
      p:size="75" p:speaker-ref="sp">
</bean>
```

