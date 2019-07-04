# ORM(MyBatis)

## I. 설치

- namespace 에서 tx 를 추가한다.

```xml
<tx:annotation-driven
		transaction-manager="txManager" />
```



- DataBase 를 세팅한다.

```xml
<!-- 1. Database Setting -->

<bean
      class="org.springframework.jdbc.datasource.DriverManagerDataSource"
      id="dataSource">

    <property value="oracle.jdbc.driver.OracleDriver"
              name="driverClassName" />

    <property value="jdbc:oracle:thin:@70.12.114.54:1521:xe"
              name="url" />

    <property value="db" name="username" />
    <property value="db" name="password" />

</bean>
```

- Oracle 은 오픈소스가 아니기 때문에 Maven 에서 추가할 수 없다.
- 따라서 Oracle6_g 를 따로 External Library 로 추가해 준다.



- 트랜젝션을 세팅한다.

```xml
<!-- 2. Transaction Setting -->

<bean
      class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
      id="txManager">

    <property name="dataSource" ref="dataSource" />
</bean>
```



- 말바티스를 세팅한다.

```xml
<!-- 3. MyBatis Setting -->

<bean class="org.mybatis.spring.SqlSessionFactoryBean"
      id="sqlSessionFactory">

    <property name="dataSource" ref="dataSource" />
    <property value="classpath:com/config/mybatis.xml"
              name="configLocation" />
</bean>
```

- 말바티스에 필요한 xml 들을 추가한다.
- 말바티스에 필요한 mapper 들을 추가한다.

- 말바티스와 spring 을 연동한다.

```xml
<!-- 4. Spring Mybatis Connect -->

<bean class="org.mybatis.spring.SqlSessionTemplate"
      id="sqlSessionTemplate">

    <constructor-arg ref="sqlSessionFactory" />

</bean>
```

- mapper 를 세팅한다.

```xml
<!-- 5. Mapper Setting -->

<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property value="com.mapper" name="basePackage" />
</bean>
```



## II. 사용

- Dao에 Mapper 를 추가하고 하고 싶은 걸 한다.

```java
public class UserDao implements Dao<String, User> {
	
	@Autowired
	private UserMapper um;
	
	@Override
	public void insert(User v) throws Exception {
		um.insert(v);
	}
}
```

- 만약 리턴타입이 User 라고 한다면 User 형식으로 까지 반환해 준다.
- 하지만 User 에 기본 Constructor 가 없다면 오류가 나니 조심!!

```java
public class User {
	String id;
	String pwd;
	String name;

	public User() {
        // 잊지 말자!!
	}
	
	public User(String id, String pwd, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
}
```



## III. 트랜젝션 처리

- 트랜잭션이 여러개일 때, 도중에 터져도 지금까지 한 건 commit 된다.
- 따라서 @Transactional 을 쓰면 도중에 터지면 Rollback 된다.

```java
public interface Dao<K, V> {
    @Transactional
    public abstract void insert(V v) throws Exception;
    @Transactional
    public abstract void update(K k, V v) throws Exception;
    @Transactional
    public abstract void delete(K k) throws Exception;

    public abstract V select(K k) throws Exception;
    public abstract ArrayList<V> selectAll() throws Exception;
}
```



## IV. resultMap

- 받은 결과들을 사용자 정의로 원하는 모습으로 받을 수 있다.

```javascript
	<resultMap type="user" id="um">
		<result property="id" column=id/>
		<result property="name" column=name/>
	</resultMap>
```



## V. Like

- 검색을 할 때, LIKE 를 이용해 검색할 수 있다.

```xml
<mapper namespace="com.mapper.SearchMapper">

    <select id="search" parameterType="String" resultType="user">
        SELECT * FROM T_USER WHERE NAME LIKE '%'||#{k}||'%'
    </select>
</mapper>
```



# SpringMVC

## I.  설치

- Dynamic Web Project 생성
- Spring 및 Maven 환경 추가
- Web.xml 에 필요한 소스 추가

```xml
<servlet>
    <servlet-name>action</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    // 신기하게도 spring 에서 Dispatcher 를 지원한다!!
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/config/spring.xml</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>action</servlet-name>
    <url-pattern>*.mc</url-pattern>
</servlet-mapping>

// 한글 깨지는 거 방지
<filter>
    <filter-name>enc</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>enc</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



- config 밑에 spring.xml 추가
- ViewResolver 정보를 추가하고 web 폴더 및에 view 폴더 추가

```xml
<mvc:annotation-driven />

<!-- ViewResolver -->

<bean
      class="org.springframework.web.servlet.view.InternalResourceViewResolver"
      id="viewResolver">
    <property value="/view/" name="prefix" />
    <property value=".jsp" name="suffix" />
    <property value="0" name="order" />
</bean>
```

- index.html 에서 main.do 로 보냄

```html
<script>
	location.href="main.mc";
</script>
```

- MainController 클래스 생성

```java
@Controller
public class MainController {
	public ModelAndView main() {
        // Model : 필요한 데이타
        // View : 필요한 화면
        // 필요한 것을 전달해준다!!
		ModelAndView mv = new ModelAndView("main");
        mv.setViewName("main");
        // ViewResolver 에서 view 폴더를 등록시켰으므로,
        // 이럴경우, view/main.jsp 페이지로 간다.
        
        mv.addObject("","");
        // 이거는 setAddtirube 함수와 같다!!
        // 데이터 전송할 때 이걸 쓰면 됨
        
		return mv;
        // RequestDispatcher 에서의 forward 와 같다.
	}
}
```

