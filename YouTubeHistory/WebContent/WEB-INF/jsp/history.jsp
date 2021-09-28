<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.History"%>
<%
List<History> histories = (List<History>) request.getAttribute("histories");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YouTubeHistory</title>
</head>
<body>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>履歴表示一覧</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-secondary">
		<a class="navbar-brand" href="#"><img src="./img/icon0035.png" alt="アイコン" width="30" height="21"> 履歴表示一覧</a>
		<button class="navbar-toggler p-0 border-0" type="button"
			data-toggle="offcanvas">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>

	<main role="main" class="p-4">
		<div class="row">
			<div class="col-12">
				<div class="p-3 bg-white rounded box-shadow">
					<h6 class="border-bottom border-gray pb-2 mb-0">履歴表示一覧</h6>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">再生日時</th>
								<th scope="col">タイトル</th>
							</tr>
						</thead>
						<tbody>

							<% for(History history : histories) { %>
							<tr>
								<td class="text-muted"><%=history.getPlayed_at()%></td>
								<td class="text-muted"><a href="https://youtu.be/<%=history.getYoutube_video_id() %>" target="_blank" rel="noopener noreferrer"><%=history.getTitle()%></a></td>
							</tr>
							<% } %>
						</tbody>
					</table>
					<h6 class="border-bottom border-gray pb-2 mb-0"><a href= "./RankingServlet" target="_blank" >ランキング表示ページへ</a></h6>
					<h6></h6>
					<h6 class="border-bottom border-gray pb-2 mb-0"><a href= "./ChannelRanking" target="_blank" >チャンネルランキングページへ</a></h6>
					<h6></h6>
					<h6 class="border-bottom border-gray pb-2 mb-0"><a href= "./FindServlet" target="_blank" >タイトル検索ページへ</a></h6>
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