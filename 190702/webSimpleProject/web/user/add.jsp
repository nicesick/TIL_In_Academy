<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="main.do">Dashboard</a></li>
	<li class="breadcrumb-item active">Add User</li>
</ol>

<div class="container">
	<div class="card card-register mx-auto mt-5">
		<div class="card-header">Add User</div>
		<div class="card-body">
			<form id="form_info" action="user.do?cmd=addimpl" method="post">
				<div class="form-group">
					<div class="form-label-group">
						<input type="text" id="inputId" class="form-control"
							placeholder="ID" required="required" name="inputId"> <label
							for="inputId">ID</label>
					</div>
				</div>
				<div class="form-group">
					<div class="form-label-group">
						<input type="text" id="inputName" class="form-control"
							placeholder="Name" required="required" name="inputName"> <label
							for="inputName">Name</label>
					</div>
				</div>
				<div class="form-group">
					<div class="form-row">
						<div class="col-md-6">
							<div class="form-label-group">
								<input type="password" id="inputPassword" class="form-control"
									placeholder="Password" required="required" name="inputPassword"> <label
									for="inputPassword">Password</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-label-group">
								<input type="password" id="confirmPassword" class="form-control"
									placeholder="Confirm password" required="required" name="confirmPassword"> <label
									for="confirmPassword">Confirm password</label>
							</div>
						</div>
					</div>
				</div>
				<input type="submit" class="btn btn-primary btn-block" value="Submit">
			</form>
			<div class="text-center">
				<a class="d-block small mt-3" href="index.jsp">Main</a>
			</div>
		</div>
	</div>
</div>