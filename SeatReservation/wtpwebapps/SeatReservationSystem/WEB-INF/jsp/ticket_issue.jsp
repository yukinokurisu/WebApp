<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.Event" %>
<%@ page import = "dao.Ticket" %>
<% Event event = (Event) request.getAttribute("event"); %>
<% Ticket ticket = (Ticket) request.getAttribute("ticket2"); %>
<%
  String message = (String) request.getAttribute("IssueFailure");
%>
<% int capacity = (int) request.getAttribute("capacity"); %>
<% int tickets = (int) request.getAttribute("tickets"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>整理券発行</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
        <a class="navbar-brand" href="#">整理券発行</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav3" aria-controls="navbarNav3" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav3">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="./TopServlet">Top <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./Logout">ログアウト</a>
                </li>
            </ul>
        </div>
    </nav>

	<main role="main" class="p-4">

	<% System.out.println(tickets + "jsp"); %>


	<% if (tickets >= capacity) { %>
	  <p><%= message %></p>
	<% } else { %>
		<div class="row">
			<div class="col-12">
					<div class = "ticket">
						<p>整理券番号: <%= ticket.getIdentification_code()%></p>
					</div>
					<div class = "ticket">
						<p>イベント名: <%= event.getTitle() %></p>
					</div>
					<div class = "form-group">
						<p>開催日時: <%= event.getStart_at() %></p>
					</div>
					<div class="form-group">
						<form action="./PrintServlet" method="get">
							<input type="submit" value="印刷ページへ" class="btn btn-secondary">
							<input type="hidden" name="identification_code" value="<%= ticket.getIdentification_code()%>">
						</form>
					</div>
			</div>
		</div>
	<% } %>
	</main>



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