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
				location.href='ask?type=product&cmd=delete&id=${pDetail.id }';
			}
		});
		
		$('button').eq(1).click(function(){
			var c = confirm('Are you Sure to update yours??');
			
			if (c) {
				location.href='ask?type=product&cmd=update&id=${pDetail.id }';
			}
		});
	});
</script>
</head>
<body>
	<h1>PRODUCT - DETAIL</h1>
	
	<p>
		<img src="img/${pDetail.imgname }">
	</p>
	<h4>ID : ${pDetail.id }</h4>
	<h4>NAME : ${pDetail.name }</h4>
	<h4>PRICE : ${pDetail.price }</h4>
	<h4>REGDATE : ${pDetail.regdate }</h4>
	
	<button>DELETE</button>
	<button>UPDATE</button>
</body>
</html>