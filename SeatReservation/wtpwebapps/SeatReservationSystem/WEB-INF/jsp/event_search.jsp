<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "dao.Event" %>
<% List<Event> events = (List<Event>) request.getAttribute("events");  %>

<!DOCTYPE html >
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>イベント検索ページ</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
        <a class="navbar-brand" href="#">イベント検索</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav3"
        		aria-controls="navbarNav3" aria-expanded="false" aria-label="Toggle navigation">
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
		<div class="row">
			<div class="col-12">
				<form action="./EventSearchServlet" method="GET">

					<input name="title" type="text">
					<input type="submit" class="btn btn-secondary" value="検索">
				</form>

					<div >
						<label>開催日時</label>
						<input type = "date" name = "start_at_min"  placeholder = "例:2000-07-01">から

						<input type = "date" name = "start_at_max"  placeholder = "例:2000-08-02">まで (※半角で入力して下さい)
					</div>
						<p>
							<%if(request.getAttribute("dateSearchError")!=null){ %>
							<font color="RED"><%=request.getAttribute("dateSearchError") %></font>
							<%}%>
						</p>

					<div>
						<label>イベント名</label>
						<input type = "text" name = "title" placeholder = "イベントABC2">
						<input type="submit"   value="検索">
						<!--  class="btn btn-secondary" -->
					</div>

				</form>



				<div class="p-3 bg-white rounded box-shadow">
					<h6 class="border-bottom border-gray pb-2 mb-0">イベント一覧</h6>
					<table class="table">
						<thead>
							<tr>

								<th scope="col">開催日時</th>
								<th scope="col">イベント名</th>
								<th scope="col">定員数</th>
								<th scope="col">整理券</th>
								<th scope="col">詳細</th>
							</tr>
						</thead>
						<tbody>
						<%    for( Event event : events){ %>
							<tr>
								<td class="text-muted"><%= event.getStart_at() %> </td>
								<td class="text-muted"><%= event.getTitle() %> </td>
								<td class="text-muted"><%= event.getCapacity() %> </td>
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
							</tr>
						<%   } %>
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