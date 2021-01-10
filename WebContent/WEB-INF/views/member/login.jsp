<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
.outer{
		width: 430px; min-height: 400px; margin-left: auto; margin-right: auto; 
		margin-top:100px; margin-bottom: 5%;
	} 
	
.int-id input{ /* ID입력단 */
	width: 430px;
	height: 70px;
	background: #EAEAEA;
	text-align: center; 
	border: none;
	font-size:25px; color:#838383;
	
}
.int-pw input{ /* PW입력단 */
	width: 430px;
	height: 70px;
	background: #EAEAEA;
	text-align: center; 
	border: none;
	font-size:25px; color:#838383;
}

 
/* ===================================================================================================== */
.login_search {
   left: 400px;
   padding-top: 20px;
   padding-bottom: 20px;
   text-indent: 155px;
   float: right;
  
}

/* ============================================================= 아래부터 로그인 버튼 */

.btn_type1 {
	width:430px;
    height: 80px;
	font-size: 25px; color:#fff;
    border: 1px solid  #589284;
    background-color:  #589284;

}
.btn_type2{

	width:430px;
    height: 80px;
	font-size: 25px; color:#589284;
    border: 1px solid  #589284;
    background-color:  #ffff;
    
}

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">

		<form action="<%=request.getContextPath()%>/login.me" method="post" id="myForm">
			<h2 align="center">로그인</h2>
			<br>
			<table class="login-box">
				<tr>
					<td width="200px">
						<div class="int-id"><input type="text" name="userId" size="20" tabindex="1" placeholder="6자 이상의 영문" required></div>
					</td>
				</tr>
				<tr>
					<td>
					<div class="int-pw"><br><input type="password" name="userPwd" size="20" tabindex="2" placeholder="비밀번호를 입력해주세요" required></div>
					</td>
				</tr>
				<tr>
					<td><div class="login_search">
							<a class="link" style="cursor:pointer" onclick="searchId();">아이디 찾기</a> <span class="bar">/</span> 
							<a class="link" style="cursor:pointer" onclick="searchPwd();">비밀번호찾기</a>
						</div></td>
				</tr>
				<tr>
					<td>
						<button style="cursor:pointer" id="btn_type1" type="submit" class="btn_type1"><span class="txt_type">로그인</span>
						</button><br>
						<br>
						<button type="button" style="cursor:pointer" class="btn_type2" id="btn_type2" onclick="memberSignUp();"><span class="txt_type">회원가입</span>
						</button>
					</td>
				</tr>
			</table>

		</form>
	</div>
	<br><br><br><br><br><br><br><br><br>
	
	

	<%@ include file="../common/footer.jsp" %>
	<script>
	

		function memberSignUp(){
		      location.href='<%=request.getContextPath() %>/signUpForm.me';
		};
		
		function searchId(){
			$(this).hover(function(){
				$(this).css('pointer','cursor');
			})
			window.open("searchIdForm.me", "searchIdForm", 'width=400 , height=520 , top=200, left=400');
		};
		
		function searchPwd(){
			location.href='<%=request.getContextPath() %>/searchPwdForm.me';
		};
	</script>
</body>
</html>