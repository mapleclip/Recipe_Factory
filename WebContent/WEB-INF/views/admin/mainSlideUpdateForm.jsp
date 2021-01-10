<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="admin.model.vo.*, java.util.ArrayList" %>
<%
	ArrayList<Banner> bList = (ArrayList<Banner>)request.getAttribute("bList");
	ArrayList<BAttachment> fList = (ArrayList<BAttachment>)request.getAttribute("fList");
	Banner b = bList.get(0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인배너 수정</title>
<style>
	.outer{
      width: 100%; border: 5px solid white;
      margin-left: auto; margin-right: auto;
    }
    .wrap{background: white; width: 100%; height: 50px;}
	.menu{
		background: #589284; color: white; text-align: center;
		vertical-align: middle; width: 200px; height: 80px; display: table-cell;
		font-family: "타이포_쌍문동"; font-size: 20px; border: 3px solid white;
	}
	nav{width: 800px; margin-left: auto; margin-right: auto; margin-top: 40px;}
	.menu:hover {background: beige; color:#589284; font-weight:bold; cursor:pointer;}
	#listArea{text-align: center; font-family: "나눔스퀘어"; font-size: 16px;}
	.tableArea{width:1000px; margin-left:auto;	margin-right:auto; margin-bottom: 30px;}
	table {
	  border-collapse: separate;
	  border-spacing: 0 8px;
	}
	th{border-bottom: 1px solid white; background: #589284; color:white; height:30px;}
	.pagingArea button{border-radius: 15px; background: white; border: none; margin-bottom: 30px;}
	.buttonArea{margin-right: 50px; margin-botton: 30px;}
	.buttonArea button{background: #D1B2FF; border-radius: 5px; color: white; width: 80px; heigth: 25px; text-align: center;}
	button:hover{cursor: pointer;}
	#numBtn{background: lightgray; color: white; font-family: "나눔스퀘어";}
	#choosen{background: white; font-family: "나눔스퀘어";}
	#listArea{margin: 10px;}
	h2{font-family: "타이포_쌍문동"; font-size: 25px;  color: #3c3c3c; margin-top: 80px;}
	td{margin-botton: 100px;}
	#updateBtn, #cancelBtn{background: lightgray; border-radius: 10px; color: white; width: 80px; height: 25px; display: inline-block; margin-bottom: 20px;}
	#updateBtn:hover, #cancelBtn:hover{cursor: pointer;}
	#cancelBtn{background: gray;}		
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer" align="center">
		<div class="wrap">
		<nav>
			<div class="menu" onclick="mainBanner();">메인배너 수정</div>
			<div class="menu" onclick="memberInfo();">회원목록 조회</div>
			<div class="menu" onclick="orderInfo();">배송목록 조회</div>
			<div class="menu" onclick="goHome();">메인 바로가기</div>
		</nav>
		</div>
			
		<br>
		<h2 align="center">메인 배너 변경하기</h2>
		
		<!-- 파일업로드를 위해 enctype(인코딩타입)을 지정해줘야 된다. -->
		<form action="<%= request.getContextPath() %>/mainBannerUpdate.ad" method="post" encType="multipart/form-data"><!-- 파일올리는 거기 때문에 -->
			<div class="insertArea">
				<table id="insertThumbTable">
					<tr>
						<th width="100px">배너 이름 <input type="hidden" name="bId" value="<%= b.getBannerId() %>"></th>
						<td><input type="text" size="119" name="title" value="<%= b.getBannerTitle() %>"></td>
					</tr>
					<tr>
						<th>배너 1</th>
						<td>
							<div id="contentImgArea1">
								<img id="contentImg1" src="<%= request.getContextPath() %>/main_uploadFiles/<%= fList.get(0).getChangeName() %>" width="800" height="350"> 
							</div>
							<input type="hidden" name="fId" value="<%= fList.get(0).getFileId() %>">
							<input type="hidden" name="oldFileName" value="<%= fList.get(0).getChangeName() %>">
						</td>
					</tr>
					<tr>
						<th>배너 2</th>
						<td>
							<div id="contentImgArea2">
								<img id="contentImg2" src="<%= request.getContextPath() %>/main_uploadFiles/<%= fList.get(1).getChangeName() %>" width="800" height="350"> 
							</div>
							<input type="hidden" name="fId" value="<%= fList.get(1).getFileId() %>">
							<input type="hidden" name="oldFileName" value="<%= fList.get(1).getChangeName() %>">							
						</td>
					</tr>
					<tr>
						<th>배너 3</th>
						<td>
							<div id="contentImgArea3">
								<img id="contentImg3" src="<%= request.getContextPath() %>/main_uploadFiles/<%= fList.get(2).getChangeName() %>" width="800" height="350"> 
							</div>
							<input type="hidden" name="fId" value="<%= fList.get(2).getFileId() %>">
							<input type="hidden" name="oldFileName" value="<%= fList.get(2).getChangeName() %>">							
						</td>
					</tr>
					<tr>
						<th>배너 4</th>
						<td>
							<div id="contentImgArea4">
								<img id="contentImg4" src="<%= request.getContextPath() %>/main_uploadFiles/<%= fList.get(3).getChangeName() %>" width="800" height="350"> 
							</div>
							<input type="hidden" name="fId" value="<%= fList.get(3).getFileId() %>">
							<input type="hidden" name="oldFileName" value="<%= fList.get(3).getChangeName() %>">							
						</td>
					</tr>
					<tr>
						<th width="100px">사진 메모</th>
						<td colspan="3"><textarea name="content" rows="10" cols="108" style="resize:none;"><%= b.getBannerContent() %></textarea>
					</tr>
				</table>
				
				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
					<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">
					<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
					<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this,4)">
				</div>
				<script>
					// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
					$(function(){
						$("#fileArea").hide();
						
						$("#contentImgArea1").click(function(){
							$("#thumbnailImg1").click();
						});
						$("#contentImgArea2").click(function(){
							$("#thumbnailImg2").click();
						});
						$("#contentImgArea3").click(function(){
							$("#thumbnailImg3").click();
						});
						$("#contentImgArea4").click(function(){
							$("#thumbnailImg4").click();
						});
					});
					
					// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
					function LoadImg(value, num){
						if(value.files && value.files[0]){
							var reader = new FileReader();
							
							reader.onload = function(e){								
								switch(num){
								case 1: 
									$("#contentImg1").attr("src", e.target.result);
									break;
								case 2:
									$("#contentImg2").attr("src", e.target.result);
									break;
								case 3: 
									$("#contentImg3").attr("src", e.target.result);
									break;
								case 4:
									$("#contentImg4").attr("src", e.target.result);
									break;
								}
							}
							
							reader.readAsDataURL(value.files[0]);
						}
					}
				</script>
				
			</div>
			<br>
			<div class="btnArea" align="center">
				<input type="submit" id="updateBtn" value="작성완료">
				<input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/main.ad'" value="취소하기">
			</div>
		</form>
	</div>
	
		<script>
		function goHome(){
			location.href='<%= request.getContextPath() %>';
		}
		
		function memberInfo(){
			location.href='<%=request.getContextPath() %>/member.ad';
		}
		
		function mainBanner(){
			location.href='<%=request.getContextPath() %>/mainBanner.ad';
		}
		function orderInfo(){
			location.href='<%=request.getContextPath() %>/orderInfo.ad';
		}
	</script>
	
	<%@ include file="../common/footer.jsp" %>
	

</body>
</html>