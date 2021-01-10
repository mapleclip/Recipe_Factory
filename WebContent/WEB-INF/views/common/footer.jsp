<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
footer{
	width: 100%;
	height: 250px;
	background-color: #589284; color: white;
	padding-top: 30px;
}
#bottom_logo{
	display: inline-block;
	width: 346px;
	height: 77px;
}

#notice{
	display: inline-block;
	width: 800px;
	color: white;
	font-family: NanumGothic;
	font-size: 12px;
	line-height: 18px;
	letter-spacing: 1px;
}
.bottom_button{
	float: right;
}
.sq{
	width: 150px; height: 50px;
	border: 1px solid white;
	text-align: center;
	display: flex;
	justify-content : center;
	color: white;
	background-color: #589284;
}

#sq_notice{
	font-family: NanumGothic;
	font-size: 13px;
	letter-spacing: 1px;
}

#sq_qna{
	font-family: NanumGothic;
	font-size: 13px;
	letter-spacing: 1px;
}

#sq_de_qna{
	font-family: NanumGothic;
	font-size: 13px;
	letter-spacing: 1px;
}
</style>
</head>
<body>
	<footer>
		<div class="footer">
			<div class="frame botton_area">
				<div id="bottom_logo"><img  src="images/logo2.png"></div>
				<div class="bottom_button">
					
					<input type="button" id="sq_notice" class="sq" id="loginBtn" value="공지사항" onclick="noticeboard();">
					<br>
					
					<input type="button" id="sq_qna" class="sq" id="loginBtn" value="Q & A" onclick="qna();">
					<br>
					<input type="button" id="sq_de_qna" class="sq" id="loginBtn" value="자주묻는 질문" onclick="fqna();">
				</div>
				<div id="notice">
					레시피팩토리에 게시된 모든 컨텐츠들은 저작권법에 의거 보호받고 있습니다. <br> 저작권자 또는
					(주)레시피팩토리의 승인없이 컨텐츠의 일부 또는 전부를 복제,전송,배포 및 기타의 방법으로 저작물을 이용할 경우에는<br>
					저작권법에 의해 법적 조치에 처해질 수 있으므로 주의하시길 바랍니다. <br> <br> 사업자등록번호:
					111-22-33333 통신판매업신고번호 : 제1111-서울역삼-2222호 대표 : 강동기 <br> 주소 :
					서울시 역삼동 어쩌주 저쩌구 고객센터: 1234-1004 <br> <br> <b>ⓒ 2020
						Recipe Factory Corp. Ltd. All rights reserved</b>
				</div>
			</div>
		</div>
	</footer>
	
	<script>
		function noticeboard(){
			location.href="<%= request.getContextPath() %>/NoticeListServlet.no";
		}
		
		function qna(){
		      location.href="<%=request.getContextPath() %>/QboardListServlet.qb";
		}
		function fqna(){
			location.href="<%= request.getContextPath()%>/FAQListServlet.fa";
		}
	</script>
</body>
</html>