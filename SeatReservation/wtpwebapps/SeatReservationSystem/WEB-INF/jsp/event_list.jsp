<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "dao.Event" %>
<!-- List<Event>を宣言 -->
<% List<Event> events = (List<Event>) request.getAttribute("events");  %>

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>イベント一覧</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
        <a class="navbar-brand" href="#">イベント一覧</a>
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

	<thead>
		<tr>
			<th>
				<div class="form-group">
					<input type="button" onclick = "location.href='./NewEventServlet'"
							class="btn btn-secondary" value="イベント作成">
				</div>
			</th>
			<th>
				<div class="form-group">
					<input type="button" onclick = "location.href='./EventSearchServlet'"
							class="btn btn-secondary" value="イベント検索">
				</div>
			</th>
		</tr>
	</thead>


	<div class = "row">
		<div class = "col-6">
			<div class="form-group" style="text-align:center">
				<input type="button" onclick = "location.href='./NewEventServlet'"
						class="btn btn-secondary" value="イベント作成">
			</div>
		</div>
		<div class = "col-6">
			<div class="form-group" style="text-align:center">
				<input type="button" onclick = "location.href='./EventSearchServlet'"
						class="btn btn-secondary" value="イベント検索">
			</div>
		</div>
	</div>

	<main role="main" class="p-4">
		<div class="row">
			<div class="col-12">
				<div class="p-3 bg-white rounded box-shadow">
					<h6 class="border-bottom border-gray pb-2 mb-0">イベント一覧</h6>

						<form action="./EventListServlet" method="GET">
							<!--
							<input type="hidden" name="end" value="yes" >
							-->
							<input type="submit" name="end" class="btn btn-secondary" value="開催済み">
						</form>
						<form action="./EventListServlet" method="GET">
							<!--
							<input type="hidden" name="end" value="no" >
							-->
							<input type="submit" name="endNot" class="btn btn-secondary" value="未開催">
						</form>

					<div class = "row">
						<div class = "col-6">
							<form action="./EventListServlet" method="GET">
								<input type="submit" name="end" class="btn btn-success" value="開催済み">
							</form>
						</div>
						<div class = "col-6">
							<form action="./EventListServlet" method="GET">
								<input type="submit" name="endNot" class="btn btn-success" value="未開催">
							</form>
						</div>
					</div>

					<table class="table">
						<thead>
							<tr>
								<th scope="col">開催日時</th>
								<th scope="col">イベント名</th>
								<th scope="col">定員数</th>
								<th scope="col">整理券</th>
								<th scope="col">詳細</th>

								<th scope="col">  </th>

								<!-- イベント詳細ページへのリンクは無しにして、そのまま詳細を表示 -->
							</tr>
						</thead>
						<tbody>

						<% for(Event event : events){ %>
							<tr>
								<td class="text-muted"><%= event.getStart_at() %></td>
								<td class="text-muted"><%= event.getTitle() %></td>
								<td class="text-muted"><%= event.getCapacity() %></td>
								<td>
									<div class="form-group">
										<form action="./TicketIssueServlet" method="post">
										<input type="submit" class="btn btn-secondary" value="整理券発行">
										<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">
										<input type="hidden" name="capacity" value="<%= event.getCapacity()%>">
										</form>
									</div>
									<div class="form-group">

										<form action="./TicketsListServlet" method="post">

										<form action="./TicketsListServlet" method="get">

										<input type="submit" class="btn btn-secondary" value="整理券一覧">
										<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">

										</form>
									</div>
								</td>

								<td class="text-muted"><%=event.getDescription() %> </td>

								<td class="text-muted"><%=event.getDescription() %></td>
								<td>
									<div class="form-group">
										<form action="./EventKistServlet" method="get">

										<input type="submit" class="btn btn-secondary" value="整理券発行">
										<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">
										<input type="hidden" name="capacity" value="<%= event.getCapacity()%>">

										<input type="submit" name="あああああ" class="btn btn-danger" value="イベントを削除">

										</form>
									</div>
								</td>


							</tr>
						<% } %>
						</tbody>
					</table>
				</div>
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