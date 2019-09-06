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
<script>
	
</script>
</head>
<body>
	<h1>USER - UPDATE</h1>
	
	<form action="ask?type=user&cmd=updateimpl" method="POST">
		<p>
			<img src="img/${userUpdate.id }.jpg">
		</p>
		<h4>ID : <input type="text" name="id" value="${userUpdate.id }" readonly="readonly"></h4>
		<h4>PWD : <input type="password" name="pwd" value="${userUpdate.pwd }"></h4>
		<h4>NAME : <input type="text" name="name" value="${userUpdate.name }"></h4>
		<input type="submit" value="SUBMIT">
	</form>
</body>
</html>