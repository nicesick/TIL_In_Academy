<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<div class="center_page">
	<h1>USER ADD PAGE</h1>
	<form action="useraddimpl.mc" method="POST">
		ID <input type="text" name="id"> <br>
		PWD <input type="password" name="pwd"> <br>
		NAME <input type="text" name="name"> <br>
		<input type="submit" value="submit">
	</form>
</div>