<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<div class="center_page">
	<h1>PRODUCT ADD PAGE</h1>
	
	<form action="productaddimpl.mc" method="POST" enctype="multipart/form-data">
		<input type="file" name="mf"> <br>
		NAME <input type="text" name="name"> <br>
		PRICE <input type="number" name="price"> <br>
		<input type="submit" value="submit">
	</form>
</div>