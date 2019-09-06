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
	<h1>RESULT</h1>
	<h2>${company }</h2>
	<h2>${car }</h2>
			
	<table>
		<tbody>
			<tr>
				<c:forEach var="lap" items="${laptop }">
					<td><h2>${lap }</h2></td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
</body>
</html>