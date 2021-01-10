<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String pId = (String)request.getAttribute("pId"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 작성</title>
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
	<%@ include file="../common/menubar.jsp" %>
		
	<div class="outer">
		<br>
		<h2 align="center">문의글 작성</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/writePQNA.pr" method="post">
				<input type="hidden" name="pId" value="<%= pId %>">
				<table>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text" size="58" name="pqbTitle"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="15" cols="60" name="pqbContent" style="resize:none;"></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<input type="submit" id="insertBtn" value="등록하기">
					<input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/detail.pr?pId=<%= pId %>'" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>