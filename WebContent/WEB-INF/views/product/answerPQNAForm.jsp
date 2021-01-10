<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="product.model.vo.PQNA"%>
<% PQNA pqna = (PQNA)request.getAttribute("pqna"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 답변</title>
<style>
	.outer{
		width: 800px; height: 500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	.tableArea {width:500px; height:350px; margin-left:auto; margin-right:auto;}
	#insertBtn {background: #B2CCFF;}
	#cancelBtn{background: #D1B2FF;}
</style>
</head>
<body>
	<div class="outer">
		<br>
		<h2 align="center">문의글 답변</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/answerPQNA.pr" method="post">
				<input type="hidden" name="pId" value="<%= pqna.getpId() %>">
				<input type="hidden" name="pqbNo" value="<%= pqna.getPqbNo() %>">
				<table>
					<tr>
						<th>작성자</th>
						<td colspan="3"><input type="text" size="58" name="mId" readonly value="<%= pqna.getmId() %>"></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td colspan="3"><input type="text" size="58" name="pqbDate" readonly value="<%= pqna.getPqbDate() %>"></td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text" size="58" name="pqbTitle" readonly value="<%= pqna.getPqbTitle() %>"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="15" cols="60" name="pqbContent" style="resize:none;" readonly ><%= pqna.getPqbContent() %></textarea>
						</td>
					</tr>
					<tr>
						<th>답변</th>
						<td colspan="3">
							<% if(pqna.getPqbAnswer() != null){ %>
							<textarea rows="15" cols="60" name="pqbAnswer" style="resize:none;"><%= pqna.getPqbAnswer() %></textarea>
							<% }else{ %>
							<textarea rows="15" cols="60" name="pqbAnswer" style="resize:none;"></textarea>
							<% } %>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<input type="submit" id="insertBtn" value="등록하기">
					<input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/detail.pr?pId=<%= pqna.getpId() %>'" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>