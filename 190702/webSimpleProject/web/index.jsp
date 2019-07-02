<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>JIHUN Admin - Dashboard</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">
</head>

<body id="page-top">

	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

		<a class="navbar-brand mr-1" href="main.do">JiHun's DataBase</a>

		<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
	</nav>

	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="sidebar navbar-nav">
			<c:choose>
				<c:when test="${loginuser == null }">
					<li class="nav-item dropdown"><a class="nav-link"
						href="user.do?cmd=login"> <i
							class="fas fa-fw fa-tachometer-alt"></i> <span>Login</span>
					</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>${loginuser.name }</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">${loginuser.name } 님 반갑습니다:</h6>
							<a class="dropdown-item" href="login.do">Logout</a>
						</div></li>
				</c:otherwise>
			</c:choose>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>Users</span>
			</a>
				<div class="dropdown-menu" aria-labelledby="pagesDropdown">
					<h6 class="dropdown-header">User:</h6>
					<a class="dropdown-item" href="user.do?cmd=add">Add</a> <a
						class="dropdown-item" href="user.do?cmd=list">List</a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>Products</span>
			</a>
				<div class="dropdown-menu" aria-labelledby="pagesDropdown">
					<h6 class="dropdown-header">Product:</h6>
					<a class="dropdown-item" href="product.do?cmd=add">Add</a> <a
						class="dropdown-item" href="product.do?cmd=list">List</a>
				</div></li>
		</ul>

		<div id="content-wrapper">

			<div class="container-fluid">
				<c:choose>
					<c:when test="${center == null}">
						<jsp:include page="main.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						<jsp:include page="${center }.jsp"></jsp:include>
					</c:otherwise>
				</c:choose>

			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>CopyRight : JiHun_Lim 2019</span>
					</div>
				</div>
			</footer>

		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="vendor/chart.js/Chart.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script src="js/demo/chart-area-demo.js"></script>
	<script src="js/demo/chart-bar-demo.js"></script>
	<script src="js/demo/chart-pie-demo.js"></script>
</body>
</html>