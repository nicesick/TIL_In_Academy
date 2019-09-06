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
	<h1>Main Page</h1>
	
	<h3><a href="ask?type=user&cmd=add">USER ADD</a></h3>
	<h3><a href="ask?type=user&cmd=list">USER LIST</a></h3>
	
	<h3><a href="ask?type=product&cmd=add">PRODUCT ADD</a></h3>
	<h3><a href="ask?type=product&cmd=list">PRODUCT LIST</a></h3>
</body>
</html>
