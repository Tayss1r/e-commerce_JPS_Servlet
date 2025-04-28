<%@page import="miniP.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth == null || !"admin@example.com".equals(auth.getEmail())) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<meta charset="UTF-8">
<title>Edit Product</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Edit Product</div>
			<div class="card-body">
				<form action="edit-product" method="post">
					<input type="hidden" name="id" value="${product.id}">
					
					<div class="form-group">
						<label>Product Name</label> <input type="text"
							class="form-control" name="name" value="${product.name}" required>
					</div>
					<div class="form-group">
						<label>Category</label> <input type="text"
							class="form-control" name="category" value="${product.category}" required>
					</div>
					<div class="form-group">
						<label>Price</label> <input type="number" step="0.01"
							class="form-control" name="price" value="${product.price}" required>
					</div>
					<div class="form-group">
						<label>Image File Name</label> <input type="text"
							class="form-control" name="image" value="${product.image}" required>
					</div>
					<div class="text-center mt-3">
						<button type="submit" class="btn btn-primary">Update Product</button>
						<a href="index.jsp" class="btn btn-secondary">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html> 