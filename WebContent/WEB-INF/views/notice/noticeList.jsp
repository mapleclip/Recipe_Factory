<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,notice.model.vo.PageInfo,notice.model.vo.Notice"%>
<%
	ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
	ArrayList<Notice> list1 = (ArrayList<Notice>) request.getAttribute("list1");
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
<title>공지사항</title>
<script src="https://kit.fontawesome.com/735b504274.js"
	crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<style>
* {
	margin: 0;
	padding: 0;
}

.ul_li {
	list-style-type: none;
}

.sdb_title {
	padding: 7px 0 20px;
	line-height: 1.5;
}

.sdb_list_menu>li {
	border: 1px solid #dcdbde;
	padding: 13px 13px;
	text-align: left;
	font-size: 13px;
	margin-right: 0;
	width: 100px;
}

table {
	border-collapse: collapse;
}

ul>i {
	text-align: right;
}

caption {
	display: none;
}

a {
	text-decoration: none;
	color: inherit;
}

.board_list_title {
	font-size: large;
	margin-top: 10px;
	margin-bottom: 30px;
}

.board_page_wrap {
	width: 100%;
	margin-top: 100px;
	display: flex;
}

.sidebar {
	width: 100px;
	margin-right: 50px;
}

.board_right {
	flex: 1;
	margin-bottom: 100px;
}

.board_list {
	text-align: center;
}

.board_list {
	width: 100%;
	border-top: 1px solid #589284;
}

.board_list tr {
	border-bottom: 1px solid #ccc;
}

.board_list th, .board_list td {
	padding: 10px;
	font-size: 14px;
}

.board_list td.center {
	text-align: center;
}

.board_list .tit {
	text-align: left;
}

.board_list .tit:hover {
	text-decoration: underline;
}

.board_page_wrap .paging {
	margin-top: 20px;
	font-size: 0;
	text-align: center;
}

.board_page_wrap .paging a {
	display: inline-block;
	padding: 5px 10px;
	margin-left: 10px;
	font-size: 12px;
	border-radius: 100px;
}

.board_page_wrap .paging a:first-child {
	margin-left: 0;
}

.board_page_wrap .paging a.bt {
	border: 1px solid #eee;
	background: #eee;
}

.board_page_wrap .paging a.num {
	border: 1px solid green;
	font-weight: 600;
	color: green;
}

.board_page_wrap .paging a.num.on {
	background: green;
	color: #fff;
}

.icon_right {
	display: inline-block;
	float: right;
}

li:hover {
	cursor: pointer;
}

#writeNoBtn {
	background-color: #589284;
	
	padding: 3px 13px;
	color: white;
	margin-left: 13px;
	font-size: x-small;
	text-align: right;
}

.outline {
	width: 1000px;
	min-height: 500px;
	margin-left: auto;
	margin-right: auto;
}
.qboard_title{
	width: 50%;
}
button:hover{
	cursor: pointer;
}
#numBtn{
	background-color: white;
	color: green;
	border: 1px solid green;
	
}
#choosen{
	background-color : green;
	color: white;
	border: 1px solid green;
	
}
#listArea{
	margin: auto;
}
.bt{
	border-radius: 100px;
	font-size: 12px;
	margin-left: 10px;
	padding: 5px 10px;
	border:none;
}
.noBtn{
	border-radius: 100px;
	font-size: 12px;
	margin-left: 10px;
	padding: 5px 10px;
	
}
#on{
	color: #589284;
	background-color: rgba(219, 216, 216, 0.267);
}
.left{
	text-align:left;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="outline">
		<section>
			<div class="board_page_wrap">
				<div class="sidebar">
					<h2 class="sdb_title">고객센터</h2>
					<div class="inner_sdb">
						<ul class="sdb_list_menu">
							<li onclick="noticeboard();" class="ul_li" id="on"><b>공지사항</b>
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="fre_board();" class="ul_li">자주하는 질문
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="qboard();" class="ul_li">1:1 문의
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="rec_product();" class="ul_li">상품제안
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
						</ul>
					</div>
				</div>

				<div class="board_right">
					<div class="board_list_title">
						<b>공지사항</b>
						<%
							if (loginUser != null && loginUser.getMemberid().equals("admin")) {
						%>
							<input type="button" onclick="location.href='writeNoticeForm.no'" id="writeNoBtn" value="글쓰기">
						<%
							}
						%>
					</div>
					<table class="board_list">

						<tr>
							<th>번호</th>
							<th class="qboard_title">제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>

						<%
							for (Notice n : list1) {
						%>
						<tr>
							<td class="center"><%= n.getNoticeNo() %></td>
							<td class="left"><%= n.getNoticeTitle() %></td>
							<td class="center"><%= n.getNoticeWriter() %></td>
							<td class="center"><%= n.getNoticeCount() %></td>
							<td class="center"><%= n.getNoticeDate() %></td>
						</tr>
						<%
							}
						%>
					</table>

					
					<div class="paging">
						<button onclick="location.href='<%= request.getContextPath() %>/NoticeListServlet.no?currentPage=1'" class="bt">첫 페이지로</button>
						<button id="beforeBtn" class="bt" onclick="location.href='<%= request.getContextPath() %>/NoticeListServlet.no?currentPage=<%= currentPage - 1 %>'">이전페이지</button> 
						<% for(int p = startPage; p <= endPage; p++){ %>
							<% if(p == currentPage){ %>
								<button class="noBtn" id="choosen" disabled><%= p %></button>
							<% } else { %>
								<button class="noBtn" id="numBtn" onclick="location.href='<%= request.getContextPath() %>/NoticeListServlet.no?currentPage=<%= p %>'"><%= p %></button>
							<% } %>
						<% } %>
						 
						<button id="afterBtn" onclick="location.href='<%= request.getContextPath() %>/NoticeListServlet.no?currentPage=<%= currentPage + 1 %>'" class="bt">다음 페이지</button>
						<button onclick="location.href='<%= request.getContextPath() %>/NoticeListServlet.no?currentPage=<%= maxPage %>'" class="bt">마지막 페이지</button>
					</div>
				</div>

			</div>


		</section>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script>
	
		if(<%= currentPage %> <= 1){
			var before = $('#beforeBtn');
			before.attr('disabled', 'true' );
		}
		
		if(<%= currentPage %> >= <%= maxPage %>){
			var after = $('#afterBtn');
			after.attr('disabled', 'true');
		}
	
	function noticeboard(){
		
		location.href="<%=request.getContextPath()%>/NoticeListServlet.no";
	}
	function fre_board(){
		location.href="<%= request.getContextPath()%>/FAQListServlet.fa"
	}
	function qboard(){
		location.href="<%=request.getContextPath()%>/QboardListServlet.qb";
	}
	function rec_product(){
		console.log("넘어갑니다~~");
		location.href="<%= request.getContextPath()%>/RecProListServlet.rp";
	}
	$(function(){
		$('.board_list td').mouseenter(function(){
			$(this).parent().css({'background':'#89ddc9', 'cursor':'pointer'});
		}).mouseout(function(){
			$(this).parent().css('background', 'none');
		}).click(function(){
			var num = $(this).parent().children().eq(0).text();
			location.href='<%= request.getContextPath() %>/detail.no?no='+ num;
							});
		});
	</script>
</body>
</html>