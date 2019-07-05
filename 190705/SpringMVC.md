# SpringMVC

## I. Login

- 로그인 할 때 인자를 받는 방법

```java
@RequestMapping("loginimpl")
public ModelAndView loginImpl(ModelAndView mv, HttpServletRequest request, HttpSession session) {
    // mv 를 직접 받아도 되고, 필요한 정보를 argument 에다 넣기만 하면 자동으로 생성해 준다.
    mv.setViewName("main");

    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    String name = request.getParameter("name");
    return mv;
}
```

- 신기한 거!! : 만약 타겟 클래스에 필요한 정보가 다 있다면 그 클래스 자체로 만들 수 있다 : 다만 getter 랑 setter 가 있어야 된다.

```java
@RequestMapping("/useraddimpl.mc")
public ModelAndView userAddImpl(ModelAndView mv, User user) {
    mv.setViewName("main");

    System.out.println(user);
    // 받은 정보로 User 를 만들어 줬다 :) ㅎㅎㅎ
    return mv;
}
```

```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<div class="center_page">
    <h1>USER ADD PAGE</h1>
    <form action="useraddimpl.mc" method="POST">
        ID <input type="text" name="id"> <br>
        PWD <input type="password" name="pwd"> <br>
        NAME <input type="text" name="name"> <br>
// 다만 name 에 들어가는 이름은 User 클래스의 변수 이름과 같아야 한다.
        <input type="submit" value="submit">
    </form>
</div>
```



- 그럼 파일 올릴 때에는 어떻게 해야 할까
  - Product 에 새로운 변수를 선언
  - spring.xml 에 파일 업로드 선언
  - 얻은 파일을 이용해 사용

```java
public class Product {
	int id;
	String name;
	Double price;
	Date date;
	String imgname;
	MultipartFile mf;
    // 파일을 위한 클래스를 선언해주고, getter setter 추가
}
```

```xml
<!-- File Upload -->
<bean
      class="org.springframework.web.multipart.commons.CommonsMultipartResolver"
      id="multipartResolver">
    <property value="500000000" name="maxUploadSize" />
</bean>
```

```java
@RequestMapping("/productaddimpl.mc")
public ModelAndView productAddImpl(ModelAndView mv, Product product) {
    mv.setViewName("main");

    String imgname = product.getMf().getOriginalFilename();
    product.setImgname(imgname);

    System.out.println(product);
    return mv;
}
```



## II. Multi-Lang

- spring.xml 에 코드를 추가!

```xml
<!-- Multi language -->

<bean
      class="org.springframework.context.support.ResourceBundleMessageSource"
      id="messageSource">
    <property name="basenames">
        <list>
            <value>messages/messages</value>
        </list>
    </property>
</bean>
<bean
      class="org.springframework.web.servlet.i18n.SessionLocaleResolver"
      id="localeResolver">
</bean>
<mvc:interceptors>
    <bean
          class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
        <property value="lang" name="paramName" />
    </bean>
</mvc:interceptors>
```

- Messages 에 맞게 properties 를 설정한다.

```properties
welcome.txt = Welcome {0} {1}
register.txt = Register OK {0}
item.list=Item List
```

- main.jsp 에 taglib 를 추가

```jsp
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
```

- 타겟 위치에 spring 태그를 추가

```jsp
<spring:message code="welcome.txt" arguments="hi,mulcam">
</spring:message>
```



## III. 사진 업로드

```java
public static void saveFile(MultipartFile mf) {
    byte[] data;
    String imgname = mf.getOriginalFilename();
    String rootPath = System.getProperty("user.dir");
    String imgPath = rootPath + "/web/img/";

    try {
        data = mf.getBytes();
        FileOutputStream fo = new FileOutputStream(imgPath + imgname);
        fo.write(data);
        fo.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```

