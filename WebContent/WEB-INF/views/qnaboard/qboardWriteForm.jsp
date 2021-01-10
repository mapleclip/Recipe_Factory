<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Q&A 작성</title>
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
						<b>1:1 문의</b>
					</div>
					<form action="<%=request.getContextPath()%>/Qboardinsert.qb"
						method="post">
						<table class="board_list">
							<caption>1:1문의 작성</caption>
							<tbody>
								<tr>
									<th class="input_txt">작성자</th>
									<td class="sel_op">
									 	<input type="text" name="writer" value="<%= loginUser.getMemberid() %>" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th class="input_txt">제목</th>
									<td class="sel_op"><select name="itemcd" id="item_label"
										label="질문유형" class="select">
											<option value class="sel_font">선택해주세요</option>
											<option value="01">배송지연/불만</option>
											<option value="02">반품문의</option>
											<option value="03">A/S문의</option>
											<option value="06">환불문의</option>
									</select> <br> <input type="text" name="title" style="width: 100%"
										requires fld_essentioal label="제목" value></td>
								</tr>

								<tr>
									<th class="input_txt">이메일</th>
									<td class="email_check"><input type="text" name="email"
										size="26" readonly="readonly"
										class="read_only"> <span class="noline smalle"
										style="padding-left: 10px"> <input type="checkbox"
											name="mailing"> <span style="font-size: 8pt;">답변수신을
												이메일로 받겠습니다 </span>
									</span></td>
								</tr>
								<tr>
									<th class="input_txt">문자 메시지</th>
									<td class="phone_check"><input type="text" name="mobile[]"
										readonly="readonly" class="read_only">
										<span class="noline smalle" style="padding-left: 10px">
											<input type="checkbox" name="sms"> <span
											style="font-size: 8pt;">답변수신을 문자메시지로 받겠습니다.</span>
									</span></td>
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
													<strong class="qna_public">1:1문의 작성 전 확인해주세요!</strong><br>

													<dl class="list_qna_goods">
														<dd>
															<span>답변은 영업일 기준 2~3알 소요됩니다.</span> <span>해당 게시판의
																성격과 다른 글은 사전동의 없이 담당 게시판으로 이동될 수 있습니다.</span><br> <span>배송관련,
																주문(취소/교환/환불)관련 문의 및 요청사항은 마이페이지 내 1:1 문의에 남겨주세요.</span>
														</dd>
													</dl>
													<dl class="list_qna_goods">
														<dt>제품</dt>
														<dd>
															<span> - 입고일 : 품정 상품 입고 일이 확정된 경우, 섬네일에 기재되어 있습니다.</span><Br>
															<span> - 제품 상세정보 : 영양성분 및 함량, 용량, 보관 및 취급 방법 등 제품
																정보는 상세 이미지 또는 상세정보에서 확인 가능합니다.</span>
														</dd>
													</dl>
													<dl class="list_qna_goods">
														<dt>반품 / 환불</dt>
														<dd>
															<span> - "제품 하자 혹은 이상으로 반품 (환불)이 필요한 경우 사진과 함께
																구체적인 내용을 남겨주세요."</span>
														</dd>
													</dl>
													<dl class="list_qna_goods">
														<dt>배송</dt>
														<dd>
															<span>- 주문 완료 후 배송 방법은 변경이 불가능합니다.</span><br> <span>-
																배송일 및 배송시간 지정은 불가능합니다.</span><br> <span>※전화번호, 이메일,
																주소, 계좌번호 등의 상세 개인정보가 문의 내용에 저장되지 않도록 주의해 주시기 바랍니다.</span>
														</dd>
													</dl>
													<textarea name="content"
														style="width: 100%; height: 280px; overflow:auto;" class="editing_area"
														cols="30" rows="10" ></textarea>
												</div>
											</div>
										</div>
									</td>
								</tr>

							</tbody>
						</table>
						<div class="center_align">
							<input type="submit" class="submit" value="제출"> <input
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
	function fre_board(){
		location.href="<%=request.getContextPath()%>/FAQListServlet.fa";
	}
	function boardone(){
		location.href="<%=request.getContextPath()%>/board_one.bo";
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