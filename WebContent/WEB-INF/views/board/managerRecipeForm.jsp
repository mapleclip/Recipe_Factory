<!-- 레펙 레시피 8. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList, board.model.vo.*" %>

<%
	ArrayList<ManagerRecipe> bList = (ArrayList<ManagerRecipe>)request.getAttribute("bList");
	ArrayList<MRAttachment> fList = (ArrayList<MRAttachment>)request.getAttribute("fList");
	ArrayList<ManagerRecipe> manaRList = (ArrayList<ManagerRecipe>)request.getAttribute("manaRList");
	
	
	ArrayList<ManagerRecipe> btList = (ArrayList<ManagerRecipe>)request.getAttribute("btList");
	ArrayList<MRAttachment> ftList = (ArrayList<MRAttachment>)request.getAttribute("ftList");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int boardLimit = pi.getBoardLimit();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레팩 레시피</title>
<style>
	.outer{
      width: 100%; border: 5px solid white;
      margin-left: auto; margin-right: auto;
      
   }
   
   .Recipe{
   		width:1100px; min-height:350px; margin-left:auto; margin-right:auto; margin-bottom: 10px;
   }
	.topRecipe{font-family: "타이포_쌍문동"; font-size: 14px;  color: #3c3c3c; margin-top: 80px; border-color: #CFCFCF; border-style:dashed; }
	.topRecipe {width:1100px; min-height:350px; margin-left:auto; margin-right:auto; margin-bottom: 10px;}
	#topRecipeList{font-family: "나눔스퀘어"; font-size: 17px; color: #3c3c3c; font-weight: bold; margin: 10px; line-height: 25px;}
	#topRecipeWriter{font-family: "나눔스퀘어"; font-size: 14px; color: gray; font-weight: bold;}
	.reipe-list{align: left; width: 100px; }
	.reipe-list{align: left; width: 100px; }
	#insertBtn{width: 500px; height: 100px; border: none; background-color: #589284; color: white;
				font-family: "타이포_쌍문동"; font-size: 21px; margin: 10px; align:center;}
				
	.topthumbnailArea {width:800px; height:550px; margin-left:auto; margin-right:auto; margin-top: 20px;}
    .topthumb-list {width:300px; border:1px solid white; display:inline-block; margin:10px; align:center;}
    .topthumb-list:hover {opacity:0.8; cursor:pointer;}
    
	.thumbnailArea {width:800px; height:550px; margin-left:auto; margin-right:auto; margin-top: 20px;}
    .thumb-list {width:300px; border:1px solid white; display:inline-block; margin:10px; align:center;}
    .thumb-list:hover {opacity:0.8; cursor:pointer;}
    h2{Text-decoration:underline;}
    button{width:50px; height:50px; background-color: #74D36D; opacity: 0.5; paint-order: none; border: none; color: white;  border-radius: 50%}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer" align="center" id="listArea">
		<div class="topRecipe">
			<h2>오늘의 인기 레시피</h2>
			<div class="topreipe-list" align="center"></div>
			
			<% if(btList.isEmpty() || ftList.isEmpty()) { %>
				등록된 레시피가 없습니다.
			<% } else { %>
			<% for(int i = 0; i<btList.size(); i++){ %>
				<% ManagerRecipe b = btList.get(i); %>
				<div class="thumb-list" align="center">
					<div>
						<input type="hidden" value="<%= b.getM_R_NO() %>">
						<input type="hidden" id="pId" name="pId" value="<%= b.getP_Id() %>">
						<% for(int j = 0; j < ftList.size(); j++) { %>
							<% MRAttachment a = ftList.get(j); %>
							<% if(b.getM_R_NO() == a.getM_R_NO()) { %>
								<img src="<%= request.getContextPath()%>/managerRecipe_uploadFiles/
								<%= a.getChangeName() %>" width="300px" height="400px">
							<% } %>
						<% } %>
					</div>
					<p id="topRecipeList">[레시피] <%= b.getM_R_Title() %></p>
				</div>
			<% } %>
			<% } %>
		
		</div>
	</div>
	
	<div class="button" align="center">
			<% if(loginUser != null && loginUser.getMemberid().equals("admin")) { %>
				<input type="button" onclick="location.href='writManagerRecipeForm.th'" id="insertBtn" value="작성하기">
			<% } %>
	</div>
	<br><br><br>
	<div class="out" align="center" id="listArea">
		<div class="Recipe">
			<h2>레펙 레시피</h2>
			<div class="reipe-list" align="center"></div>
			
			<% if(bList.isEmpty() || fList.isEmpty()) { %>
				등록된 레시피가 없습니다.
			<% } else { %>
			<% for(int i = 0; i<manaRList.size(); i++){ %>
				<% ManagerRecipe b = manaRList.get(i); %>
				<% System.out.println("리스트 : "+b); %>
				
				<div class="thumb-list" align="center">
					<div>
						<input type="hidden" value="<%= b.getM_R_NO() %>">
						<input type="hidden" id="pId" name="pId" value="<%= b.getP_Id() %>">
						<% for(int j = 0; j < fList.size(); j++) { %>
							<% MRAttachment a = fList.get(j); %>
							<% if(b.getM_R_NO() == a.getM_R_NO()) { %>
								<img src="<%= request.getContextPath()%>/managerRecipe_uploadFiles/<%= a.getChangeName() %>" width="300px" height="400px">
							<% } %>
						<% } %>
					</div>
					<p id="topRecipeList">[레시피] <%= b.getM_R_Title() %></p>
				</div>
			<% } %>
			<% } %>
		
		</div>
	</div>
	
	<br><br><br>
		
			
		<div class="pagingArea" align="center">
		<% System.out.println("currentPage : "+currentPage); %>
			<button onclick="location.gref='<%= request.getContextPath() %>/list.managerR?currentPage=1'">&lt;&lt;</button>
			<button onclick="location.gref='<%= request.getContextPath() %>/list.managerR?currentPage=<%= currentPage -1 %>'" id="beforeBtn">&lt;</button>
		
			<script>
				if(<%= currentPage %> <= 1){
					var before = $('#beforeBtn');
					before.attr('disabled', 'true');
				}
			</script>
		
			<% for(int p = startPage; p <= endPage; p++){ %>
				<% if(p == currentPage){ %>
					<button id="choosen" disabled><%= p %></button>
				<% } else { %>
					<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.managerR?currentPage=<%= p %>'"><%= p %></button>
				<% } %>
			<% } %>
			
			<button onclick="location.href='<%= request.getContextPath() %>/list.managerR?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
			
			
			<script>
			
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $('#agterBtn');
					after.attr('disabled', 'true');
				}
			</script>
			
			<button onclick="location.href='<%= request.getContextPath() %>/list.managerR?currentPage=<%= maxPage %>'">&gt;&gt;</button>
			
			</div>
			
					
				<script>
					$(function(){
						$('.thumb-list').click(function(){
							var bId = $(this).children().children().eq(0).val();
							var pId = $(this).children().children().eq(1).val();
							location.href="<%= request.getContextPath() %>/detail.MR?bId=" + bId + "&pId=" + pId;
						});
					});
				
		
	</script>
			
		<br><br><br>
	
	<%@ include file="../common/footer.jsp" %>
	
</body>
</html>