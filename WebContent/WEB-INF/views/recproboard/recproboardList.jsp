<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, recproboard.model.vo.RecproBoard"%>
<%
	ArrayList<RecproBoard> list = (ArrayList<RecproBoard>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>상품 제안</title>
<script src="https://kit.fontawesome.com/735b504274.js"
	crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<style>
/* 	section */
.section-area {
	width: 1000px;
	margin: auto;
	margin-top: 50px;
	margin-bottom: 50px;
}

* {
	margin: 0;
	padding: 0;
}

ul li {
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

.board_list td {
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

.qna_write {
	margin-top: 5%;
	margin-left: 16%;
	padding: 3px 13px;
	background-color: #589284;
	color: white;
	font-size: x-small;
}

.icon_right {
	display: inline-block;
	float: right;
}

li:hover {
	cursor: pointer;
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
.on{
	color: #589284;
	background-color: rgba(219, 216, 216, 0.267);
}
</style>
</head>

<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="outline">
	<section >
		<div class="board_page_wrap">
			<div class="sidebar">
				<h2 class="sdb_title">고객센터</h2>
				<div class="inner_sdb">
					<ul class="sdb_list_menu">
						<li onclick="noticeboard();">공지사항
							<div class="icon_right">
								<i class="fas fa-angle-right"></i>
							</div>
						</li>
						<li onclick="fre_board();">자주하는 질문
							<div class="icon_right">
								<i class="fas fa-angle-right"></i>
							</div>
						</li>
						<li onclick="qboard();">1:1 문의
							<div class="icon_right">
								<i class="fas fa-angle-right"></i>
							</div>
						</li>
						<li class="on"><b>상품제안</b>
							<div class="icon_right">
								<i class="fas fa-angle-right"></i>
							</div>
						</li>
					</ul>
				</div>
			</div>

			<div class="board_right">
				<div class="board_list_title">
					<b>상품 제안</b>
				</div>
				<table class="board_list">
					<caption>게시판 목록</caption>
					<thead>
						<tr>
							<th>번호</th>
							<th class="qboard_title">제목</th>
							<th>글쓴이</th>
							<th>작성일</th>

						</tr>
						
							
					</thead>
					<tbody>
					
						<% for(int i = 0; i < list.size(); i++) { %>
                   				<% RecproBoard rp = list.get(i); %>
                   					<% if(loginUser !=null && loginUser.getMemberid().equals(rp.getRecpro_writer()))  { %>
									<tr>
										<td><%= rp.getRecpro_no() %></td>
										<td><%= rp.getRecpro_title() %></td>
										<td><%= rp.getRecpro_writer() %></td>
										<td><%= rp.getRecpro_date() %></td>
									</tr>
								<% } %>
						<% } %>
					</tbody>
				</table>

			</div>

		</div>

		<% if(loginUser == null){ %>
		<input type="button" class="qna_write" value="글쓰기"
			onclick="loginPopup();">
	<% } else{%>
		<input type="button" class="qna_write" value="글쓰기"
			onclick="recproboard_write_form();">
	<% } %>
	</section>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script>
		function recproboard_write_form(){
			location.href='<%=request.getContextPath() %>/GorboardWriteForm.qb';
		}
		function fre_board(){
			location.href="<%= request.getContextPath()%>/FAQListServlet.fa"
		}
		function noticeboard(){
			location.href="<%= request.getContextPath() %>/NoticeListServlet.no";
		}
		function qboard(){
			location.href="<%= request.getContextPath() %>/QboardListServlet.qb";
		}
		function loginPopup(){
			var bool = confirm("로그인 후 이용해주세요.");
			if(bool){
				location.href='<%=request.getContextPath()%>/loginform.me';
			}
		}
		$(function(){
			$('.board_list td').mouseenter(function(){
				$(this).parent().css({'background':'#89ddc9', 'cursor':'pointer'});
			}).mouseout(function(){
				$(this).parent().css('background', 'none');
			}).click(function(){
				var num = $(this).parent().children().eq(0).text();
				location.href='<%=request.getContextPath()%>/detail.rpb?rpb='+ num;
			});
		});
	</script>
</body>

</html>