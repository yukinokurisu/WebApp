<%@page import="dao.History"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List"%>

<%
List<History> histories = (List<History>)request.getAttribute("histories");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ranking</title>
</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>YouTubeRanking</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-secondary">
		<a class="navbar-brand" href="#"><img src="./img/icon0035.png" alt="アイコン" width="30" height="21"> 再生回数ランキング</a>
		<button class="navbar-toggler p-0 border-0" type="button"
			data-toggle="offcanvas">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>
	<main role="main" class="p-4">
		<div class="row">
			<div class="col-12">
				<div class="p-3 bg-white rounded box-shadow">
					<h6 class="border-bottom border-gray pb-2 mb-0">YouTubeランキング</h6>
					<table class="table">
						<thead>
							<tr>
								<th scope="col"> </th>
								<th scope="col">タイトル</th>
								<th scope="col">回数</th>
							</tr>
						</thead>
						<tbody>
							<% int count=1; %>
							<% for(History history : histories) { %>
							<tr>
								<td class="text-muted"><%=count %>位</td>
								<td class="text-muted"><%= history.getTitle() %></td>
								<td class="text-muted">	<%= history.getCount() %> </td>
								<%count++;%>
								<td class="text-muted">
									<iframe width="560" height="315" src="https://www.youtube.com/embed/<%=history.getYoutube_video_id() %>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
									</iframe>
								</td>
							</tr>
							<% } %>
						</tbody>
					</table>
				</div>
				<h6 class="border-bottom border-gray pb-2 mb-0"><a href= "./HistoryServlet" target="_blank" >履歴表示ページへ</a></h6>
			</div>
		</div>



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