<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="main.do">Dashboard</a></li>
	<li class="breadcrumb-item active">Add Product</li>
</ol>

<div class="container">
	<div class="card card-register mx-auto mt-5">
		<div class="card-header">Add Product</div>
		<div class="card-body">
			<form id="form_info" action="product.do?cmd=addimpl" method="post"
				, enctype="multipart/form-data">
				<div class="form-group">
					<div class="form-label-group">
						<input type="file" id="inputImg" class="form-control"
							placeholder="ID" required="required" name="inputImg"> <label
							for="inputImg">Img</label>
					</div>
				</div>
				<div class="form-group">
					<div class="form-label-group">
						<input type="number" id="inputId" class="form-control"
							placeholder="ID" name="inputId" value="auto" readonly="readonly"> <label
							for="inputId">ID</label>
					</div>
				</div>
				<div class="form-group">
					<div class="form-label-group">
						<input type="text" id="inputName" class="form-control"
							placeholder="Name" required="required" name="inputName">
						<label for="inputName">Name</label>
					</div>
				</div>
				<div class="form-group">
					<div class="form-label-group">
						<input type="number" id="inputPrice" class="form-control"
							placeholder="Price" required="required" name="inputPrice">
						<label for="inputPrice">Price</label>
					</div>
				</div>
				<input type="submit" class="btn btn-primary btn-block"
					value="Submit">
			</form>
			<div class="text-center">
				<a class="d-block small mt-3" href="index.jsp">Main</a>
			</div>
		</div>
	</div>
</div>