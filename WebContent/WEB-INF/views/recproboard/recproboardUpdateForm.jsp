<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="recproboard.model.vo.RecproBoard"%>
<%
	RecproBoard rb = (RecproBoard)request.getAttribute("rb");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 제안 수정</title>
<style type="text/css">
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

.qna_write {
	margin-top: 5%;
	margin-left: 16%;
	padding: 3px 13px;
	background-color: #589284;
	color: white;
	font-size: x-small;
}

.input_txt {
	width: 14%;
	padding: 20px;
	font-size: x-small;
	text-align: left;
}

.sel_op {
	padding-left: 10px;
	text-align: left;
}

.order_num {
	padding-left: 10px;
	text-align: left;
}

.email_check {
	padding-left: 10px;
	text-align: left;
}

.phone_check {
	padding-left: 10px;
	text-align: left;
}

th {
	background-color: #f3ececbe;
}

.order_btn {
	font-size: x-small;
	height: 20px;
	background-color: #589284;
	color: white;
}
/* .select{
    height: 30px;
} */
.sel_font {
	font-size: 8pt;
}

.notice_qna {
	text-align: left;
}

.list_qna_goods {
	padding: 10px 0;
}

.notice_qna {
	padding: 10px;
	font-size: 10px;
	font-family: "noto sans";
}

.img_file {
	padding: 10px;
}

.sel_file {
	padding: 15px;
}

.icon_right {
	display: inline-block;
	float: right;
}

li:hover {
	cursor: pointer;
}

.submit {
	background-color: #589284;
	margin-top: 5%;
	padding: 3px 13px;
	color: white;
	margin-left: 13px;
	font-size: x-small;
}

.center_align {
	text-align: center;
}

.outline {
	width: 700px;
	margin-left: auto;
	margin-right: auto;
	min-height: 500px;
	margin-bottom: 100px;
}

.editing_area {
	border: "none";
}
.writeNoBtn {
	background-color: #589284;
	margin-top: 5%;
	padding: 3px 13px;
	color: white;
	margin-left: 13px;
	font-size: x-small;
	text-align: right;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="outline">

		<section>
			<div class="outer">
				<br> <br>
				<h2 align="left" style="margin-top: 40px;">상품 제안</h2>
				<br>
				<div class="tableArea">
					<form action="rpbUpdate.re" id="detailForm"
						name="detailForm" method="post">
						<table class="board_list">
							<caption>레시피 제안</caption>
							<tbody>

								<tr>
									<th class="input_txt">제목</th>
									<td class="sel_op">
										<input type="hidden" name = "no" value ="<%= rb.getRecpro_no() %>">
										<input type="text" name="title" value="<%= rb.getRecpro_title() %>">
									</td>
								</tr>
								<tr>
									<th class="input_txt">작성자</th>
									<td class="sel_op"><%= rb.getRecpro_writer() %></td>
								</tr>

								<tr>
									<th class="input_txt">작성일</th>
									<td class="sel_op"><%= rb.getRecpro_date() %></td>
								</tr>
								<tr>
									<th class="input_txt">내용</th>
									<td class="edit_area" style="position: relative;">
										<!-- <style>
									#qnaNotice .qna_goods{display: none;}
								</style> -->
										<div id="qnaNotice">
											<div class="inner_qnaNotice">
												<div class="notice_qna">

													<textarea name="content"
														style="width: 100%; overflow:auto;" class="editing_area"
														cols="20" rows="6"><%= rb.getRecpro_content() %></textarea>
												</div>
											</div>
										</div>
									</td>
								</tr>

							</tbody>
						</table>
						<div align="center">
							<% if (loginUser != null && loginUser.getMemberid().equals(rb.getRecpro_writer())) { %>
							<input type="submit" id="updateNoBtn" value="수정" class="writeNoBtn"> 
							<input type="button" id="deleteNoBtn" value="삭제" onclick="deleteNotice();" class="writeNoBtn">
							<!--  /delete.no?no=(번호)붙여서 보내면 된다. -->
							<% } %>
							<input type="button" onclick="location.href='javascript:history.go(-1);'" id="cancelBtn" value="취소" class="writeNoBtn">
						</div>
					</form>
				</div>
			</div>
			<br>

			
		</section>
	</div>
	<script>
		function deleteNotice(){
			var bool = confirm("정말 삭제하시겠습니까?");
			if(bool){
				location.href="<%=request.getContextPath()%>/delete.qb?qb="+<%=rb.getRecpro_no()%>;
			}
		}
	</script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>