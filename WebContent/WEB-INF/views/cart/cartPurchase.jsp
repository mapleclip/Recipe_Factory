<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.ArrayList, cart.model.vo.*"%>
<% 
	String chk = (String)request.getAttribute("chk"); 
	ArrayList<Cart> list = (ArrayList<Cart>)request.getAttribute("list");
	int total = 0;
	for(Cart c : list) { 
		total += c.getCart_price() * c.getAmount(); 
		
	 }

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문하기</title>
<style>
	.outer{
		min-width: 500px; min-height: 500px; font-size: 14px;
		margin-left: auto; margin-right: auto; margin-top: 5%; margin-bottom: 50px;
	}
	
	table {text-align:center; }
	td{height:40px;}
	
	input[type="text"]{
		height:20px; 
		border : 0px;
		text-align:center;
	}
	hr{width:50%; color:#bcbcbc;}
	
	.btn{
		font-size: 14px; color:#fff; border: 1px solid  #589284;
		background-color:  #589284; width:80px; height:30px;
	}
	
	.Btn{
		font-size: 14px; color:#fff; border: 1px solid  #589284;
		background-color:  #589284; width:150px; height:30px;
	}
	
	
</style>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	<br><br><br><br>

	<input type="hidden" name="chk" value="<%= chk %>">
	
	
	
	<div class="outer">
		<form action="<%= request.getContextPath() %>/importBuy" method="post" id="buyForm" name="buyForm">
			<div id="listArea" align="center">
				<h2 align="center">주문하신 상품</h2>			 
				<table class="table1">
					<tr>
						<td></td>
						<td>상품 코드</td>
						<td>상품 이름</td>
						<td>상품 가격</td>
						<td>개수</td>
					</tr>
					<% for(Cart c : list) { %>
					<tr>
						<td><input type="hidden" name="chk" value="<%= c.getCart_no()%>"></td>
						<td><input type="text" name="p_id" value="<%= c.getP_id()%>" readonly></td>
						<td><input type="text" name="p_name" value="<%= c.getP_name()%>" readonly></td>
						<td><input type="text" name="cart_price" value="<%= c.getCart_price()%>" readonly></td>
						<td><input type="text" name="amount" value="<%= c.getAmount()%>" readonly></td>
					</tr>
					<% } %>
				</table>		
				<br><br><br><br>
				총 금액 : <input type="text" name="total" value="<%= total%>" readonly>	
				<br><br><br><br>
				
			</div>
			<hr>
			<div id="loginUserTable" align="center">
				<h2>배송지</h2>
				<p>(저장되어있는 배송지를 가져옵니다. 수정하실 부분을 적어주세요.)</p>
				<br>
				<table id="table2">
					<tr>
						<td width="200px">수령인</td>
						<td width="200px"><input type="text" name="orderName" value="<%= loginUser.getMembername() %>" required></td>
					</tr>				
					<tr>
						<td>아이디</td>
						<td><input type="text" name="memberId" style="background:lightgray;" readonly value="<%= loginUser.getMemberid()%>"></td>
					</tr>
					<tr>
						<td>휴대폰 번호</td>
					<td><input type="text" maxlength="11" name="orderPhone" placeholder="(-없이)01012345678" value="<%= loginUser.getPhone() == null ? " - " : loginUser.getPhone() %>" required></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email"value="<%= loginUser.getEmail()%>" readonly ></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" id="userPostal" name="joinPostal"  value="<%= loginUser.getPostal() %>"readonly></td>
						<td><div><input type="button" class="btn" id="findPostal" onclick="ifindPostal();" value="검색"></div></td>
					</tr>
					<tr>
						<td>* 주소</td>
						<td><input type="text" id="joinAddress1" name="joinAddress1" value="<%= loginUser.getAddress1()%>" readonly></td>
						<td><span id="guide" style="color:#999;display:none"></span></td>
					</tr>
					<tr>
						<td>* 상세주소</td>
						<td height="60px"><input type="text" id="joinAddress2" name="joinAddress2" value="<%= loginUser.getAddress2()%>" required></td>
					</tr>
					<tr>
						<td>배송메모</td>
						<td><input type="text" id="orderRequire" name ="orderRequire" placeholder="요청사항을 입력해주세요."></td>
					</tr>
				</table>
				<br>
			</div>
			<hr>
			<br><br><br><br>
			<div align="center">
				<input type="submit" onclick="buy(); " value="주문하기" class="Btn">
				<input type="button" onclick="location.href='javascript:history.back();'" value="장바구니 수정하기" class="Btn">
			</div>
			<br><br><br><br>	
		</form>	
	</div><!-- outer마지맘ㄱ -->
	
	
	
	<br><br><br><br>
	<%@ include file="../common/footer.jsp" %>
	
	<script>
	
	function ifindPostal() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraRoadAddr !== '') {
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('userPostal').value = data.zonecode;
                document.getElementById("joinAddress1").value = roadAddr;
//                 document.getElementById("joinAddress2").value = data.jibunAddress;
				document.getElementById("joinAddress2").value = "";

                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if (roadAddr !== '') {
                    document.getElementById("joinAddress1").value += extraRoadAddr;
                } else {
                    document.getElementById("joinAddress1").value += '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if (data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if (data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
	
	</script>
</body>
</html>