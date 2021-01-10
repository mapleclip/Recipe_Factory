<%@page import="product.model.vo.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Category> cList = (ArrayList<Category>)request.getAttribute("cList");
	Product p = (Product)request.getAttribute("product");
	ArrayList<PAttachment> fileList = (ArrayList<PAttachment>)request.getAttribute("fileList");
	PAttachment titleImg = fileList.get(0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<style>
	.outer {
		width:1000px; height:850px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	}
	.insertArea {width:500px; height:650px; margin-left:auto; margin-right:auto;}
	.btnArea {width:180px; margin-left:auto; margin-right:auto;}
	#titleImgArea {width:350px; height:200px; border:2px dashed darkgray; text-align:center; display:table-cell; vertical-align:middle;}
	#titleImgArea:hover, #contentImgArea1:hover, #contentImgArea2:hover, #contentImgArea3:hover {cursor:pointer;}
	#contentImgArea1, #contentImgArea2, #contentImgArea3 {
		width:150px; height:100px; border:2px dashed darkgray;
		text-align:center; display:table-cell; vertical-align:middle;
	}
	#insertBtn{background: #D1B2FF;}
	#cancelBtn{background: #B2CCFF}
	#insertThumbTable{margin: auto;}
	.delete-btns{
		display: inline-block;
		border: 1px solid black;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">샹품 등록</h2>
		
		<!-- 파일업로드를 위해 enctype을 지정해줘야 된다. -->
		<form action="<%= request.getContextPath() %>/update.pr" method="post" encType="multipart/form-data" onsubmit="return send(this);"><!-- 파일올리는 거기 때문에 -->
			<div class="insertArea">
				<input type="hidden" name="pId" value="<%= p.getpId() %>">
				<table id="insertThumbTable">
					<tr>
						<th width="100px">상품이름</th>
						<td colspan="3"><input type="text" size="45" name="pName" value="<%= p.getpName() %>"></td>
					</tr>
					<tr>
						<th width="100px">원산지</th>
						<td colspan="3"><input type="text" size="45" name="pOrigin" value="<%= p.getpOrigin() %>"></td>
					</tr>
					<tr>
						<th width="100px">상품가격</th>
						<td colspan="3"><input type="number" size="45" name="pPrice" value="<%= p.getpPrice() %>"></td>
					</tr>
					<tr>
						<th width="100px">상품용량</th>
						<td colspan="3"><input type="text" size="45" name="pCapacity" value="<%= p.getpCapacity() %>"></td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<select id="pCategory" name="pCategory">
								<option value="0">--------</option>
								<% for(Category c : cList){ %>
									<option value="<%= c.getCateId() %>"><%= c.getCateName() %></option>
								<% } %>
							</select>
						</td>	
					</tr>
					<tr>
						<th>대표 이미지</th>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="350" height="200" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= fileList.get(0).getChangeName() %>">
							</div>
							<input type="hidden" name="fId" value="<%= fileList.get(0).getFileId() %>">
							<input type="hidden" name="oldFileName" value="<%= fileList.get(0).getChangeName() %>">
						</td>
					</tr>
					<tr>
						<th>내용 사진</th>
							<td>
								<div id="contentImgArea1">
									<% if(fileList.get(0).getChangeName() != null){ %>
										<img id="contentImg1" width="120" height="100" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= fileList.get(0).getChangeName() %>"> 
									<% }else{ %>
										<img id="contentImg1" width="120" height="100">
									<% } %>
								</div>
								<input type="hidden" name="fId" value="<%= fileList.get(1).getFileId() %>">
								<input type="hidden" name="oldFileName" value="<%= fileList.get(1).getChangeName() %>">
							</td>
					</tr>
					<tr>
						<th width="100px">상품 설명</th>
						<% if(p.getpDescription()!= null){ %>
						<td colspan="3"><textarea name="pDescription" rows="5" cols="50" style="resize:none;"><%= p.getpDescription() %></textarea></td>
						<% }else{ %>
						<td colspan="3"><textarea name="pDescription" rows="5" cols="50" style="resize:none;"></textarea></td>
						<% } %>
					</tr>
				</table>
				
				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
					<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">

				</div>
				<script>
					// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
					$(function(){
						$("#fileArea").hide();
						
						$("#titleImgArea").click(function(){
							$("#thumbnailImg1").click();
						});
						$("#contentImgArea1").click(function(){
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
									$("#contentImg1").attr("src", e.target.result);
									break;
								}
							}
							
							reader.readAsDataURL(value.files[0]);
						}
					}
				</script>
				
			</div>
			<br>
			<div class="btnArea">
				<input type="submit" id="insertBtn" value="작성완료">
				<input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.pr'" value="취소하기">
			</div>
		</form>
	</div>
	<script>
		function send(form){
			var pCategory = $("#pCategory option:selected").val();
			console.log(pCategory);
			if(pCategory == 0){
				alert("카태고리를 선택해주세요.");
				return false;
			}else{
				return true;
			}
		}
	</script>
</body>
</html>