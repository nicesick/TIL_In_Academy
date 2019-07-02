<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="main.do">Dashboard</a></li>
	<li class="breadcrumb-item active">Main</li>
</ol>

<!-- Icon Cards-->
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-primary o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fas fa-fw fa-comments"></i>
				</div>
				<div class="mr-5">User : Add</div>
			</div>
			<a class="card-footer text-white clearfix small z-1"
				href="user.do?cmd=add"> <span class="float-left">Go to
					page</span> <span class="float-right"> <i
					class="fas fa-angle-right"></i>
			</span>
			</a>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-warning o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fas fa-fw fa-list"></i>
				</div>
				<div class="mr-5">User : List</div>
			</div>
			<a class="card-footer text-white clearfix small z-1"
				href="user.do?cmd=list"> <span class="float-left">Go to
					page</span> <span class="float-right"> <i
					class="fas fa-angle-right"></i>
			</span>
			</a>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fas fa-fw fa-shopping-cart"></i>
				</div>
				<div class="mr-5">Product : Add</div>
			</div>
			<a class="card-footer text-white clearfix small z-1"
				href="product.do?cmd=add"> <span class="float-left">Go to
					page</span> <span class="float-right"> <i
					class="fas fa-angle-right"></i>
			</span>
			</a>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-danger o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fas fa-fw fa-life-ring"></i>
				</div>
				<div class="mr-5">Product : List</div>
			</div>
			<a class="card-footer text-white clearfix small z-1"
				href="product.do?cmd=list"> <span class="float-left">Go
					to Page</span> <span class="float-right"> <i
					class="fas fa-angle-right"></i>
			</span>
			</a>
		</div>
	</div>
</div>