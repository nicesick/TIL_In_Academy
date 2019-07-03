Spring Xml

## I. import

- xml 을 import 할 수 있따.

```xml
<import resource="mydao.xml"/>
<import resource="mybiz.xml"/>
```

- init-method, destory-method 를 사용해 초기화를 해 줄 수 있다.

```xml
<bean id="userbiz" class="com.user.UserBiz" init-method="startBiz" destroy-method="endBiz"></bean>
```



## II. Annotation

- namespace 에서 context 를 추가한다.

- 클래스들을 스캔한다.

```xml
<context:component-scan base-package="springtv"></context:component-scan>
```

- 해당 패키지 클래스에 @Component 를 추가한다.

```java
@Component("ltv")
public class LTV implements TV {
}
```

- 클래스 안의 다른 클래스를 할당하려면 @Autowired 를 추가한다.

```java
@Component("stv")
public class STV implements TV {
    String status;
    int volume;

    @Autowired
    // 단 하나의 Speaker 형식만 있을 때만 사용할 수 있다.
    Speaker speaker;

    public STV() {
        System.out.println("Constructor STV ...");
    }
}
```

- 만약 여러개의 Speaker 가 있고, 그 중에 선택해야 한다면 @Qualifier 를 추가한다.

```java
@Autowired
@Qualifier("ms")
Speaker speaker;
```



- 하지만 이렇게 쓰게 되면 바꿀 때마다 코드를 바꿔줘야 한다.

```xml
<bean id="ms" class="springtv.HarmanSpeaker"></bean>

// 이렇게 필요한 스피커만 추가해서 Autowired 되게 한다.
```



- Annotation 과 Bean 을 섞어서 쓴다.
- 다른 용어

```java
@Component := @Repository := @Service := @Controller
    // @Servie : Biz 에서 쓰임
    // @Repository : Dao 에서 쓰임
    // @Controller : ??
    
@Autowired, @Qualifier := @Resource
```



## III. AOP

- Aspect Oriented Programming
- 하는 방법
  - XML
  - Annotation



- 우선 xml 의 namespace 에서 aop 를 추가한다.
- bean 을 만들어 log 를 맵핑시킨다.

```xml
<bean id="log" class="com.frame.LogAdvice"></bean>
```

- aop 를 만들어 틀을 만들어 준다.

```xml
<aop:config>
    // 어디에 적용하는가?
    <aop:pointcut expression="execution(* com.*..*Biz.*(..))" id="id01"/>
    // 무엇을 적용하는가?
    <aop:aspect ref="log">
        <aop:before pointcut-ref="id01" method="printLog"/>
    </aop:aspect>
</aop:config>
```



- before, after, around 에서 특히 around 는 특정 함수를 만들어 줘야한다.

```
public class LogAdvice {
    public void printLog() {
    Date d = new Date();
    System.out.println(d + " : in prograss...");
    }

    public void exLog() {
    Date d = new Date();
    System.out.println(d + " : Exception...");
    }

    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Before Action...");

    Object obj = pjp.proceed();

    System.out.println("After Action...");

    return obj;
    }
}
```

```xml
<aop:around pointcut-ref="id01" method="aroundLog"/>
```



## IV. JoinPoint

- 실행되는 함수의 정보를 가지고 오기 위해 JoinPoint 라는 클래스를 쓸 수 있다.

```java
public void beforeLog(JoinPoint jp) {
    String method = jp.getSignature().getName();
    Object[] args = jp.getArgs();

    System.out.println(method + " : " + args[0]);
    System.out.println("BEFORE : in prograss...");
}
```

- 실행되고 나서 리턴되는 값을 참조하려면 2가지 절차가 필요하다.

```xml
<aop:after-returning pointcut-ref="id01" method="afterLog" returning="returnObj"/>
// returning 을 이용해 리턴 값을 알려준다.
```

```java
public void afterLog(JoinPoint jp, Object returnObj) {
    System.out.println(returnObj);
    System.out.println("AFTER : in prograss...");
}
```

- after-throwing 에서는 returning 대신 throwing 이 있다.

```xml
<aop:after-throwing pointcut-ref="id02" method="exLog" throwing="ex"/>
```



## V. AOP - Annotation

- xml 에 자동 스캔을 실행한다.

```xml
<aop:aspectj-autoproxy />
```

- 해당 클래스에 @Service, @Aspect 를 달아준다.

```java
@Service
@Aspect
public class LogAdvice {
}
```

- 실행될 조건을 메소드 위에 붙여준다.

```java
@Before("execution(* com..*Biz.insert(..))")
public void beforeLog(JoinPoint jp) {
    // JoinPoint 그대로 사용해도 된다.
    
    String method = jp.getSignature().getName();
    Object[] args = jp.getArgs();

    System.out.println(method + " : " + args[0]);
    System.out.println("BEFORE : in prograss...");
}
```

- 만약 AfterReturning 처럼 여러 인자가 있다고 한다면,

```java
@AfterReturning(pointcut="execution(* com..*Biz.select(..))",
                returning="returnObj")
public void afterLog(JoinPoint jp, Object returnObj) {
    System.out.println(returnObj);
    System.out.println("AFTER : in prograss...");
}
```

