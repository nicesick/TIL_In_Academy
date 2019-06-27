# 서버 Side

- Dynamic Web project 를 만들 때, Generate web.xml deployment descriptor 를 체크해 준다.



## I. Servlet

- html 에 반응을 보내기 위해서는 2가지 방법이 있다.



1. 직접 필요한 것을 쏜다.

```java
response.setContentType("text/html;charset=UTF-8");
response.setCharacterEncoding("UTF-8");

PrintWriter out = response.getWriter();
out.println("<h1>LOGIN OK</h1>");
out.println("<h1>" + id + "</h1>");
```



2. redirect 시킨다.

```java
response.sendRedirect("ok.jsp");
```



## II. JSP

- JSP 이지만 실행 할 때에는 Servlet 서버처럼 바뀐다.
- JSP 파일에 있는 html 코드들은 out.print() 로 실행된다.
- 따라서 JSP 는 위의 1번을 해결하기 위해 만들어진 것 같다.



- Servlet 에서 JSP 로 파라미터 던져주기
  1. get 방식으로 보내주기
  2. jsp 는 결국 doGet 함수 안에 존재하기 때문에 바로 접근
  3. RequestDispatcher 사용 (추천)

```java
response.sendRedirect("ok.jsp?id=" + id + "&pwd=" + pwd);
```

```jsp
<%
	String id = request.getParameter("id");
%>

// 하지만 jsp 와 java 언어가 섞이기 때문에 비추
```

```java
request.setAttribute("id", id);
RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
rd.forward(request, response);
```

```jsp
<h1>LOGIN OK</h1>
<h3>${id} 님 환영 합니다.</h3>	// 받은 attribute 는 이런식으로 사용
```



##  III. Library

- 기본적으로 제공하는 것

  - Apache
  - JRE SystemLibrary

- 이외에는 따로 등록해야함

  - ojdbc6_g : 데이터베이스

  - json-simple-1.1.1 : JSON

  - jstl : JSP 에 필요한 라이브러리(JAVA 코딩을 하지않게 하기 위함)

    

  - standard : standard 와 cos 는 jstl 을 사용하기 위한 라이브러리

  - cos



- 이 라이브러리들을 web -> WEB_INF -> lib 에 등록

- jsp 파일에 등록한다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
```

- jsp 는 html, el, jstl 만 사용해야만 한다.



## IV. JSTL

```jsp
<c:
```

- <c: 로 시작한다.

```jsp
<c:if test="${cnt > 5}">				// if문
	<h3>
        HIGH
    </h3>
</c:if>


<c:choose>								// if-else 문
	<c:when test="${cnt >= 9}">
    	<h4>
            1등급
        </h4>
    </c:when>
    <c:when test="${cnt >= 8 && cnt < 9}">	
    	<h4>
            2등급
        </h4>
    </c:when>
    <c:when test="${cnt >= 7 && cnt < 8}">
    	<h4>
            2등급
        </h4>
    </c:when>
    <c:otherwise>
    	<h4>
            F등급
        </h4>
    </c:otherwise>
</c:choose>


<c:forEach var="num" items="${list1}">	// for 문 arrayList list1
	<h4>
        ${num}
    </h4>
</c:forEach>


<c:forEach var="it" items="${list2 }">	// for 문 arrayList class
    <tr>
        <td>${it.name }</td>
        <td>${it.price }</td>
    </tr>
</c:forEach>
```



## V. Servlet 분할

```jsp
<h3><a href="ask?type=user&cmd=add">USER ADD</a></h3>
```

- 이와같이 ask 라는 servlet 에게 type=user 와 cmd=add 라는 요청을 보냈다.

- 하지만 ask 에서 모두 분할을 한다면 코드의 길이가 너무 커진다.
- 따라서, ask 밑에 user 라는 servlet 을 만든다면 분할이 가능하다!
- 또한 이 servlet 은 암호화를 복호해주는 역할도 할 수 있다.



```java
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String type = request.getParameter("type");		// Type 에 따라 경로를 바꿀 수 있다.

    if (type.equals("") || type == null) {
        type = "index.jsp";
    }

    RequestDispatcher rd = request.getRequestDispatcher(type);
    rd.forward(request, response);
}
```

