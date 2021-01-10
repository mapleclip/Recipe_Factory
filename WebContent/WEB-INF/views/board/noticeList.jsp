<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, board.model.vo.Notice" %>
<% 
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항게시판</title>
<script src="https://kit.fontawesome.com/735b504274.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>

*{
    margin: 0;
    padding: 0;
}
ul li{
    
    list-style-type: none;
}

.sdb_title{
    padding: 7px 0 20px;
    line-height: 1.5;
}
.sdb_list_menu > li{
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
ul > i {
    text-align: right;
}
caption {
    display: none;
}
a {
    text-decoration: none;
    color: inherit;
}

.board_list_title{
    font-size: large;
    margin-top: 10px;
    margin-bottom: 30px;
}

.board_page_wrap{
    width: 100%;
    margin-top: 100px;
    display: flex;
}

.sidebar{
    width: 100px;
    margin-right: 50px;
    
}
.board_right{
    flex:1;
    margin-bottom: 100px;
    
}
.board_list{
    text-align: center;
}
.board_list{
    width: 100%;
    border-top: 1px solid #589284;
}

.board_list tr {
    border-bottom: 1px solid #ccc;
}
.board_list th,
.board_list td {
    padding: 10px;
    font-size: 14px;
}
.board_list td {
    text-align: center;
}
.board_list .tit {
    text-align: left;
}
.board_list .tit:hover{
    text-decoration: underline;
}
.board_page_wrap .paging {
    margin-top: 20px;
    font-size: 0;
    text-align: center;
}
.board_page_wrap .paging a{
    display: inline-block;
    padding: 5px 10px;
    margin-left: 10px;
    
    font-size: 12px;
    border-radius: 100px;
}
.board_page_wrap .paging a:first-child{
    margin-left: 0;
}

.board_page_wrap .paging a.bt {
    border: 1px solid #eee;
    background: #eee;
}
.board_page_wrap .paging a.num{
    border: 1px solid green;
    font-weight: 600;
    color: green;
}
.board_page_wrap .paging a.num.on {
    background: green;
    color: #fff;
}
.icon_right{
    display: inline-block;
    float: right;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<section style="padding: 0 20%; min-height: 500px;">
		<div class="board_page_wrap">
			<div class="sidebar">
				<h2 class="sdb_title">고객센터</h2>
				<div class="inner_sdb">
					<ul class="sdb_list_menu">
						<li><a href="board.html">공지사항</a><div class="icon_right"><i class="fas fa-angle-right"></i></div></li>
						<li><a href="board_qna.html">자주하는 질문<div class="icon_right"><i class="fas fa-angle-right"></i></div></a></li>
						<li><a href="board_one.html">1:1 문의<div class="icon_right"><i class="fas fa-angle-right"></i></div></a></li>
						<li><a href="">상품제안<div class="icon_right"><i class="fas fa-angle-right"></i></div></a></li>
					</ul>
				</div>
			</div>

			<div class="board_right">
				<div class="board_list_title">
					<b>공지사항</b>
				</div>
				<table class="board_list">

					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
					 <% if(list.isEmpty()){ %>
						<tr>
							<td colspan="6">조회된 리스트가 없습니다.</td>
						</tr>
					<% } else{ %>
					<% for(Notice n : list){ %>
					<tr>
						<td><%= n.getNoticeNo() %></td>
						<td><%= n.getNoticeTitle() %></td>
						<td><%= n.getNoticeWriter() %></td>
						<td><%= n.getNoticeCount() %></td>
						<td><%= n.getNoticeDate() %></td>
					</tr>
					<%	 } %>
					<%	 } %>
					
				</table>
				<div class="paging">
					<a href="#" class="bt">첫 페이지로</a>
					<a href="#" class="bt">이전 페이지</a>
					<a href="#" class="num on">1</a>
					<a href="#" class="num">2</a>
					<a href="#" class="num">3</a>
					<a href="#" class="bt">다음 페이지</a>
					<a href="#" class="bt">마지막 페이지</a>
				</div>
			<%-- 	<%		} %> --%>
			</div>
			</div>


	</section>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>