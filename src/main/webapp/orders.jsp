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
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>

orders page

<%@include file="/includes/footer.jsp"%>

</body>
</html>