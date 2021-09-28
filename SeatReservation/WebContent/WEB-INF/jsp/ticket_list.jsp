<%@page import="dao.Ticket"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<!-- List<tickets>を宣言 -->
<% List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");  %>

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>発行済みチケット</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
        <a class="navbar-brand" href="#">発行済みチケット</a>
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
		<div class="col-12">
			<div class="p-3 bg-white rounded box-shadow">

					<h6 class="border-bottom border-gray pb-2 mb-0">チケット一覧</h6>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">整理券番号</th>
								<th scope="col">ステータス</th>
								<!-- ステータスは入場時間から条件式で判断 -->
							</tr>
						</thead>
						<tbody>

						<% for(Ticket ticket : tickets){ %>
							<tr>
								<td class="text-muted"><%= ticket.getIdentification_code() %></td>
								<td class="text-muted">
									<% if(ticket.getPunched_at() == null){ %>
										発行済み <%=ticket.getSold_at()%>
									<%} else{ %>
										使用済み <%=ticket.getPunched_at()%>
									<%} %>
								</td>
								<td>
								<%if(ticket.getPunched_at()==null){ %>
										<div class="form-group">
											<form action="./PrintServlet" method="get"  target="_blank">
												<input type="submit" name="reissue" value="再発行" class="btn btn-info">

												<input type="hidden" name="identification_code" value="<%= ticket.getIdentification_code()%>">
											</form>
										</div>
								<% }%>
								</td>

								<td>
									<%if(ticket.getPunched_at()==null){ %>
										<div class="form-group">
											<form action="./TicketsListServlet" method="post"  target="_blank">
												<input type="submit" name ="cancell_issue" value="発行取り消し" class="btn btn-danger">

												<input type="hidden" name="identification_code" value="<%= ticket.getIdentification_code()%>">
												<input type="hidden" name="event_id" value="<%= ticket.getEvent_id()%>">
											</form>
										</div>
									<% }%>
								</td>
							</tr>
						<% } %>
						</tbody>
					</table>
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