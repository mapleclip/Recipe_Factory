<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, cart.model.vo.Buy" %>
<%
	ArrayList<Buy> list = (ArrayList<Buy>)request.getAttribute("list");

	int totalPrice = 0;
	for(Buy b:list){
		totalPrice += b.getTotalPrice(); 
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style>
	.outer{
      width: 100%; border: 5px solid white;
      margin-left: auto; margin-right: auto;
    }
    .wrap{background: white; width: 100%; height: 50px;}
	.menu{
		background: #589284; color: white; text-align: center;
		vertical-align: middle; width: 200px; height: 80px; display: table-cell;
		font-family: "타이포_쌍문동"; font-size: 20px; border: 3px solid white;
	}
	nav{width: 800px; margin-left: auto; margin-right: auto; margin-top: 40px;}
	.menu:hover {background: beige; color:#589284; font-weight:bold; cursor:pointer;}
	#listArea{text-align: center; font-family: "나눔스퀘어"; font-size: 16px;}
	.tableArea{width:900px; margin-left:auto;	margin-right:auto; margin-bottom: 30px;}
	table {
	  border-collapse: separate;
	  border-spacing: 0 8px;
	}
	th{border-bottom: 1px solid white; background: #589284; color:white; height:30px;}
	.pagingArea button{border-radius: 15px; background: white; border: none; margin-bottom: 30px;}
	.buttonArea{margin-right: 50px; margin-botton: 30px;}
	.buttonArea button{background: #D1B2FF; border-radius: 5px; color: white; width: 80px; heigth: 25px; text-align: center;}
	button:hover{cursor: pointer;}
	#numBtn{background: lightgray; color: white; font-family: "나눔스퀘어";}
	#choosen{background: white; font-family: "나눔스퀘어";}
	#listArea{margin: 10px;}
	h2{font-family: "타이포_쌍문동"; font-size: 25px;  color: #3c3c3c; margin-top: 80px;}
	td{margin-botton: 100px;}

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<div class="wrap">
		<nav>
			<div class="menu" onclick="mainBanner();">메인배너 수정</div>
			<div class="menu" onclick="memberInfo();">회원목록 조회</div>
			<div class="menu" onclick="orderInfo();">배송목록 조회</div>
			<div class="menu" onclick="goHome();">메인 바로가기</div>
		</nav>
		</div>
	
	
		<br>
		<h2 align="center">최근 주문현황</h2>
		<div class="tableArea">
			<table id="listArea">
				<tr>
					<th width="100px">주문번호</th>
					<th width="100px">아이디</th>
					<th width="100px">상품번호</th>
					<th width="300px">상품이름</th>
					<th width="300px">주소</th>
					<th width="150px">주문날짜</th>
					<th width="100px">결제금액</th>
				</tr>
				<% if(list.isEmpty()){ %>
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
				<% } else{ %>
				<%		for(Buy b : list){ %>
							<tr>
								<td><%= b.getOrderno() %></td>
								<td><%= b.getMemberid() %></td>
								<td><%= b.getP_id() %></td>
								<td><%= b.getP_name() %></td>
								<td><%= b.getOrderAddress() %></td>
								<td><%= b.getOrderDate() %></td>
								<td><%= b.getTotalPrice() %></td>
							</tr>
				<%		} %>
				<% } %>
			</table>
			
			<h2 align="center">총 매출 : <%= totalPrice %></h2>
			<br>
		</div>
		

	</div>
	
	<script>
		function goHome(){
			location.href='<%= request.getContextPath() %>';
		}
		
		function memberInfo(){
			location.href='<%=request.getContextPath() %>/member.ad';
		}
		
		function mainBanner(){
			location.href='<%=request.getContextPath() %>/mainBanner.ad';
		}
		
		function orderInfo(){
			location.href='<%=request.getContextPath() %>/orderInfo.ad';
		}
	</script>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>