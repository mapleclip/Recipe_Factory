<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String memberId = (String)request.getAttribute("memberId");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매완료</title>
<style>
	.outer{
		min-width: 500px; min-height: 200px; font-size: 18px;
		margin-left: auto; margin-right: auto; margin-top: 5%; margin-bottom: 50px;
	}
	.Btn{
		font-size: 14px; color:#fff; border: 1px solid  #589284;
		background-color:  #589284; width:150px; height:30px;
	}
</style>
</head>
<body>



	<%@ include file="../common/menubar.jsp" %>	
		<br><br><br><br>
	<div class="outer">
		<br><br><br><br>
		<h3 align="center">감사합니다. <%= memberId %>님 주문내역은 아래 버튼을 눌러 확인해주세요</h3>
	

		<br><br><br><br>
		<div align="center">
			<button class="Btn" onclick="location.href='<%= request.getContextPath() %>/orderList.me'">주문 내역 확인하기</button>
			<button class="Btn" onclick="location.href='<%= request.getContextPath() %>/'">홈으로 돌아가기</button>
		</div>
	</div>

		<br><br><br><br>
	<%@ include file="../common/footer.jsp" %>	


</body>
</html>