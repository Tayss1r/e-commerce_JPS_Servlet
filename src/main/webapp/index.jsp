<%@page import="miniP.connection.DBCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="miniP.model.*"%>
<%@page import="miniP.dao.ProductDao"%>
<%@page import="java.util.*"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("person", auth);
}
ProductDao pd = new ProductDao(DBCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}

boolean isAdmin = false;
if (auth != null && "admin@example.com".equals(auth.getEmail())) {
	isAdmin = true;
}
%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-image/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Price:
							<%=p.getPrice()%>
							dt
						</h6>
						<h6 class="category">
							Category:
							<%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<%
							if (isAdmin) {
							%>
							<div class="admin-controls">
								<a class="btn btn-warning btn-sm"
									href="edit-product?id=<%=p.getId()%>">Edit</a> <a
									class="btn btn-danger btn-sm"
									href="delete-product?id=<%=p.getId()%>"
									onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
							</div>
							<%
							} else {
							%>
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add
								to Cart</a> <a class="btn btn-primary"
								href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no products");
			}
			%>

		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>

</body>
</html>