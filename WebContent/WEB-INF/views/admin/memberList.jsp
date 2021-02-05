<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, member.model.vo.Member, admin.model.vo.PageInfo" %>
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
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
<title>회원 목록</title>
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
	.tableArea{width:1000px; margin-left:auto;	margin-right:auto; margin-bottom: 30px;}
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
		<h2 align="center">회원 목록 (최근 가입일 순)</h2>
		<div class="tableArea">
			<table id="listArea">
				<tr>
					<th width="100px">아이디</th>
					<th width="100px">이름</th>
					<th width="50px">성별</th>
					<th width="100px">연락처</th>
					<th width="200px">주소</th>
					<th width="200px">상세주소</th>
					<th width="200px">이메일</th>
					<th width="200px">가입일</th>
					<th width="100px">회원여부</th>
				</tr>
				<% if(list.isEmpty()){ %>
				<tr>
					<td colspan="9">조회된 리스트가 없습니다.</td>
				</tr>
				<% } else{ %>
				<%		for(Member m : list){ %>
							<tr>
								<td><%= m.getMemberid() %></td>
								<td><%= m.getMembername() %></td>
								<td><%= m.getGender() %></td>
								<td><%= m.getPhone() %></td>
								<td><%= m.getAddress1() %></td>
								<td><%= m.getAddress2() %></td>
								<td><%= m.getEmail() %></td>
								<td><%= m.getEnrolldate() %></td>
								<td><%= m.getStatus() %></td>
							</tr>
				<%		} %>
				<% } %>
			</table>
		</div>
		
		<!-- 페이징 -->
		<div class="pagingArea" align="center">
		
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/member.ad?currentPage=1'">&lt;&lt;</button>
		
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/member.ad?currentPage=<%= currentPage - 1 %>'"
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
						onclick="location.href='<%= request.getContextPath() %>/member.ad?currentPage=<%= p %>'">
						<%= p %>
					</button>
				<% } %>
			<% } %>
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/member.ad?currentPage=<%= currentPage + 1 %>'"
				id="afterBtn">&gt;</button>
			<script>
				/* 맨 끝에 도달하면 다음페이지 버튼 비활성화 */
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $('#afterBtn');
					after.attr('disabled', 'true');
				}
			</script>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/member.ad?currentPage=<%= maxPage %>'">&gt;&gt;</button>
		</div>
		
	</div>
	
	<script>
		function goHome(){
			location.href='<%= request.getContextPath() %>/';
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