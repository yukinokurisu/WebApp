<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.User" %>
<%@ page import="bean.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
  // データベースからひとことリストを取得する
 // List<Message> messages = (ArrayList<Message>) application.getAttribute("messages");
  List<Message> messages = (List<Message>)request.getAttribute("messages");
  // セッションスコープからログインユーザ情報を取得
  User user = (User) session.getAttribute("user");
  //リクエストスコープからエラーメッセージを取得
  String errorMessage = (String) request.getAttribute("errorMessage");
 /*  request.setAttribute("tweet", null);
  request.setAttribute("pushGood", null); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>第10章 ひとこと掲示板</title>
</head>
<body>
<h2>ひとこと掲示板へようこそ</h2>
<% if( user != null ) { %>
  <p><%= user.getUserName() %>さん、ログイン中</p>
<% } %>
<a href="./logout">ログアウト</a>
<hr>
<form method="post">
  <label for="comment">ひとこと：</label>
  <input type="text" name="comment" id="comment" /><br />
  <input type="submit" name="tweet" value="投稿"  />
</form>
<hr>
<% if( errorMessage != null ) { %>
  <p><%= errorMessage %></p>
<% } %>
<% if( messages.size() == 0 ) { %>
  <p>投稿された「ひとこと」はまだありません。</p>
<% } else { %>
  <% for(Message message : messages){ %>
        <p>
        	<%= message.getUserName() %> : <%= message.getComment() %> : いいね <%= message.getGood()%>件 :<%= message.getDate() %>
        </p>
        <form action="./messages" method="post">
			        <input type="hidden" name="message_id" value = "<%= message.getMessageID() %>">
					<input type="submit" value="いいね" name="pushGood" />
		</form>
  <% } %>
<% } %>
</body>
</html>