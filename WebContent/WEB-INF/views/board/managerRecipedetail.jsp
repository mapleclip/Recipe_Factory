<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.*, product.model.vo.*, java.util.*"%>
    
<%
	ManagerRecipe managerRecipe = (ManagerRecipe)request.getAttribute("ManagerRecipe");
	ArrayList<MRAttachment> fileList = (ArrayList<MRAttachment>)request.getAttribute("fileList");
	MRAttachment titleImg = fileList.get(0);
	
	ArrayList<Product> pMRList = (ArrayList<Product>)request.getAttribute("pMRList");
	ArrayList<PAttachment> aMRList = (ArrayList<PAttachment>)request.getAttribute("aMRList");
 %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레팩 레시피 - <%= managerRecipe.getM_R_Title() %></title>
<style>
.outer {
		width:1000px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	}
	.detail{text-align:center;}
	.detail th, .detail td{width: 1000px; padding: 10px; background: rgba(255, 255, 255, 0.4);}
	.detail th{background: white;}
	#titleImgArea{width:500px; height:300px; margin-left:auto; margin-right:auto;}
	#contentArea{height:30px;}
	.detailImgArea{width:900px; margin-left:auto; margin-right:auto;}
	#titleImg{width:500px; height:300px;}
	.detailImg{width:900px;}
	.downBtn{background: #D1B2FF;}
	#thumbTable{margin: auto;}
	.replyArea{width:1000px; height: 300px; background-color: gray;}
	
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
	.numBtn {
		border: 1px solid black;
	}
	#numBtn1 {
		background-color: #589284;
		border: 1px solid #589284;
		color: #fff;
	}
	.numBtn:hover {
		background-color: #e0e0e0;
		border: 1px solid black;
	}
	#numBtn1:hover {
		background-color: #537d73;
		border: 1px solid #537d73;
		color: #fff;
	}
	
	.btn{
		display: inline-block;
		height: 25px;
		padding: 0 14px 0;
		font-size: 13px;
		line-height: 25px;
	}
	.btn:hover{
		cursor:pointer;
	}
	#btn-buy {
		border: 1px solid black;
	}
	#btn-addCart {
		background-color: #589284;
		border: 1px solid #589284;
		color: #fff;
	}
	#btn-buy:hover {
		background-color: #e0e0e0;
		border: 1px solid black;
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
	#tab_foodList{background-color: #589284; color: white;}
	
	
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
	.foodthumb-list{
	width:320px; border:1px solid white; display:inline-block; margin:10px; align:center;
	}
