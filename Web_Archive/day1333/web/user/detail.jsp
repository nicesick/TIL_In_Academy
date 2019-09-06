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
	$(document).ready(function(){
		$('button').eq(0).click(function(){
			var c = confirm('Are you Sure to delete yours??');
			
			if (c) {
				location.href='ask?type=user&cmd=delete&id=${userDetail.id }';
			}
		});
		
		$('button').eq(1).click(function(){
			var c = confirm('Are you Sure to update yours??');
			
			if (c) {
				location.href='ask?type=user&cmd=update&id=${userDetail.id }';
			}
		});
	});
</script>
</head>
<body>
	<h1>USER - DETAIL</h1>
	
	<p>
		<img src="img/${userDetail.id }.jpg">
	</p>
	<h4>ID : ${userDetail.id }</h4>
	<h4>PWD : ${userDetail.pwd }</h4>
	<h4>NAME : ${userDetail.name }</h4>
	
	<button>DELETE</button>
	<button>UPDATE</button>
</body>
</html>