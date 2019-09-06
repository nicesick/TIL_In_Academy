<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <div class="center_page">
	    <c:forEach var="u" items="${ulist }">
	    	<h1>ID : ${u.id }, NAME : ${u.name }, PWD : ${u.pwd }</h1>
	    </c:forEach>
    </div>