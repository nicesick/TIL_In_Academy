<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <div class="center_page">
	    <c:forEach var="p" items="${plist }">
	    	<div>
	    	<img src="img/${p.imgname }" width="100px">
	    	<h1>ID : ${p.id }, NAME : ${p.name }, PRICE : ${p.price }, REGDATE : ${p.regdate }</h1>
	    	</div>
	    </c:forEach>
    </div>