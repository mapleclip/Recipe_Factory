<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>

	.outer{
			width: 430px; min-height: 400px;
			margin-left: auto; margin-right: auto; margin-top: 100px;
		} 
	
	p{font-size:12px; float:left;  margin-bottom: 1px; margin-top:1px;}	
	
	table {border-collapse:seperate; border-spacing:10px 50px;}
	
	input[type="text"]{width:200px; height:50px; font-size:18px;} 
	
	.Btn{font-size: 25px; color:#fff; border: 1px solid  #589284;
	background-color:  #589284; width:200px; height:50px;
	margin-bottom:100px; }
</style>
</head>
<body>
	
	
	<%@ include file="../common/menubar.jsp"%>
	
	
	<div class="outer" align="center">
		
		<h3>비밀번호 찾기</h3>
		<p>비밀번호의 경우 암호화 저장되어 분실 시 찾아드릴 수 없는 정보입니다.</p>
		<p>본인 확인을 통해 비밀번호를 재설정 하실 수 있습니다.</p>
		<form action="<%= request.getContextPath() %>/searchPwdFirstForm" method="post" name="searchPwdFirst">
			<table>
				<tr>
					<th width="200px">아이디</th>
					<td><input type="text" name="userId" required="required"></td>
				</tr>
				<tr>
				<th>이메일</th> 
					<td><input type="text" name="email" required="required"></td>
				</tr>
			
			</table>
				<input type="submit" value="찾기" class="Btn">
		</form>
	</div>



	<%@ include file="../common/footer.jsp" %>

</body>
</html>