button:hover{cursor: pointer;}
#updateBtn, #deleteBtn{background: #589284; color: white; width: 120px; height: 50px; display: inline-block; margin-bottom: 20px;}
#updateBtn:hover, #deleteBtn:hover{cursor: pointer;}
#deleteBtn{background: gray;}	
</style>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>  
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<form action="<%= request.getContextPath() %>/managerRecipeUpdateForm.bo" id="detailForm" method="post">
			<table class="detail" id="thumbTable">
				<tr><td><input type="hidden" id="pId" name="pId" value="<%= managerRecipe.getP_Id() %>">
						<input type="hidden" name="bId" value="<%= managerRecipe.getM_R_NO() %>"></td></tr>
				<tr>
					<td rowspan="8">
						<div id="titldImgArea" align="center">
							<img id="titleImg" src="<%= request.getContextPath() %>/managerRecipe_uploadFiles/<%= titleImg.getChangeName() %>">
						</div>
					</td>
				</tr>
				<tr><td><%= managerRecipe.getM_R_Title() %></td></tr>
				<tr><td><%= managerRecipe.getM_R_Origin() %></td></tr>
				<tr><td><%= managerRecipe.getM_R_Capacity() %></td></tr>
				<tr><td><%= managerRecipe.getM_R_price() %>원</td></tr>
				<tr><td>수량 : <input type="number" id="amount" name="amount" min="1" max="99" value="1"></td></tr>
				<tr><td>총 금액 : <label id="price"><%= managerRecipe.getM_R_price() %></label>원</td></tr>
				<tr><td>
					<div class="btn" id="btn-addCart">장바구니</div>
				</td></tr>
			</table>
			
			<br><br><br><br>
			<div align="center">
				<% if(loginUser != null && loginUser.getMemberid().equals("admin")){ %>
				<input type="submit" id="updateBtn" value="수정">
				<input type="button" onclick="deleteBoard();" id="deleteBtn" value="삭제">
				<% } %>
			</div>
			
			<br><br>
			<table class="tab_area">
				<tr>
					<td><div class="tab" id="tab_foodList"  onclick="location.href='#tab_foodList'">재료 보기</div></td>
					<td><div class="tab" onclick="location.href='#tab_detail'">상세페이지</div></td>
				</tr>
			</table>
			<table class="foodList">
				<div class="food-list" align="center"></div>
			
					<% if(pMRList.isEmpty() || aMRList.isEmpty()) { %>
						등록된 재료가 없습니다.
					<% } else { %>
					<% for(int i = 0; i<pMRList.size(); i++){ %>
						<% Product p = pMRList.get(i); %>
						<div class="foodthumb-list" align="center">
							<div>
								<input type="hidden" value="<%= p.getpId() %>">
								<% for(int j = 0; j < aMRList.size(); j++) { %>
									<% PAttachment a = aMRList.get(j); %>
									<% if(p.getpId().equals(a.getpId())) { %>
										<img src="<%= request.getContextPath()%>/thumbnail_uploadFiles/<%= a.getChangeName() %>" 
										 width="80px" height="80px">
									<% } %>
								<% } %>
							</div>
							<p id="foodRecipeList">[재료] <%= p.getpName() %></p>
						</div>
					<% } %>
					<% } %>
		
			</table>
			
			
			<table class="tab_area">
				<tr>
					<td><div class="tab" onclick="location.href='#tab_foodList'">재료 보기</div></td>
					<td><div class="tab" id="tab_detail"  onclick="location.href='#tab_detail'">상세페이지</div></td>
				</tr>
			</table>
			<table class="detail">
				<% for(int i = 1; i < fileList.size(); i++){ %>
					<tr>
						<td>
							<div class="detailImgArea">
								<img id="detailImg<%= i %>" class="detailImg" width="700px" src="<%= request.getContextPath() %>/managerRecipe_uploadFiles/<%= fileList.get(i).getChangeName() %>">
							</div>
						</td>
					</tr>
				<% } %>
			</table>
			
			<script>
	
			function deleteBoard(){
  				var bool = confirm('정말 삭제하시겠습니까?');
  		
  				if(bool){
  					location.href='deleteMR.vo?bId=<%= managerRecipe.getM_R_NO() %>';
  				}
  			}
	
	</script>
		</form>
		
	</div>
	<script>
		$(function(){
			$('.thumb-list').click(function(){
				var pId = $(this).children().children().eq(0).val();
				location.href='<%= request.getContextPath() %>/detail.MR?bId='+ bId;
			});
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
	<%@ include file="../common/footer.jsp" %>
	<script>
	$('#btn-addCart').click(function(){
		<% if(loginUser != null){ %>
// 			var pId = $('#pId').val()
// 			var amount = 
// 			var price = 
// 			var pName = 
			$.ajax({
				url: 'addRCard.ca', //필수 속성
				data: {pId:$('#pId').val(), amount:$('#amount').val(), price:$('#price').text(), pName:'<%= managerRecipe.getM_R_Title() %>'},
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
		var price = <%= managerRecipe.getM_R_price() %>;
		var amount = $('#amount').val();
		console.log(price);console.log(amount);
		$('#price').text(price*amount);
		console.log($('#price').text());
	});
	</script>
</body>
</html>