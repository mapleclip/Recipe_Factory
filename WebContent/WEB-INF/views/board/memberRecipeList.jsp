<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, board.model.vo.*, member.model.vo.Member"%>
<%
   ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
   ArrayList<Attachment> fList = (ArrayList<Attachment>)request.getAttribute("fList");
   ArrayList<Board> btList = (ArrayList<Board>)request.getAttribute("btList");
   ArrayList<Attachment> ftList = (ArrayList<Attachment>)request.getAttribute("ftList");
   
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
<title>회원 레시피</title>
<style>
	.outer{
      width: 100%; border: 5px solid white;
      margin-left: auto; margin-right: auto;
      
   }
	.topRecipe{font-family: "타이포_쌍문동"; font-size: 14px;  color: #3c3c3c; margin-top: 80px;}
	.topRecipe {width:1100px; min-height:550px; margin-left:auto; margin-right:auto; margin-bottom: 10px;}
	#topRecipeList{font-family: "나눔스퀘어"; font-size: 17px; color: #3c3c3c; font-weight: bold; margin: 10px; line-height: 25px;}
	#topRecipeWriter{font-family: "나눔스퀘어"; font-size: 14px; color: gray; font-weight: bold;}
	.reipe-list{align: left;}
	.bottomRecipe{font-family: "타이포_쌍문동"; font-size: 14px;  color: #3c3c3c; margin-top: 50px;}
    .bottomRecipe {width:1100px; min-height:550px; margin-left:auto; margin-right:auto; margin-bottom: 20px;}
	.reipe-list{width: 100px;}	
	#insertBtn{width: 952px; height: 100px; border: none; background-color: #589284; color: white;
				font-family: "타이포_쌍문동"; font-size: 21px; margin: 10px;}
	.thumbnailArea {width:800px; height:550px; margin-left:auto; margin-right:auto; margin-top: 20px;}
    .thumb-list {width:300px; border:1px solid white; display:inline-block; margin:10px; align:center;}
    .thumb-list:hover {opacity:0.8; cursor:pointer;}
	.pagingArea button{border-radius: 15px; background: white; border: none; margin-bottom: 30px;}
	.buttonArea{margin-right: 50px; margin-botton: 30px;}
	.buttonArea button{background: #D1B2FF; border-radius: 5px; color: white; width: 80px; heigth: 25px; text-align: center;}
	button:hover{cursor: pointer;}
	#numBtn{background: lightgray; color: white; font-family: "나눔스퀘어";}
	#choosen{background: white; font-family: "나눔스퀘어";}
	#listArea{margin: 10px;}    
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<div class="outer" align="center">
		<div class="topRecipe">
			<h2>금주의 회원 인기 레시피 TOP3!</h2>
			<div class="reipe-list" align="center"></div>

		<% if(btList.isEmpty() || ftList.isEmpty()) { %>
		등록된 게시물이 없습니다.
		<% } else { %>
		<% for(int i = 0; i<btList.size(); i++){ %>
		<% Board b = btList.get(i); %>
		<div class="thumb-list" align="center">
			<div>
				<input type="hidden" value="<%= b.getBoardId() %>">
				<% for(int j = 0; j<ftList.size(); j++) { %>
				<% Attachment a = ftList.get(j); %>
				<% if(b.getBoardId() == a.getBoardId()) { %>
				<img
					src="<%= request.getContextPath()%>/thumbnail_uploadFiles/<%= a.getChangeName() %>"
					width="300px" height="400px">
				<%   } %>
				<% } %>
			</div>
			<p id="topRecipeList">
				<!-- 게시글 들어갈 내용 -->
				[<%= b.getBoardTitle() %>]
			</p>
			<p id="topRecipeWriter">
				작성자 :
				<%= b.getBoardWriter() %>
			</p>
		</div>
		<% } %>
		<% } %>
	</div>
		</div>
	
	<div class="insertbuttonArea" align="center">
		<% if(loginUser != null) { %>
		<input type="button" onclick="location.href='writeMemberRecipe.th'" id="insertBtn" value="나만의 레시피를 공유해보세요! click!">
		<% } %>
	</div>

	<div class="outer" align="center">
		<div class="bottomRecipe">
			<h2>회원 추천 레시피</h2>
			<div class="reipe-list" align="center"></div>

		<% if(bList.isEmpty() || fList.isEmpty()) { %>
		등록된 사진이 없습니다.
		<% } else { %>
		<% for(int i = 0; i<bList.size(); i++){ %>
		<% Board b = bList.get(i); %>
		<div class="thumb-list" align="center">
			<div>
				<input type="hidden" value="<%= b.getBoardId() %>">
				<% for(int j = 0; j<fList.size(); j++) { %>
				<% Attachment a = fList.get(j); %>
				<% if(b.getBoardId() == a.getBoardId()) { %>
				<img
					src="<%= request.getContextPath()%>/thumbnail_uploadFiles/<%= a.getChangeName() %>"
					width="300px" height="400px">
				<%   } %>
				<% } %>
			</div>
			<p id="topRecipeList">
				<!-- 게시글 들어갈 내용 -->
				[<%= b.getBoardTitle() %>]
			</p>
			<p id="topRecipeWriter">
				작성자 :
				<%= b.getBoardWriter() %>
			</p>
		</div>
		<% } %>
		<% } %>
	</div>
		</div>

		<!-- 페이징 -->
		<div class="pagingArea" align="center">
		
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.memberR?currentPage=1'">&lt;&lt;</button>
		
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.memberR?currentPage=<%= currentPage - 1 %>'"
				id="beforeBtn">&lt;</button>
			<script>
				/* 현재 페이지가 1페이지면 이전페이지 버튼 비활성화 */
				if(<%= currentPage %> <= 1){
					var before = $('#beforeBtn');
					before.attr('disabled', 'true');
				}
			</script>
			
			<!-- 숫자 목록 버튼 : 몇페이지가 있을지 모르니 for문 돌리자! -->
			<% for(int p = startPage; p <= endPage; p++){ %>
				<% if(p == currentPage){ %>
					<button id="choosen" disabled><%= p %></button>
				<% } else{ %>
					<button id="numBtn" 
						onclick="location.href='<%= request.getContextPath() %>/list.memberR?currentPage=<%= p %>'">
						<%= p %>
					</button>
				<% } %>
			<% } %>
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.memberR?currentPage=<%= currentPage + 1 %>'"
				id="afterBtn">&gt;</button>
			<script>
				/* 맨 끝에 도달하면 다음페이지 버튼 비활성화 */
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $('#afterBtn');
					after.attr('disabled', 'true');
				}
			</script>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.memberR?currentPage=<%= maxPage %>'">&gt;&gt;</button>
		</div>
		
	
   <script>
   		$(function(){
   			$('.thumb-list').click(function(){
   				var bId = $(this).children().children().eq(0).val();
   				location.href='<%= request.getContextPath() %>/detail.memberR?bId='+ bId;
   			});
   		});
   </script>	

	
    <%@ include file="../common/footer.jsp" %>
	
</body>
</html>