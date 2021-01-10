<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	Member member = (Member)request.getAttribute("member"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보보기</title>
<style>
	.outer{
		width: 48%; min-height: 450px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 5%; margin-bottom:5%; 
	}
	#myForm td {text-align: center; height:35px;}
	#myForm>table{margin: auto; font-size:14px;}	
	.Btn{font-size: 15px; color:#fff; border: 1px solid  #589284;
		background-color:  #589284; width:110px; height:30px; margin-top:20px;}	

</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	
	<div class="outer">
		<br>
		<h2 align="center"><%= member.getMembername() %>님의 정보</h2>
		<br><hr><br>
		<form action="<%= request.getContextPath() %>/updateForm.me" method="post" id="myForm">
			<table>
				<tr>
					<td width="200px" >아이디</td>
					<td width="200px"><%= member.getMemberid() %></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><%= member.getMembername() %></td>
				</tr>
				<tr>
					<td>휴대폰 번호</td>
					<td><%= member.getPhone() %></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><%= member.getEmail() %></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><%= member.getPostal() %></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><%= member.getAddress1() %></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><%= member.getAddress2() %></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><%= member.getGender() %></td>
				</tr>
				<tr>
					<td>가입일</td>
					<td><%= member.getEnrolldate() %></td>
				</tr>
			</table>
			
			
			<br><br>
			
			<div id="myPageBtns" align="center">
				<input class="Btn" type="submit" value="정보 수정">
				<input class="Btn" type="button" value="비밀번호 수정" onclick="location.href='updatePwdForm.me'">
				<input class="Btn" type="button" value="탈퇴하기" onclick="deleteMember(); ">
				<input type="button" class="Btn" onclick="location.href='<%= request.getContextPath() %>'" value="메인 이동">
			</div>
		</form>

	</div>

	
	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
		function deleteMember(){
			var bool = confirm("정말 삭제하시겠습니까? 다시는 복구할 수 없습니다. 확인을 누르면 바로 삭제됩니다.");
			
			if(bool){
				location.href="delete.me";
			}
		}
	</script>

</body>
</html>