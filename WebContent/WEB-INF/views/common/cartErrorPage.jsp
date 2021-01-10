<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류</title>
<style>
	.outer{
		width: 300px; height: 300px; border: 5px solid #DDEFBB;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
</style>
</head>
<body>

	<div class="outer">
		<h4 align="center"><%= msg %></h4>
		<div align="center">
			<button onclick="window.close();">확인</button>
		</div>
	</div>

</body>
</html>