<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN OK</h1>
	<h3>${id} ${cnt }님 환영 합니다.</h3>
	
	<c:choose>
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
	            3등급
	        </h4>
	    </c:when>
	    <c:otherwise>
	    	<h4>
	            F등급
	        </h4>
	    </c:otherwise>
	</c:choose>
	
	<c:forEach var="num" items="${list1 }">
		<h5>${num }</h5>
	</c:forEach>
	
	<table border="1">
		<tbody>
			<c:forEach var="it" items="${list2 }">
				<tr>
					<td>${it.name }</td>
					<td>${it.price }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>