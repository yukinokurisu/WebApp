<%@page import="dao.Event"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "dao.Shop"%>
<%
final String CONTEXT_PATH = request.getContextPath();
%>
<%
	Event event = (Event)request.getAttribute("event");
	int tickets = (int) request.getAttribute("tickets");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>イベント詳細</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
	  <nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
        <a class="navbar-brand" href="#">イベント詳細</a>
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

		<div class="row">
		  	<div class="col-10">
		  	</div>
			<div class="col-1">
				<div class="form-group">
				<% if(tickets == 0){ %>
					<form action="./EventUpdateServlet" method="get">
						<input type="submit" name="updateEvent" class="btn btn-info" value="更新">
						<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">
					</form>
				<%} %>
				</div>
			</div>
			<div class="col-1">
					<div class="form-group">
						 <form method="post" action="./EventDetailsServlet">
								<input type="submit" name="deleteEvent" class="btn btn-danger" value="削除" onclick = "return disp();">
								<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">
								<input type = "hidden" name = "start_at" value = "<%= event.getStart_at() %>">
						</form>
					</div>
			</div>
		</div>
			<p>
				<%if(request.getAttribute("deleteEventError")!=null){ %>
				<font color="RED"><%=request.getAttribute("deleteEventError") %></font>
				<%}%>
			</p>

				<table class="table table-striped">
					<thead>
							<tr></tr>
					</thead>
					<tr>
						<td class="text-muted"><b>イベント名: </b></td>
						<td class="text-muted"><%=event.getTitle() %></td>
					</tr><tr>
						<td class="text-muted"><b>日時:</b></td>
						<td class="text-muted"><%=event.getStart_at()%></td>
					</tr><tr>
						<td class="text-muted"><b>定員:</b></td>
						<td class="text-muted"><%=event.getCapacity()%></td>
					</tr><tr>
						<td class="text-muted"><b>開催店名:</b></td>
						<td class="text-muted"><%=event.getShop_name()%></td>
					</tr><tr>
						<td class="text-muted"><b>詳細:</b></td>
						<td class="text-muted"><%=event.getDescription()%></td>
					</tr>
				</table>

		<div class="row">
			<div class="col-6">
				<div class="form-group">
					<form action="./TicketIssueServlet" method="post" style="text-align:center">
					<input type="submit" name="ticket_issue" class="btn btn-secondary" value="整理券発行">
					<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">
					<input type="hidden" name="capacity" value="<%= event.getCapacity()%>">
					</form>
				</div>
			</div>

		  	<div class="col-6">
				<div class="form-group">
					<form action="./TicketsListServlet" method="get" style="text-align:center">
					<input type="submit" name="ticket_list" class="btn btn-secondary" value="整理券一覧">
					<input type="hidden" name="event_id" value="<%= event.getEvent_id()%>">
					</form>
				</div>
			</div>
		</div>

	</main>


	<script type="text/javascript">
	    function disp() {
	        // 「OK」時の処理開始 ＋ 確認ダイアログの表示
	        var res = confirm("このイベントを削除して本当によろしいですか？\n(※すでに整理券が発行されている未開催のイベントは削除できません。)");

	        if (res == true) {
	             return true;
	        }
	        // 「OK」時の処理終了
	        // 「キャンセル」時の処理開始
	        else {
	            window.alert('キャンセルされました'); // 警告ダイアログを表示
	            return false;
	        }
	        // 「キャンセル」時の処理終了
	    }
	</script>




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
		 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

</body>
</html>