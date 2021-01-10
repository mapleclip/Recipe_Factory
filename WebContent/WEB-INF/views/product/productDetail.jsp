<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="product.model.vo.*, java.util.*"%>
<%
	Product p = (Product)request.getAttribute("product");
	ArrayList<PAttachment> fileList = (ArrayList<PAttachment>)request.getAttribute("fileList");
	PAttachment titleImg = fileList.get(0);
	ArrayList<Review> rList = (ArrayList<Review>)request.getAttribute("rList");
	ArrayList<PQNA> qList = (ArrayList<PQNA>)request.getAttribute("qList");
	PageInfo rpi = (PageInfo)request.getAttribute("rpi");
	PageInfo qpi = (PageInfo)request.getAttribute("qpi");
	
	int listCount = rpi.getListCount();
	int currentPage = rpi.getCurrentPage();
	int maxPage = rpi.getMaxPage();
	int startPage = rpi.getStartPage();
	int endPage = rpi.getEndPage();
	
	int listCountQ = qpi.getListCount();
	int currentPageQ = qpi.getCurrentPage();
	int maxPageQ = qpi.getMaxPage();
	int startPageQ = qpi.getStartPage();
	int endPageQ = qpi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= p.getpName() %></title>
<style>
	.outer {
		width:1000px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	}
   .detail{text-align:left; }
   .detail td{width: 60px; background: rgba(255, 255, 255, 0.4); font-family: "나눔스퀘어"; font-size: 15px; margin-left: 10px;}
   .th{width: 80px; background: rgba(255, 255, 255, 0.4); font-family: "나눔스퀘어"; font-size: 15px; color:gray; margin-left: 20px;}
   #title{backgroung: white;}
   .th{color: #589284; text-align: left;}
      #titleImgArea{margin-left:auto; }
   #titleImg{width:350px; height:450px;}
	#contentArea{height:30px;}
	.detailImgArea{width:995px; margin-left:auto; margin-right:auto; text-align: center;}
	.detailImg{width:800px;}
	.downBtn{background: #D1B2FF;}
	#thumbTable{width: 800px; }
	.replyArea, .qna_area{width:1000px; height: 290px; background-color: #e0e0e0;}
	#line{margin: 50px;}
	
	.pagingBtn{
		display: inline-block;
		height: 15px;
		padding: 0 14px 0;
		font-size: 5px;
		line-height: 15px;
	}
	.pagingBtn:hover{
		cursor:pointer;
	}
	.numBtn, .numBtnQ {
		border: 1px solid black;
	}
	#numBtn1, #numBtnQ1 {
		background-color: #589284;
		border: 1px solid #589284;
		color: #fff;
	}
	.numBtn:hover, .numBtnQ:hover {
		background-color: #e0e0e0;
		border: 1px solid black;
	}
	#numBtn1:hover, #numBtnQ1:hover {
		background-color: #537d73;
		border: 1px solid #537d73;
		color: #fff;
	}
	
	.btn{
		display: inline-block;
		width: 200px;
		height: 50px;
		padding: 0 14px 0;
		font-size: 17px;
		font-family: "나눔스퀘어";
		line-height: 25px;
		text-align:center;
		display: table-cell;
		vertical-align:middle;
	}
	.btn:hover{
		cursor:pointer;
	}
	#btn-buy {
		background: lightgray;
		border: 1px solid lightgray;
	}
	#btn-addCart {
		background-color: #589284;
		border: 1px solid #589284;
		color: #fff;
	}
	#btn-buy:hover {
		background-color: #e0e0e0;
		border: 1px solid lightgray;
	}
	#btn-addCart:hover {
		background-color: #537d73;
		border: 1px solid #537d73;
		color: #fff;
	}
	.tab_area{border-collapse:collapse;}
	.tab{width: 100px; height:32px; text-align: center; padding-top: 10px;}
	.tab:hover{cursor: pointer;}
	#tab_detail{background-color: #589284; color: white;}
	#tab_review{background-color: #589284; color: white;}
	#tab_qna{background-color: #589284; color: white;}
	
	.pop-layer3 .pop-container3 {
		padding: 20px 25px;
	}

	.pop-layer3 p.ctxt3 {
		color: #666;
		line-height: 25px;
	}

	.pop-layer3 {
		display: none;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 400px;
		height: auto;
		background-color: #fff;
		border: 5px solid #589284;
		z-index: 10;
		text-align: center;
	}

	.dim-layer3 {
		display: none;
		position: fixed;
		_position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 100;
	}

	.dim-layer3 .dimBg3 {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: #000;
		opacity: .5;
		filter: alpha(opacity=50);
	}

	.dim-layer3 .pop-layer3 {
		display: block;
	}
	#pqbNo{display: none;}
	
	
	.pop-layer2 .pop-container2 {
		padding: 20px 25px;
	}

	.pop-layer2 p.ctxt2 {
		color: #666;
		line-height: 25px;
	}

	.pop-layer2 {
		display: none;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 400px;
		height: auto;
		background-color: #fff;
		border: 5px solid #589284;
		z-index: 10;
		text-align: center;
	}

	.dim-layer2 {
		display: none;
		position: fixed;
		_position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 100;
	}

	.dim-layer2 .dimBg2 {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: #000;
		opacity: .5;
		filter: alpha(opacity=50);
	}

	.dim-layer2 .pop-layer2 {
		display: block;
	}
	
	
	
	.pop-layer .pop-container {
		padding: 20px 25px;
	}

	.pop-layer p.ctxt {
		color: #666;
		line-height: 25px;
	}

	.pop-layer {
		display: none;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 400px;
		height: auto;
		background-color: #fff;
		border: 5px solid #589284;
		z-index: 10;
		text-align: center;
	}

	.dim-layer {
		display: none;
		position: fixed;
		_position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 100;
	}

	.dim-layer .dimBg {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: #000;
		opacity: .5;
		filter: alpha(opacity=50);
	}

	.dim-layer .pop-layer {
		display: block;
	}

	.layer-btn {
		display: inline-block;
		height: 25px;
		padding: 0 14px 0;
		font-size: 13px;
		line-height: 25px;
	}

	.layer-btn:hover {
		cursor:pointer;
	}
	.btn-r {
		border: 1px solid black;
	}
	.btn-l {
		background-color: #589284;
		border: 1px solid #589284;
		color: #fff;
	}
	.btn-r:hover {
		background-color: #e0e0e0;
		border: 1px solid black;
	}
	.btn-l:hover {
		background-color: #537d73;
		border: 1px solid #537d73;
		color: #fff;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">         
		<form action="<%= request.getContextPath() %>/productUpdateForm.pr" id="detailForm" method="post">
			<table class="detail" id="thumbTable">
				<tr><td><input type="hidden" id="pId" name="pId" value="<%= p.getpId() %>"></td></tr>
				<tr>
					<td rowspan="8">
						<div id="titldImgArea" align="center">
							<img id="titleImg" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= titleImg.getChangeName() %>">
						</div>
					</td>
				</tr>
				<tr>
				<th class="th">상품명</th>
				<td><%= p.getpName() %></td>
				</tr>
				<tr>
				<th class="th">원산지</th>
				<td><%= p.getpOrigin() %></td>
				</tr>
				<tr>
				<th class="th">용　량</th>
				<td><%= p.getpCapacity() %></td>
				</tr>
				<tr>
				<th class="th">가　격</th>
				<td><%= p.getpPrice() %>원</td>
				</tr>
				<tr>
				<th class="th">수　량</th>
				<td><input type="number" id="amount" name="amount" min="1" max="99" value="1"></td>
				</tr>
				<tr>
				<th class="th">총 금액</th>
				<td><label id="price"><%= p.getpPrice() %></label>원</td></tr>
				<tr>
					<td colspan="2">
						<div class="btn" id="btn-addCart">장바구니</div>
<!-- 						<div class="btn" id="btn-buy">구매하기</div> -->
					</td>
				</tr>
				<% if(p.getpDescription() != null){ %>
				<tr>
					<td colspan="2">
						<p id="contentArea">
							<%= p.getpDescription() %>
						</p>
					</td>
				</tr>
				<% } %>
			</table>
			<hr id="line">
			<table class="tab_area">
				<tr>
					<td><div class="tab" id="tab_detail"  onclick="location.href='#tab_detail'">상세페이지</div></td>
					<td><div class="tab" onclick="location.href='#tab_review'">구매후기</div></td>
					<td><div class="tab" onclick="location.href='#tab_qna'">상품질문</div></td>
				</tr>
			</table>
			<table class="detail">
				<% for(int i = 1; i < fileList.size(); i++){ %>
					<% if(fileList.get(i).getChangeName() != null){ %>
					<tr>
						<td>
							<div class="detailImgArea">
								<img id="detailImg<%= i %>" class="detailImg" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= fileList.get(i).getChangeName() %>">
							</div>
						</td>
					</tr>
					<%} %>
				<% } %>
			</table>
			<div align="center">
				<% if(loginUser != null && loginUser.getMemberid().equals("admin")){ %>
				<input type="submit" id="updateBtn" value="수정">
<!-- 				<input type="button" onclick="deleteBoard();" id="deleteBtn" value="삭제"> -->
				<% } %>
			</div>
		</form>
		<table class="tab_area">
			<tr>
				<td><div class="tab" onclick="location.href='#tab_detail'">상세페이지</div></td>
				<td><div class="tab" id="tab_review" onclick="location.href='#tab_review'">구매후기</div></td>
				<td><div class="tab" onclick="location.href='#tab_qna'">상품질문</div></td>
			</tr>
		</table>
		<div class="replyArea">
			<div id="replySelectArea">
				<table id="replySelectTable">
					<thead>
					<tr>
						<th width="150px">제목</th>
						<th width="670px">내용</th>
						<th width="80px">작성자</th>
						<th width="90px">작성일</th>
					</tr>
					</thead>
					<tbody>
					<% if(rList.isEmpty()){ %>
						<tr><td colspan="3">댓글이 없습니다.</td></tr>
					<% }else{ %>
						<% for(int i = 0; i < rList.size(); i++){ %>
						<tr>
							<td style="display: none"><%= rList.get(i).getrNo() %></td>
							<td><%= rList.get(i).getrTitle() %></td>
							<td><%= rList.get(i).getrContent() %></td>
							<td><%= rList.get(i).getmId() %></td>
							<td><%= rList.get(i).getrDate() %></td>
						</tr>
						<% } %>
					<% } %>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagingArea" align="center">
			<% if(endPage == 5){ %>
				<div class="pagingBtn" id="firstBtn">&lt;&lt;</div>
			<% } %>
			<% for(int i = 1; i <= endPage; i++){ %>
				<% if(i == currentPage){ %>
					<div class="pagingBtn numBtn choosen" id="numBtn<%= i %>"><%= i %></div>
				<% }else{ %>
					<div class="pagingBtn numBtn" id="numBtn<%= i %>"><%= i %></div>
				<% } %>
			<% } %>
			<% if(endPage == 5){ %>
				<div class="pagingBtn" id="maxBtn">&gt;&gt;</div>
			<% } %>
		</div>
		
		<div class="buttonArea" align="right">
			<% if(loginUser != null){ %>
			<button onclick="location.href='writeReviewForm.pr?pId=<%= p.getpId() %>'">작성하기</button>
			<% } %>		
		</div>
	<script>
		function selectReply(){
			$('#replySelectTable td').mouseenter(function(){
				$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
			}).mouseout(function(){
				$(this).parent().css('background', 'none');
			}).click(function(){
				var num = $(this).parent().children().eq(0).text();
				$.ajax({
					url: '<%= request.getContextPath() %>/selectReview.pr',
					data: {rNo:num},
					success: function(data){

						
						var $el = $('#review-layer');        //레이어의 id를 $el 변수에 저장
				        var isDim = $el.prev().hasClass('dimBg2');   //dimmed 레이어를 감지하기 위한 boolean 변수
		
				        isDim ? $('.dim-layer2').fadeIn() : $el.fadeIn();
		
				        var $elWidth = ~~($el.outerWidth()),
				            $elHeight = ~~($el.outerHeight()),
				            docWidth = $(document).width(),
				            docHeight = $(document).height();
		
				        // 화면의 중앙에 레이어를 띄운다.
				        if ($elHeight < docHeight || $elWidth < docWidth) {
				            $el.css({
				                marginTop: -$elHeight /2,
				                marginLeft: -$elWidth/2
				            })
				        } else {
				            $el.css({top: 0, left: 0});
				        }
				        
				       	console.log(data);
				       	$('#rTitle').text(data.rTitle);
				       	$('#rWriter').text(data.mId);
				       	$('#rDate').text(data.rDate);
				       	$('#rContent').text(data.rContent);
						
				        $('#btn-layerClose2').click(function(){
				            isDim ? $('.dim-layer2').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				            return false;
				        });
						
				        $('.layer2 .dimBg2').click(function(){
				            $('.dim-layer2').fadeOut();
				            return false;
				        });
						
					}
				});
			});
		}
		function updateTable(p){
			$.ajax({
				url: '<%= request.getContextPath() %>/listReview.pr',
				data: {choosenNum:p, pId:<%= p.getpId() %>},
				success: function(data){
					$tableBody = $('#replySelectTable tbody');
					$tableBody.html('');
					
					$('#replySelectTable > tbody').empty();
					
					$.each(data, function(index, value){
						var $tr = $('<tr>');
						var $rNoTd = $('<td style="display: none">').text(value.rNo);
						var $titleTd = $('<td>').text(value.rTitle);
						var $contentTd = $('<td>').text(value.rContent);
						var $midTd = $('<td>').text(value.mId);
						var $rDateTd = $('<td>').text(value.rDate);
						
						$tr.append($rNoTd);
						$tr.append($titleTd);
						$tr.append($contentTd);
						$tr.append($midTd);
						$tr.append($rDateTd);
						
						$tableBody.append($tr);
					});
					selectReply();
				}
			});
		}
		$(function(){
			selectReply();
		});
		$('#firstBtn').click(function(){
			var past = $('.choosen');
			var num = [1, 2, 3, 4, 5];
			past.removeClass('choosen');
			$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
			$('#numBtn1').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
			<% for(int i = 1; i <= endPage; i++){ %>
				$('#numBtn<%= i %>').text(parseInt(num[<%= i-1 %>]));
			<% } %>
			
			updateTable(1);
		});
		$('.numBtn').click(function(){
			var choosen = $(this);
			var past = $('.choosen');
			var choosenNum = parseInt(choosen.text());
			var pastNum = parseInt(past.text());
			var num = new Array();
			if(<%= endPage %> >=5){
				if(choosenNum == 3){
					num = [1, 2, 3, 4, 5];
					past.removeClass('choosen');
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn3').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
				}else if(choosenNum == 2){
					num = [1, 2, 3, 4, 5];
					past.removeClass('choosen');
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn2').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
				}else if(choosenNum == 1){
					num = [1, 2, 3, 4, 5];
					past.removeClass('choosen');
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn1').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
				}else if(choosenNum>3 && choosenNum<(<%= maxPage %>-2)){
					num = [parseInt(choosen.text())-2, parseInt(choosen.text())-1, parseInt(choosen.text()), parseInt(choosen.text())+1, parseInt(choosen.text())+2];
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn3').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'});
				}else if(choosenNum == <%= maxPage %>){
					num = [(<%= maxPage %>-4), (<%= maxPage %>-3), (<%= maxPage %>-2), (<%= maxPage %>-1), <%= maxPage %>];
					past.removeClass('choosen');
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn5').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
				}else if(choosenNum == (<%= maxPage %>-1)){
					num = [(<%= maxPage %>-4), (<%= maxPage %>-3), (<%= maxPage %>-2), (<%= maxPage %>-1), <%= maxPage %>];
					past.removeClass('choosen');
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn4').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
				}else if(choosenNum == (<%= maxPage %>-2)){
					num = [(<%= maxPage %>-4), (<%= maxPage %>-3), (<%= maxPage %>-2), (<%= maxPage %>-1), <%= maxPage %>];
					past.removeClass('choosen');
					$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtn3').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('chooen');
				}
				<% for(int i = 1; i <= 5; i++){ %>
					$('#numBtn<%= i %>').text(parseInt(num[<%= i-1 %>]));
				<% } %>
			}else{
				past.removeClass('choosen');
				$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
				choosen.css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
			}
			updateTable(choosenNum);
		});
		$('#maxBtn').click(function(){
			var past = $('.choosen');
			var num = [(<%= maxPage %>-4), (<%= maxPage %>-3), (<%= maxPage %>-2), (<%= maxPage %>-1), <%= maxPage %>];
			past.removeClass('choosen');
			$('.numBtn').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
			$('#numBtn5').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosen');
			<% for(int i = 1; i <= endPage; i++){ %>
				$('#numBtn<%= i %>').text(parseInt(num[<%= i-1 %>]));
			<% } %>
			
			updateTable(<%= maxPage %>);
		});
	</script>
	<br>
		<table class="tab_area">
			<tr>
				<td><div class="tab" onclick="location.href='#tab_detail'">상세페이지</div></td>
				<td><div class="tab" onclick="location.href='#tab_review'">구매후기</div></td>
				<td><div class="tab" id="tab_qna" onclick="location.href='#tab_qna'">상품질문</div></td>
			</tr>
		</table>
	<div class="qna_area">
			<div id="qnaSelectArea">
				<table id="qnaSelectTable">
					<thead>
					<tr>
						<th width="150px">작성자</th>
						<th width="590px">제목</th>
						<th width="130px">작성일</th>
						<th width="130px">답변일</th>
					</tr>
					</thead>
					<tbody>
					<% if(qList.isEmpty()){ %>
						<tr><td colspan="3">문의글이 없습니다.</td></tr>
					<% }else{ %>
						<% for(int i = 0; i < qList.size(); i++){ %>
						<tr>
							<td style="display: none"><%= qList.get(i).getPqbNo() %></td>
							<td><%= qList.get(i).getmId() %></td>
							<td><%= qList.get(i).getPqbTitle() %></td>
							<td><%= qList.get(i).getPqbDate() %></td>
							<td>
								<% if(qList.get(i).getPqbADate() != null){%>
									<%= qList.get(i).getPqbADate() %>
								<% }else{ %>
									답변이 없습니다.
								<% } %>
							</td>
						</tr>
						<% } %>
					<% } %>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagingArea" align="center">
			<% if(endPageQ == 5){ %>
				<div class="pagingBtn" id="firstBtnQ">&lt;&lt;</div>
			<% } %>
			<% for(int i = 1; i <= endPageQ; i++){ %>
				<% if(i == currentPageQ){ %>
					<div class="pagingBtn numBtnQ choosenQ" id="numBtnQ<%= i %>"><%= i %></div>
				<% }else{ %>
					<div class="pagingBtn numBtnQ" id="numBtnQ<%= i %>"><%= i %></div>
				<% } %>
			<% } %>
			<% if(endPageQ == 5){ %>
				<div class="pagingBtn" id="maxBtnQ">&gt;&gt;</div>
			<% } %>
		</div>
		
		<div class="buttonArea" align="right">
			<% if(loginUser != null){ %>
			<button onclick="location.href='writePQNAForm.pr?pId=<%= p.getpId() %>'">질문하기</button>
			<% } %>		
		</div>
	</div>
	<script>
		function selectQNA(){
			$('#qnaSelectTable td').mouseenter(function(){
				$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
			}).mouseout(function(){
				$(this).parent().css('background', 'none');
			}).click(function(){
				var num = $(this).parent().children().eq(0).text();
				$.ajax({
					url: '<%= request.getContextPath() %>/selectPQNA.pr',
					data: {pqbNo:num},
					success: function(data){

						
						var $el = $('#qna-layer');        //레이어의 id를 $el 변수에 저장
				        var isDim = $el.prev().hasClass('dimBg3');   //dimmed 레이어를 감지하기 위한 boolean 변수
		
				        isDim ? $('.dim-layer3').fadeIn() : $el.fadeIn();
		
				        var $elWidth = ~~($el.outerWidth()),
				            $elHeight = ~~($el.outerHeight()),
				            docWidth = $(document).width(),
				            docHeight = $(document).height();
		
				        // 화면의 중앙에 레이어를 띄운다.
				        if ($elHeight < docHeight || $elWidth < docWidth) {
				            $el.css({
				                marginTop: -$elHeight /2,
				                marginLeft: -$elWidth/2
				            })
				        } else {
				            $el.css({top: 0, left: 0});
				        }
				        
				       	console.log(data);
				       	$('#pqbNo').text(data.pqbNo);
				       	$('#pqbTitle').text(data.pqbTitle);
				       	$('#mId').text(data.mId);
				       	$('#pqbDate').text(data.pqbDate);
				       	$('#pqbContent').text(data.pqbContent);
				       	if(data.pqbAnswer != null){
					       	$('#pqbADate').text(data.pqbADate);
					       	$('#pqbAnswer').text(data.pqbAnswer);
				       	}else{
				       		$('#pqbADate').text('-');
					       	$('#pqbAnswer').text('아직 답변이 없습니다.');
				       	}
				        $('#btn-layerClose3').click(function(){
				            isDim ? $('.dim-layer3').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				            return false;
				        });
						
				        $('.layer3 .dimBg3').click(function(){
				            $('.dim-layer3').fadeOut();
				            return false;
				        });
						
					}
				});
			});
		}
		function updateQTable(p){
			$.ajax({
				url: '<%= request.getContextPath() %>/listPQNA.pr',
				data: {choosenNum:p, pId:<%= p.getpId() %>},
				success: function(data){
					$tableBody = $('#qnaSelectTable tbody');
					$tableBody.html('');
					
					$('#qnaSelectTable > tbody').empty();
					
					$.each(data, function(index, value){
						var $tr = $('<tr>');
						var $midTd = $('<td>').text(value.mId);
						var $pqbNoTd = $('<td style="display: none">').text(value.pqbNo);
						var $titleTd = $('<td>').text(value.pqbTitle);
						var $pqbDateTd = $('<td>').text(value.pqbDate);
						if(value.pqbADate != null){
							var $pqbADateTd = $('<td>').text(value.pqbADate);
						}else{
							var $pqbADateTd = $('<td>').text('답변이 없습니다.');
						}
						
						$tr.append($pqbNoTd);
						$tr.append($midTd);
						$tr.append($titleTd);
						$tr.append($pqbDateTd);
						$tr.append($pqbADateTd);
						
						$tableBody.append($tr);
						
					});
					selectQNA();
				}
			});
		}
		$(function(){
			selectQNA();
		});
		$('#firstBtnQ').click(function(){
			var past = $('.choosenQ');
			var num = [1, 2, 3, 4, 5];
			past.removeClass('choosenQ');
			$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
			$('#numBtnQ1').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
			<% for(int i = 1; i <= endPageQ; i++){ %>
				$('#numBtnQ<%= i %>').text(parseInt(num[<%= i-1 %>]));
			<% } %>
			
			updateQTable(1);
		});
		$('.numBtnQ').click(function(){
			var choosen = $(this);
			var past = $('.choosenQ');
			var choosenNum = parseInt(choosen.text());
			var pastNum = parseInt(past.text());
			var num = new Array();
			if(<%= endPageQ %> >=5){
				if(choosenNum == 3){
					num = [1, 2, 3, 4, 5];
					past.removeClass('choosenQ');
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ3').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
				}else if(choosenNum == 2){
					num = [1, 2, 3, 4, 5];
					past.removeClass('choosenQ');
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ2').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
				}else if(choosenNum == 1){
					num = [1, 2, 3, 4, 5];
					past.removeClass('choosenQ');
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ1').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
				}else if(choosenNum>3 && choosenNum<(<%= maxPageQ %>-2)){
					num = [parseInt(choosen.text())-2, parseInt(choosen.text())-1, parseInt(choosen.text()), parseInt(choosen.text())+1, parseInt(choosen.text())+2];
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ3').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'});
				}else if(choosenNum == <%= maxPageQ %>){
					num = [(<%= maxPageQ %>-4), (<%= maxPageQ %>-3), (<%= maxPageQ %>-2), (<%= maxPageQ %>-1), <%= maxPageQ %>];
					past.removeClass('choosenQ');
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ5').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
				}else if(choosenNum == (<%= maxPageQ %>-1)){
					num = [(<%= maxPageQ %>-4), (<%= maxPageQ %>-3), (<%= maxPageQ %>-2), (<%= maxPageQ %>-1), <%= maxPageQ %>];
					past.removeClass('choosenQ');
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ4').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
				}else if(choosenNum == (<%= maxPageQ %>-2)){
					num = [(<%= maxPageQ %>-4), (<%= maxPageQ %>-3), (<%= maxPageQ %>-2), (<%= maxPageQ %>-1), <%= maxPageQ %>];
					past.removeClass('choosenQ');
					$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
					$('#numBtnQ3').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('chooenQ');
				}
				<% for(int i = 1; i <= endPageQ; i++){ %>
					$('#numBtnQ<%= i %>').text(parseInt(num[<%= i-1 %>]));
				<% } %>
			}else{
				past.removeClass('choosenQ');
				$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
				choosen.css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
			}
			updateQTable(choosenNum);
		});
		$('#maxBtnQ').click(function(){
			var past = $('.choosenQ');
			var num = [(<%= maxPageQ %>-4), (<%= maxPageQ %>-3), (<%= maxPageQ %>-2), (<%= maxPageQ %>-1), <%= maxPageQ %>];
			past.removeClass('choosenQ');
			$('.numBtnQ').css({'background-color':'white', 'border': '1px solid black', 'color':'black'});
			$('#numBtnQ5').css({'background-color':'#589284', 'border':'1px solid #589284', 'color':'white'}).addClass('choosenQ');
			<% for(int i = 1; i <= endPageQ; i++){ %>
				$('#numBtnQ<%= i %>').text(parseInt(num[<%= i-1 %>]));
			<% } %>
			
			updateQTable(<%= maxPageQ %>);
		});
	</script>
	
	
	<div class="dim-layer">
	    <div class="dimBg"></div>
	    <div id="cart-layer" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<!--content //-->
					<p class="ctxt mb20">
						장바구니에 상품을 담았습니다.
					</p>
					<!--content //-->
					<div class="layer-btn btn-l" id="go-cartList">장바구니 가기</div>
					<div class="layer-btn btn-r" id="btn-layerClose">쇼핑계속 하기</div>
				</div>
			</div>
		</div>
	</div>
	<div class="dim-layer2">
	    <div class="dimBg2"></div>
	    <div id="review-layer" class="pop-layer2">
			<div class="pop-container2">
				<div class="pop-conts2">
					<!--content //-->
					<div id="rTitle"></div>
					<div id="rWriter"></div>
					<div id="rDate"></div>
					<div id="rContent"></div>
					<!--content //-->
					<div class="layer-btn btn-r" id="btn-layerClose2">쇼핑계속 하기</div>
				</div>
			</div>
		</div>
	</div>
	<div class="dim-layer3">
	    <div class="dimBg3"></div>
	    <div id="qna-layer" class="pop-layer3">
			<div class="pop-container3">
				<div class="pop-conts3">
					<!--content //-->
					<div id="pqbNo"></div>
					<div id="pqbTitle"></div>
					<div id="mId"></div>
					<div id="pqbDate"></div>
					<div id="pqbContent"></div>
					<div id="pqbADate"></div>
					<div id="pqbAnswer"></div>
					<!--content //-->
					<% if((loginUser != null && loginUser.getMemberid().equals("admin"))){ %>
						<div class="layer-btn btn-l" id="go-answer">답변달기</div>
					<% } %>
					<div class="layer-btn btn-r" id="btn-layerClose3">쇼핑계속 하기</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>
	<script>
	$('#go-answer').click(function(){
		var pqbNo = $('#pqbNo').text();
		console.log(pqbNo);
		location.href='<%=request.getContextPath() %>/answerPQNAForm.pr?pqbNo=' + pqbNo;
	});
	$('#btn-addCart').click(function(){
		<% if(loginUser != null){ %>
			$.ajax({
				url: 'addCard.pr', //필수 속성
				data: {pId:$('#pId').val(), amount:$('#amount').val(), price:$('#price').text(), pName:'<%= p.getpName() %>'},
				success: function(data){
					if(data == true){
						var $el = $('#cart-layer');        //레이어의 id를 $el 변수에 저장
				        var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수
		
				        isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();
		
				        var $elWidth = ~~($el.outerWidth()),
				            $elHeight = ~~($el.outerHeight()),
				            docWidth = $(document).width(),
				            docHeight = $(document).height();
		
				        // 화면의 중앙에 레이어를 띄운다.
				        if ($elHeight < docHeight || $elWidth < docWidth) {
				            $el.css({
				                marginTop: -$elHeight /2,
				                marginLeft: -$elWidth/2
				            })
				        } else {
				            $el.css({top: 0, left: 0});
				        }
						
				        $('#btn-layerClose').click(function(){
				            isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				            return false;
				        });
						
				        $('.layer .dimBg').click(function(){
				            $('.dim-layer').fadeOut();
				            return false;
				        });
				        
				        $('#go-cartList').click(function(){
				        	location.href='<%=request.getContextPath() %>/cart.me';
				        });
					}else{
						alert("장바구니에 상품을 추가하지 못했습니다.")
					}
				},
				error: function(data){
					alert("장바구니에 상품을 추가하지 못했습니다.")
				}
			});
		<% }else{ %>
			alert("로그인이 되어 있지 않습니다.")
		<% } %>
    });
	$('#amount').change(function(){
		var price = <%= Integer.parseInt(p.getpPrice()) %>
		var amount = $('#amount').val();
		$('#price').text(price*amount);
	});
	</script>
</body>
</html>