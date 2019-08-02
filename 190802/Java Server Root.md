# Java Server Root

## I. 하는법 (윈도우에서)

- smvc1 에서 속성에 들어간다.
- 웹 프로젝트 세팅에 들어간다.
- / 으로 바꾼다.
- 끄읕



## II. 하는 법 (리눅스에서)

- ROOT 를 지운다.
- 이름을 ROOT로 바꾼다.
- 끄읕



## III. log4j

- web.xml 에 listener 를 추가

```xml
<listener>
    <listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
</listener>
<context-param>
    <param-name>log4jConfigLocation</param-name>
    <param-value>/WEB-INF/config/log4j.properties</param-value>
</context-param>
```

- pom.xml 에서 org.apache.log4j 패키지 다운

```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
    <exclusions>
        <exclusion>
            <groupId>javax.mail</groupId>
            <artifactId>mail</artifactId>
        </exclusion>
        <exclusion>
            <groupId>javax.jms</groupId>
            <artifactId>jms</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.sun.jdmk</groupId>
            <artifactId>jmxtools</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.sun.jmx</groupId>
            <artifactId>jmxri</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

- Log 속성을 위한 properties 생성

```properties
###############################################################################
#
#	log4j  
#
###############################################################################

log4j.logger.user = DEBUG, console, user
log4j.logger.work = DEBUG, console, work
log4j.logger.data = DEBUG, console, data

# Console output... 
log4j.appender.console= org.apache.log4j.ConsoleAppender 
log4j.appender.console.layout = org.apache.log4j.PatternLayout 
log4j.appender.console.layout.ConversionPattern = [%d] %-5p %L %m%n 

# user
log4j.appender.user.Threadhold=DEBUG
log4j.appender.user = org.apache.log4j.DailyRollingFileAppender 
log4j.appender.user.DatePattern = '.'yyyy-MM-dd
log4j.appender.user.layout = org.apache.log4j.PatternLayout 
log4j.appender.user.layout.ConversionPattern = %-5p %L [%d] - %m%n
log4j.appender.user.File = c:/logs/user.log   

# work
log4j.appender.work.Threadhold=DEBUG
log4j.appender.work = org.apache.log4j.DailyRollingFileAppender 
log4j.appender.work.DatePattern = '.'yyyy-MM-dd
log4j.appender.work.layout = org.apache.log4j.PatternLayout 
log4j.appender.work.layout.ConversionPattern = %-5p , %L , %d , %m%n
log4j.appender.work.File = c:/logs/work.log 

# data
log4j.appender.data.Threadhold=DEBUG
log4j.appender.data = org.apache.log4j.DailyRollingFileAppender 
log4j.appender.data.DatePattern = '.'yyyy-MM-dd
log4j.appender.data.layout = org.apache.log4j.PatternLayout 
log4j.appender.data.layout.ConversionPattern = %5p  [%d{MMdd HHmmss}] %F:%L:%M - %m%n
log4j.appender.data.File = c:/logs/data.log 
```



- AOP 가 적용된 java 파일 추가

```java
@Service
@Aspect
public class Loggers {
    private Logger work_log = 
        Logger.getLogger("work"); 
    private Logger user_log = 
        Logger.getLogger("user"); 
    private Logger data_log = 
        Logger.getLogger("data"); 

    // before
    @Before("execution(* com.hw.controller..*Controller.*(..))")
    public void logging(JoinPoint jp) {
        work_log.debug(jp.getSignature().getName());
        //work_log.debug(jp.getArgs()[0].toString());
    }
```

- spring.xml 에 aop 추가!!!!!

```xml
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```



## IV. Listener

- 요청이 들어올 때의 처리는 어떻게 하는걸까?
- web.xml 에 필요한 listener 추가

```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/config/springex.xml</param-value>
</context-param>
```

- springex.xml 파일 추가

```xml
<bean id="exeptionResolver" 
      class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <props>
            <prop key="org.springframework.dao.DuplicateKeyException">
                error
                // error.jsp 로 간다.
            </prop>
            // 여기에 exception 들을 써주면서 처리할 수 있다.
        </props>
    </property>
</bean>
```

