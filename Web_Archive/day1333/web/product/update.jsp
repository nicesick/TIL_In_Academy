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
	<h1>PRODUCT - UPDATE</h1>
	
	<form action="ask?type=product&cmd=updateimpl" method="POST" enctype="multipart/form-data">
		<p>
			<img src="img/${pUpdate.imgname }"><br>
			<input type="file" name="newimgname">
			<input type="hidden" name="imgname" value="${pUpdate.imgname }">
		</p>
		
		<h4>ID : <input type="text" name="id" value="${pUpdate.id }" readonly="readonly"></h4>
		<h4>NAME : <input type="text" name="name" value="${pUpdate.name }"></h4>
		<h4>PRICE : <input type="number" name="price" value="${pUpdate.price }"></h4>
		<input type="submit" value="SUBMIT">
	</form>
</body>
</html>