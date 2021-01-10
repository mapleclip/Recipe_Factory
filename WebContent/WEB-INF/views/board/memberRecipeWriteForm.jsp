<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 레시피 등록</title>
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
	#insertBtn, #cancelBtn{background: lightgray; border-radius: 10px; color: white; width: 80px; height: 25px; display: inline-block; margin-bottom: 20px;}
	#insertBtn:hover, #cancelBtn:hover{cursor: pointer;}
	#cancelBtn{background: gray;}		
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer" align="center">
		<br>
		<h2>나만의 레시피를 소개해보세요!</h2>
		
		<!-- 파일업로드를 위해 enctype(인코딩타입)을 지정해줘야 된다. -->
		<form action="<%= request.getContextPath() %>/insert.th" method="post" encType="multipart/form-data"><!-- 파일올리는 거기 때문에 -->
			<div class="insertArea">
				<table id="insertThumbTable">
					<tr>
						<th width="100px">레시피 이름</th>
						<td><input type="text" size="50" name="title"></td>
					</tr>
					<tr>
						<th>대표 이미지</th>
						<td>
							<div id="titleImgArea">
								<img id="titleImg" width="300" height="300">
							</div>
						</td>
					</tr>
					<tr>
						<th>내용 사진</th>
						<td>
							<div id="contentImgArea">
								<img id="contentImg" width="800" height="800"> 
							</div>
						</td>
					</tr>
					<tr>
						<th width="100px">레시피 소개</th>
						<td colspan="3"><textarea name="content" rows="20" cols="108" style="resize:none;"></textarea>
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
						
						$("#titleImgArea").click(function(){
							$("#thumbnailImg1").click();
						});
						$("#contentImgArea").click(function(){
							$("#thumbnailImg2").click();
						});
					});
					
					// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
					function LoadImg(value, num){
						if(value.files && value.files[0]){
							var reader = new FileReader();
							
							reader.onload = function(e){								
								switch(num){
								case 1: 
									$("#titleImg").attr("src", e.target.result);
									break;
								case 2:
									$("#contentImg").attr("src", e.target.result);
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
				<input type="submit" id="insertBtn" value="작성완료">
				<input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.memberR'" value="취소하기">
			</div>
		</form>
	</div>
	
	<%@ include file="../common/footer.jsp" %>
	

</body>
</html>