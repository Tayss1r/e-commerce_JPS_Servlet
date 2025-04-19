<%@page import="miniP.connection.DBCon" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="miniP.model.*"%>
    
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
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