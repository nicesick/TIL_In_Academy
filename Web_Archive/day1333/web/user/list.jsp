<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<h1>USER - LIST</h1>
	<c:forEach var="user" items="${users }">
		<p>
			<a href="ask?type=user&cmd=detail&id=${user.id }">ID : ${user.id }</a>
		</p>
	</c:forEach>
</body>
</html>