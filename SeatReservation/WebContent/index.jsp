<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.Shop"%>
<% Shop shop = (Shop) request.getAttribute("shop"); %>
<%
  String message = (String) request.getAttribute("loginFailure");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座席予約</title>
</head>
<body>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<nav class="navbar navbar-expand-md navbar-dark bg-dark"> <a
	class="navbar-brand" href="#">Login</a>
<button class="navbar-toggler p-0 border-0" type="button"
	data-toggle="offcanvas">
	<span class="navbar-toggler-icon"></span>
</button>
</nav>

<% if (message != null) { %>
<p>
	<font color="RED"><%= message %></font>
</p>
<% } %>

<form action="./LoginServlet" method="post">
	<label for="shop_name">店舗名：</label> <input type="text" name="shop_name"
		id="shop_name" /><br /> <label for="password">パスワード：</label> <input
		type="password" name="password" id="password" /><br /> <input
		type="submit" class="btn btn-secondary" value="ログイン" />
</form>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>

</body>
</html>
