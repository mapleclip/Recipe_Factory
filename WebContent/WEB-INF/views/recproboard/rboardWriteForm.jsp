<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>상품 제안 하기</title>
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
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	min-height: 500px;
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
							<li onclick="boardone();">1:1 문의
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="rec_product();">상품제안
								<div class="icon_right" >
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
					<form action="<%=request.getContextPath()%>/Rboardinsert.re"
						method="post">
						<table class="board_list">
							<caption>1:1문의 작성</caption>
							<tbody>
								<tr>
									<th class="input_txt">작성자</th>
									<td class="sel_op">
									
									 	<input type="text" readonly="readonly" name="writer" value="<%= loginUser.getMemberid() %>">
									</td>
								</tr>
								<tr>
									<th class="input_txt">제목</th>
									<td class="sel_op">
									
									
									<input type="text" name="title" style="width: 100%"
										requires fld_essentioal label="제목" value></td>
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
														style="width: 100%; overflow:auto" class="editing_area"
														cols="30" rows="10"></textarea>
												</div>
											</div>
										</div>
									</td>
								</tr>

							</tbody>
						</table>
						<div class="center_align">
							<input type="submit" class="submit" value="등록"> <input
								type="button" onclick="location.href='javascript:history.go(-1);'" class="submit" value="취소" onclick="back();">
						</div>

					</form>


				</div>

			</div>


		</section>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script>
	function noticeboard(){
		location.href="<%=request.getContextPath()%>/NoticeListServlet.no";
	}
	function boardone(){
		location.href="<%=request.getContextPath()%>/QboardListServlet.qb";
	}

	function back() {
		location.href = "<%=request.getContextPath()%>/board_one.bo";
	}
	function rec_product(){
		console.log("넘어갑니다~~");
		location.href="<%= request.getContextPath()%>/RecProListServlet.rp";
	}
</script>
</body>

</html>