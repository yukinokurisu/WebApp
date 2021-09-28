<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/Style.css" rel="stylesheet">
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

<title>Top</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>

<nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
<a class="navbar-brand" href="#">Top</a>
<button class="navbar-toggler" type="button" data-toggle="collapse"
	data-target="#navbarNav3" aria-controls="navbarNav3"
	aria-expanded="false" aria-label="Toggle navigation">
	<span class="navbar-toggler-icon"></span>
</button>
<div class="collapse navbar-collapse" id="navbarNav3">
	<ul class="navbar-nav">
		<li class="nav-item active"><a class="nav-link"
			href="./TopServlet">Top <span class="sr-only">(current)</span></a></li>
		<li class="nav-item"><a class="nav-link" href="./Logout">ログアウト</a>
		</li>
	</ul>
</div>
</nav>

<!-- ここに白の空白、遊びを入れる -->

<img src="./img/Top_image_fixed.jpg" alt="イベントイメージ" class="img-fluid">

<!--
			<img src="./img/Top_image.jpg" alt="イベントイメージ" class = img>
		-->

<p></p>
<p></p>
<p></p>
<p></p>

<div class="row">
	<div class="col-4">
		<h6 class="pb-2 mb-0" style="text-align: center">
			<a href="./NewEventServlet" target="_blank">
				<button type="button" name="new_event" class="btn btn-secondary">イベント作成</button>
			</a>
		</h6>
	</div>
	<div class="col-4">
		<h6 class="pb-2 mb-0" style="text-align: center">
			<a href="./EventListServlet" target="_blank">
				<button type="button" name="event_list" class="btn btn-secondary">イベント一覧</button>
			</a>
		</h6>
	</div>
	<div class="col-4">
		<h6 class="pb-2 mb-0" style="text-align: center">
			<a href="./TicketCheckServlet" target="_blank">
				<button type="button" name="ticket_check" class="btn btn-secondary">整理券認証</button>
			</a>
		</h6>
	</div>
</div>
<p></p>
<p></p>
<p></p>
<p></p>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
</body>
</html>