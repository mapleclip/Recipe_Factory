<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, product.model.vo.*"%>
<%
	ArrayList<Product> pList = (ArrayList<Product>)request.getAttribute("list");
	ArrayList<PAttachment> fList = (ArrayList<PAttachment>)request.getAttribute("fList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
<style type="text/css">
	.outer{
      width: 100%; border: 5px solid white;
      margin-left: auto; margin-right: auto;
   }
   	.top{font-family: "타이포_쌍문동"; font-size: 20px;  color: #3c3c3c; margin-top: 55px; margin-bottop: 10px;}
	.pagingArea button{background-color: #e0e0e0;
		border: 1px solid black;}
	.buttonArea{margin-right: 50px;}
	.buttonArea button{width: 80px; heigth: 25px; text-align: center;}
	button:hover{cursor: pointer;}
	#numBtn{border: 1px solid black; background-color: white;}
	#choosen{background-color: #589284;
		border: 1px solid #589284;
		color: #fff;}
	.thumbnailArea {width:1100px; min-height:550px; margin-left:auto; margin-right:auto; margin-bottom: 20px;}
    .thumb-list {width:300px; border:1px solid white; display:inline-block; align:center; margin:10px; margin-bottom:40px;}
    .thumb-list:hover {opacity:0.8; cursor:pointer;}
    .product_list{font-family: "나눔스퀘어"; font-size: 20px; color: #3c3c3c; font-weight: bold; margin: 10px; line-height: 25px;}
    .product_price{font-family: "나눔스퀘어"; font-size: 19px; color: gray; font-weight: bold; margin: 1px; line-height: 25px;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer" align="center">
		<br>
		<h2 align="center" class="top">식재료 마켓</h2>
		<div class="thumbnailArea" align="center">
			<%
				if(pList.isEmpty() || fList.isEmpty()){
			%>
				상품이 없습니다.
			<%
				}else{
			%>
				<%
					for(int i = 0; i < pList.size(); i++){
				%>
					<%
						Product p = pList.get(i);
					%>
					<div class="thumb-list" align="center">
						<div align="center">
							<input type="hidden" value="<%=p.getpId()%>">
							<%
								for(int j = 0; j < fList.size(); j++){
							%>
								<%
									PAttachment a = fList.get(j);
								%>
								<% if(p.getpId().equals(a.getpId())){ %>
									<img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getChangeName() %>" width="300px" height="400px">
								<% } %>
							<% } %>
						</div>
						<p align="center" class="product_list">
							[<%= p.getpName() %> <%= p.getpCapacity() %>]
						</p>
						<p align="center" class="product_price">
							<%= p.getpPrice() %>원
						</p>	
					</div>
				<% } %>
			<% } %>
		</div>
		<div class="pagingArea" align="center">
			<button onclick="location.href='<%= request.getContextPath() %>/list.pr?currentPage=1'">&lt;&lt;</button>
			<button onclick="location.href='<%= request.getContextPath() %>/list.pr?currentPage=<%= currentPage - 1 %>'" id="beforeBtn">&lt;</button>
			<script>
				if(<%= currentPage %> <= 1){
					var before = $('#beforeBtn');
					before.attr('disabled', 'true');
				}
			</script>
			<% for(int p = startPage; p <= endPage; p++){ %>
				<% if(p == currentPage){ %>
					<button id="choosen" disabled><%= p %></button>
				<% }else{ %>
					<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.pr?currentPage=<%= p %>'"><%= p %></button>
				<% } %>
			<% } %>
			
			<button onclick="location.href='<%= request.getContextPath() %>/list.pr?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
			<script>
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $('#afterBtn');
					after.attr('disabled', 'true');
				}
			</script>
			<button onclick="location.href='<%= request.getContextPath() %>/list.pr?currentPage=<%= maxPage %>'">&gt;&gt;</button>
		</div>
		
		<div class="buttonArea" align="right">
			<% if(loginUser != null && loginUser.getMemberid().equals("admin")){ %>
			<button onclick="location.href='writeProductForm.pr'">작성하기</button>
			<% } %>		
		</div>
	</div>
	<script>
		$(function(){
			$('.thumb-list').click(function(){
				var pId = $(this).children().children().eq(0).val();
				location.href='<%= request.getContextPath() %>/detail.pr?pId='+ pId;
			});
		});
// 		$(function(){
// 			$('.thumb_list').mouseenter(function(){
// 				$(this).css({'border':'2px solid gray', 'cursor':'pointer'});
// 			}).mouseout(function(){
// 				$(this).css('border', '1px solid gray');
// 			}).click(function(){
// 				var pId = $(this).children().children().eq(0).val();
<%-- 				location.href='<%= request.getContextPath() %>/detail.pr?pId=' + pId; --%>
// 			});
// 		});
	</script>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>