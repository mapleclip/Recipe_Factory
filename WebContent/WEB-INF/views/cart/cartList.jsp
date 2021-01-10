<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList, cart.model.vo.Cart" %>    
<% 
	ArrayList<Cart> list = (ArrayList<Cart>)request.getAttribute("list");

	int total = 0;
	for(Cart c : list) { 
		total += c.getCart_price() * c.getAmount(); 
		
	 }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style type="text/css">
	.outer{ width: 700px; min-height: 400px;
		margin-left: auto; margin-right: auto; margin-top: 5%; margin-bottom: 50px;
		
	}
	.emptyDiv{
		width: 400px; min-height: 200px; 
		margin-left: auto; margin-right: auto; margin-top: 50px;	
		text-align:center;	
	}

	#listArea{
		text-align: center;
		border-spacing : 10px;
	}
	
	input[type="text"]{
		height:20px; width:70px;
		border : 0px; 
		text-align:center;
	}

	.Btn{
		font-size: 15px; color:#fff; border: 1px solid  #589284;
		background-color:#589284; width:110px; height:30px;	}
	
	.modifyBtn{
		border-radius: 15px; color: white; background: #589284;
		width: 80px; font-size: 12px; padding-top: 5px; padding-bottom: 5px;
		border: none;
	}
	
	.btn2{background: #c0c0c0; color: white;
		width: 120px; padding-top: 5px; padding-bottom: 5px;
		border: none;}
	
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<br><br><br><br>


	
	<div class="outer">

		<h2 align="center"><%= loginUser.getMembername() %>님의 장바구니</h2>
		<br><br>
		
		<% if(list.isEmpty()) { %>
			<div class="emptyDiv">
				<p><b>장바구니에 담긴 상품이 없습니다.</b></p>
				<p>원하는 상품을 장바구니에 담아보세요!</p>
			</div>
			
				<% } else { %>
					<form action="<%= request.getContextPath() %>/purchaseCart.me" method="post" name="purchaseForm" onsubmit="return send(this);">

						<table id="listArea" align="center">
							<tr>
								<td><input type="checkbox" id="all" checked></td>
								<td>번호</td>
								<td></td>
								<td>상품 이름</td>
								<td>상품 가격</td>
								<td>개수</td>		
								<td>옵션</td>
							</tr>		
							<% for(Cart c : list) { %>
								<tr id="trList">
									<td><input type="checkbox" class="chk" name="chk" value="<%= c.getCart_no()%>" checked></td>
									<td><%= c.getCart_no()%></td>
									<td><input type="hidden" name="p_id" value="<%= c.getP_id() %>"></td>
									<td class="p_name"><%= c.getP_name() %></td>
									<td><%= c.getCart_price() %></td>
									<td><%= c.getAmount() %></td>		
									<td><input type="button" class="modifyBtn" value="수량 변경"></td>
								</tr>
							<% } %>		
						</table>
						<br><br>
						<div align="center">
							<!-- 체크된 값만 숫자 더하기 -->
							<p>장바구니 총액</p>
							<input type="text" value="<%= total%>"readonly>
						</div>	
					<br><br>
					<div align="center">
						<input type="submit" class="Btn" value="구매하기">			
						<input type="button" class="Btn" onclick="deleteCart(); " value="삭제하기">			
					</div>
				<% } %>
			</form>
			<br><br>
			<div align="center">
				<button class="btn2" onclick="location.href='<%= request.getContextPath() %>/'">메인으로</button>
			</div>
			<br><br><br><br>
	</div>

	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
	
		$("#all").click(function(){
			if($('#all').is(":checked")){
				$(".chk").prop("checked", true);
			} else{
				$(".chk").prop("checked", false);
			}
		});

		$(".chk").click(function(){
			if($("input[name='chk']:checked").length == <%= list.size()%>){
				$("#all").prop("checked", true);
			} else {
				$('#all').prop("checked", false);
			}
		});
		
		
		$('.modifyBtn').click(function(){
			var cart_no = $(this).parent().parent().children().eq(1).text();
			console.log(cart_no);
		 	location.href='<%= request.getContextPath() %>/modifyCartForm.me?cart_no='+cart_no; 
		}); 
			
		function deleteCart(){
			 
			var checkArr = [];
		    $("input[name='chk']:checked").each(function(i){
		        checkArr.push($(this).val());   
		    });

			location.href = '<%= request.getContextPath() %>/deleteCart.me?checkArr='+checkArr;
		
		}
	
		
		function send(form){
			var checkArr = [];
		    $("input[name='chk']:checked").each(function(i){
		        checkArr.push($(this).val());   
		    });
		    
		    if(checkArr == ""){
		    	alert("구매하실 상품을 선택해주세요");
		    	return false;
		    }
		}
		
		$(function(){
			$('.p_name').mouseenter(function(){
				$(this).parent().children().eq(3).css('cursor','pointer');
			 }).mouseout(function(){
				 $(this).parent().children().eq(3).css('background','none');
			 }).click(function(){
				 var p_name = $(this).parent().children().eq(3).text();
				 var p_id = $(this).parent().children().eq(2).children().val();
				 console.log(p_id);
				location.href = '<%= request.getContextPath() %>/detail.product?p_id='+p_id;
			 });	

		 }); 

	</script>

	
</body>
</html>