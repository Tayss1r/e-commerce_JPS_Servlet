<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="miniP.model.User" %>
<%
    User auth = (User) session.getAttribute("auth");
    if (auth == null || !"admin@example.com".equals(auth.getEmail())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/includes/head.jsp"%>
<meta charset="UTF-8">
<title>Add New Product</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
    
    <div class="container mt-5">
        <h2>Add New Product</h2>
        <form action="add-product" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="category">Category</label>
                <input type="text" name="category" id="category" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" step="0.01" name="price" id="price" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="image">Image</label>
                <input type="text" name="image" id="image" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">Add Product</button>
        </form>
    </div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>
