<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="index.jsp">Dashboard</a></li>
	<li class="breadcrumb-item active">List User</li>
</ol>

<!-- DataTables Example -->
<div class="card mb-3">
	<div class="card-header">
		<i class="fas fa-table"></i> User
	</div>
	
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>PWD</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>PWD</th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="u" items="${ulist }">
						<tr>
							<td>${u.id }</td>
							<td>${u.name }</td>
							<td>${u.pwd }</td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="card-footer small text-muted">Updated yesterday at
		11:59 PM</div>
</div>