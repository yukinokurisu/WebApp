<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "dao.Shop"%>
<%
final String CONTEXT_PATH = request.getContextPath();
%>
<%
	String message = (String) request.getAttribute("dateError");
	List<Shop> shops = (List<Shop>)request.getAttribute("shops");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>イベント追加</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
	  <nav class="navbar navbar-expand-xl navbar-dark bg-dark mt-3 mb-3">
        <a class="navbar-brand" href="#">イベント追加</a>
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
			<div class="col-12">
				<form action="./NewEventServlet" method="post">
					<div class = "form-group">
						<label for="text1">イベント名<font color="RED">(必須)</font>:</label>
						<input name="title" type="text" class = "form-control">
					</div>
					<div class = "start_at">
						<label for="text1">日時 (半角数字で入力 例:1999-02-23)<font color="RED">(必須)</font>:</label>
						<input name="start_at" type="date" class = "form-control">
					</div>
					<%if(request.getAttribute("dateError")!=null){ %>
					<font color="RED"><%=request.getAttribute("dateError") %></font>
					<%}%>
					<div class = "form-group">
						<label for="text1">定員<font color="RED">(必須)</font>:</label>
						<input name="capacity" type="text" class = "form-control">
					</div>
					<%if(request.getAttribute("CapacityError")!=null){ %>
					<font color="RED"><%=request.getAttribute("CapacityError") %></font>
					<%}%>
					<div class = "form-group">
						<label for="text1">開催店名<font color="RED">(必須)</font>:</label>
						<!-- イベント詳細ページへのリンクは無しにして、そのまま詳細を表示 -->
						<!-- input name="shop_name" type="text" class = "form-control" -->
						<select name="shop_name" id = "shop_name" >
							<%for(Shop shop:shops){ %>
								<option value="<%=shop.getShop_name()%>"><%=shop.getShop_name()%></option>
							<%} %>
						</select>

					</div>
					<div class = "form-group">
						<label for="text1">詳細:</label>
						<input name="description" type="text" class = "form-control">
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-secondary" value="追加">
					</div>
				</form>
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
		 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

</body>
</html>