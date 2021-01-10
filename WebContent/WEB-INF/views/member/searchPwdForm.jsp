<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String result = (String)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비빌번호 찾기</title>
<style>
	.outer{
			width: 430px; min-height: 400px;
			margin-left: auto; margin-right: auto; margin-top: 100px;
		} 
		table {border-collapse:seperate; border-spacing:10px 50px;}
		
		input[type="text"]{width:200px; height:50px; font-size:18px;} 
		input[type="password"]{width:200px; height:50px; font-size:18px;} 
		
	.Btn{font-size: 25px; color:#fff; border: 1px solid  #589284;
		background-color:  #589284; width:200px; height:50px;
		margin-bottom:100px; }		
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	
		<div class="outer" align="center">

		<h3>비밀번호 수정</h3>
		
		<form action="<%= request.getContextPath() %>/searchPwd.me" method="post" id="updatePwdForm" name="updatePwdForm" onsubmit="return send(this);">
			<table>
				<tr>
					<th width="200px">아이디</th>
					<td><input type="text" name="userId" id="userId" value="<%= result%>" readonly></td>
				</tr>
				<tr>
					<th>변경 비밀번호</th>
					<td><input type="password" name="newPwd" required="required"></td>
				</tr>
				<tr>
					<th>변경 비밀번호 확인</th>
					<td><input type="password" name="newPwd2" required="required"></td>
				</tr>
			</table>
			
			<br><br>
			

				<input type="submit" value="변경하기" class="Btn">
				<input type="button" class="Btn" onclick="location.href='<%= request.getContextPath() %>'" value="취소하기">

		</form>
	</div>
	
	<%@ include file="../common/footer.jsp" %>
		
	<script>
		function send(form){
			var newPwd = $("input[name='newPwd']");
			var newPwd2 = $("input[name='newPwd2']");
			
			if(newPwd.val().trim() != newPwd2.val().trim()){
				alert('비밀번호가 다릅니다');
				newPwd2.val('');
				newPwd2.focus();
				return false;
			} else {
				alert('로그인 페이지로 이동 시 비밀번호 수정 성공!');				
				return true;
			}
		}
	</script>
</body>
</html>