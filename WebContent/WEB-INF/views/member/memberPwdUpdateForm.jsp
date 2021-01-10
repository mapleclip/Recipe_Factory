<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.outer{
		width: 48%; min-height: 400px; background-color: rgba(255, 255, 255, 0.4);
		margin-left: auto; margin-right: auto; margin-top: 5%;
	}
	table{margin: auto;}
	td{height: 50px;}
	input[type="password"]{height:20px; font-size:15px;}
	
	.Btn{font-size: 14px; color:#fff; border: 1px solid  #589284;
	background-color:  #589284; width:80px; height:30px;}	
</style>
<title>비밃번호 수정</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<div class="outer">
	<br>
		<h2 align="center">비밀번호 수정</h2>
		
		<form action="<%= request.getContextPath() %>/updatePwd.me" method="post" id="updatePwdForm" name="updatePwdForm" onsubmit="return send(this);">
			<table>
				<tr>
					<td width="200px"><label>현재 비밀번호</label></td>
					<td><input type="password" name="userPwd" id="userPwd" required="required"></td>
				</tr>
				<tr>
					<td><label>변경 비밀번호</label></td>
					<td><input type="password" name="newPwd" required="required"></td>
				</tr>
				<tr>
					<td><label>변경 비밀번호 확인</label></td>
					<td><input type="password" name="newPwd2" required="required"></td>
				</tr>
			</table>
			
			<br><br>
			
			<div class="btns" align="center">
				<input type="submit" class="Btn" value="변경하기">
				<input type="button" class="Btn" onclick="location.href='javascript:history.back();'" value="취소하기">
			</div>
		</form>
	</div>
	
		<br><br><br><br>
		<%@ include file="../common/footer.jsp" %>
	
	
	<script>
	function send(form){
		var userPwd = $("input[name='userPwd']");
		var newPwd = $("input[name='newPwd']");
		var newPwd2 = $("input[name='newPwd2']");
		
		if(newPwd.val().trim() != newPwd2.val().trim()){
			alert('비밀번호가 다릅니다');
			newPwd2.val('');
			newPwd2.focus();
			return false;
		} else{
			alert('마이페이지로 이동 시 비밀번호 수정 성공!')			
			return true;
		}
	}
	</script>
</body>
</html>