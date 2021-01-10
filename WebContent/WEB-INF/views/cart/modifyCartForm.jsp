<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="cart.model.vo.Cart"%>

<% 
	Cart cart = (Cart)request.getAttribute("cart");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 수정</title>
<style>
	.outer{width: 300px; min-height: 300px; margin-left: auto; margin-right: auto; 
			margin-top: 10%; margin-bottom: 5%;
	}
	.Btn{
		border-radius: 15px; color: white; background: #589284;
		width: 80px; font-size: 12px; padding-top: 5px; padding-bottom: 5px;
		border: none; margin-top:5%;
	}	
	
</style>
</head>
<body>
	
	<form action="<%= request.getContextPath() %>/modify.ca" method="post">
	
		<div class="outer" align="center">
 			<h2>장바구니 상품 수량 수정</h2>
			<input type="hidden" name="cart_no" value="<%= cart.getCart_no() %>">
			현재수량 : <input type="number" size="15" name="amount" value="<%= cart.getAmount()%>"  min="1" max="100">
			
			
			
			<div align="center">
				<input type="submit" id="modifyBtn" class="Btn" value="수정">
				<input type="button" class="Btn" value="취소" onclick="location.href='javascript:history.back();'">
			</div> 
		</div>
	</form>
	

	

</body>
</html>