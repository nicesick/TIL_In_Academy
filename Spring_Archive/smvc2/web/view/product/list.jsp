<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <div class="center_page">
	    <c:forEach var="p" items="${plist }">
	    	<h1>ID : ${p.id }, NAME : ${p.name }</h1>
	    </c:forEach>
    </div>