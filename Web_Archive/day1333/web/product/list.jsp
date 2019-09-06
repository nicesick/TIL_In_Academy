<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<style>
	img {
		width : 200px;
	}
</style>
</head>
<body>
	<h1>PRODUCT - LIST</h1>
	
	<c:forEach var="p" items="${plist }">
		<a href="ask?type=product&cmd=detail&id=${p.id }">
			<h3><img src="img/${p.imgname }"></h3>
			<h3>ID : ${p.id }, NAME : ${p.name }, PRICE : ${p.price }, REGDATE : ${p.regdate }</h3>
		</a>
	</c:forEach>
</body>
</html>