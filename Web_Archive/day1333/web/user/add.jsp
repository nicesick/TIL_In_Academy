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
	<h1>USER - ADD</h1>
	
	<form action="ask?type=user&cmd=addimpl" method="POST">
		ID <input type="text" name="id"><br>
		PWD <input type="password" name="pwd"><br>
		NAME <input type="text" name="name"><br>
		<input type="submit" value="SUBMIT">
	</form>
</body>
</html>