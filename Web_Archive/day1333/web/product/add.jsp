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
	<h1>PRODUCT - ADD</h1>
	
	<form action="ask?type=product&cmd=addimpl" method="POST" enctype="multipart/form-data">
		NAME <input type="text" name="name"><br>
		PRICE <input type="number" name="price"><br>
		IMG <input type="file" name="imgname"><br>
		<input type="submit" value="SUBMIT">
	</form>
</body>
</html>