<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String result = (String)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>
.section_login {
	width: 300px;
	margin: 0 auto;
	padding-top: 50px;
	letter-spacing: -.6px;
}

.tit_login {
	font-weight: 800;
	font-size: 20px;
	line-height: 20px;
	margin: 0 auto;
	text-align: center;
}

.name {
	width: 300px;
	height: 50px;
	background: #EAEAEA;
	text-align: center;
	border: none;
	font-size: 18px;
	color: #838383;
}

.email {
	width: 300px;
	height: 50px;
	background: #EAEAEA;
	text-align: center;
	border: none;
	font-size: 18px;
	color: #838383;
}

.section_login .find_view .tit_label {
	display: block;
	padding: 11px 0 7px;
	font-size: 12px;
	line-height: 18px;
	text-align: left;
}

.btn_type1 {
	width: 305px;
	height: 60px;
	font-size: 25px;
	color: #fff;
	border: 1px solid #589284;
	background-color: #589284;
}

.Btn{
	width: 80px;
	height: 30px;
	font-size: 15px;
	color: #fff;
	border: 1px solid #589284;
	background-color: #589284;
}
</style>
</head>
<body>
	<div class="section_login">
		<h3 class="tit_login">아이디 찾기</h3>
		<div class="write_form find_view">
			<form action="<%=request.getContextPath()%>/searchId.me"
				method="post" id="myForm">
				<input type="hidden" name="act" value="Y"> <input
					type="hidden" name="rncheck" value="none"> <input
					type="hidden" name="dupeinfo" value=""> <strong
					class="tit_label">이름</strong> <input type="text" id="membername"
					class="name" name="membername" tabindex="2" size="18"
					required="required" placeholder="고객님  성함 "> <strong
					class="tit_label">이메일</strong> <input type="text" id="email"
					class="email" name="email" size="18" tabindex="5"
					required="required" placeholder="가입 시 등록 이메일 주소"> <br>
				<br> <br>
				<button type="submit" class="btn_type1">
					<span class="txt_type">확인</span>
				</button>
			</form>
		</div>
	</div>
	<% if(result != null){ %>
		<h4 align="center">회원님의 아이디는</h4>
		<h2 align="center">'<%=result%>' 입니다.</h2>
	<% }else{ %>
		<br><br><br><br><br>
	<% } %>
	<div class="BtnDiv" align="center">
		<input type="button" onclick="window.close();" class="Btn" value="닫기">
	</div>
</body>
</html>