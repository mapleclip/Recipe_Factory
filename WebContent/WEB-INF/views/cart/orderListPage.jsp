<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList, cart.model.vo.Buy" %>    
<% 
	ArrayList<Buy> list = (ArrayList<Buy>)request.getAttribute("list");	
	String memberId = (String)request.getAttribute("memberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 조회</title>
<style>
	.outer{ width: 700px; min-height: 800px;
		margin-left: auto; margin-right: auto; margin-top: 5%; margin-bottom: 5%;
	}

	table {font-size:14px; text-align:left;
	}
	
	td{ padding: 10px;}
	
	.Btn{
		font-size: 14px; color:#fff; border: 1px solid  #589284;
		background-color:  #589284; width:150px; height:30px;
	}
	hr{color:#bcbcbc;}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<br><br><br><br>
	
	<div class="outer">
	
	<h2 align="center"><%= memberId %>님의 주문 조회</h2><br><br>
	
		<% if(!list.isEmpty()){ %>

			<% for(Buy b : list){ %>
				<table>
					<tr>
						<td>상품이름</td>
						<td class="p_name"><%= b.getP_name() %><input type="hidden" name="p_id" value="<%= b.getP_id() %>"></td>

					</tr>		
 					<tr>
						<td>총 가격</td>
						<td><%=b.getTotalPrice() %></td>
					</tr> 
					<tr>
						<td>배송여부</td>
						<td>
							<%= b.getDeli_status() %>
						</td>
					</tr>
					<tr>
						<td>주문일자</td>
						<td><%= b.getOrderDate() %></td>
					</tr>
					<tr>
						<td>개수</td>
						<td><%= b.getAmount() %></td>
					</tr>
					<tr>
						<td>배송지</td>
						<td><%= b.getOrderAddress() %></td>
					</tr>
					<tr>
						<td>연락처</td>
						<td><%= b.getOrderPhone() %></td>
					</tr>
				</table>
				<hr>
			<% } %>
		<% } else { %>
			<h2 align="center">주문하신 상품이 없습니다. 상품을 구매하신 후 이용해주세요. </h2>
			
		<% } %>
		
		<br><br><br><br>
		
		<div align="center">
			<button class="Btn"  onclick="location.href='<%= request.getContextPath() %>/list.pr'">상품 보러가기</button>
			<button class="Btn" onclick="location.href='<%= request.getContextPath() %>/'">홈으로 돌아가기</button>	
		</div>

	</div>

	
	<%@ include file="../common/footer.jsp" %>
	
	
	
	<script>
	
	$('.p_name').mouseenter(function(){
		$(this).css('cursor','pointer');
	}).mouseout(function(){
		$(this).css('background','none');
	}).click(function(){
		var p_name = $(this).text();
		console.log(p_name);
		var p_id = $(this).children().val();
		console.log(p_id);
		location.href = '<%= request.getContextPath() %>/detail.order_product?p_id='+p_id;
	});
	
	
	</script>

</body>
</html>