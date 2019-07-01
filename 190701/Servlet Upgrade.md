# Servlet Upgrade

## I. Dispatcher 만들기

- web.xml 을 생성하고, servlet 코드를 집어넣는다.

```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>web.dispatcher.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>*.do</url-pattern>
    //*.do 라고 되어있는 요청들은 무조건 dispatcher 에게 보내준다.
    // 따라서 앞으로 만드는 요청은 모두 *.do 로 이루어질 거다!!
</servlet-mapping>
```

- index.html 에서 main.do 로 이동시켜줌으로써, dispatcher에게 넘겨준다.

```html
<script>
	location.href="main.do";
</script>
```



## II. 부분 페이지 변경

- jsp 에서 부분적으로 페이지를 변경할 수 있다.

```jsp
<c:choose>
    <c:when test="${center == null }">
        <jsp:include page="center.jsp"></jsp:include>
        //jsp 구문을 이용해 다른 페이지로 넘어 갈 수 있다.
    </c:when>
    <c:otherwise>
        <jsp:include page="${center }.jsp"></jsp:include>
    </c:otherwise>
</c:choose>
```

