<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="admin.model.vo.*, board.model.vo.*, product.model.vo.*, java.util.ArrayList" %>
<%
	ArrayList<Banner> bList = (ArrayList<Banner>)request.getAttribute("bList");
	ArrayList<BAttachment> fList = (ArrayList<BAttachment>)request.getAttribute("fList");
    ArrayList<Board> btList = (ArrayList<Board>)request.getAttribute("btList");
    ArrayList<Attachment> ftList = (ArrayList<Attachment>)request.getAttribute("ftList");	
    ArrayList<Product> ptList = (ArrayList<Product>)request.getAttribute("ptList");
    ArrayList<PAttachment> pftList = (ArrayList<PAttachment>)request.getAttribute("pftList");	
    ArrayList<ManagerRecipe> manabtList = (ArrayList<ManagerRecipe>)request.getAttribute("manabtList");
    ArrayList<MRAttachment> manaftList = (ArrayList<MRAttachment>)request.getAttribute("manaftList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 팩토리에 오신걸 환영합니다.</title>
 <style>
	.outer{
      width: 100%; border: 5px solid white;
      margin-left: auto; margin-right: auto;
      
   }

/* 	section */
.section-area{
	margin-top: 20px;
	margin-bottom: 50px;
	align: center;
}

	.topRecipe{font-family: "타이포_쌍문동"; font-size: 14px;  color: #3c3c3c; margin-top: 80px; text-align:center;}
	.topRecipe {width:1100px; min-height:550px; margin-left:auto; margin-right:auto; margin-bottom: 10px;}
	#topRecipeList{font-family: "나눔스퀘어"; font-size: 17px; color: #3c3c3c; font-weight: bold; margin: 10px; line-height: 25px;}
	#topRecipeWriter{font-family: "나눔스퀘어"; font-size: 14px; color: gray; font-weight: bold;}
	
	.thumbnailArea {width:800px; height:550px; margin-left:auto; margin-right:auto; margin-top: 20px;}
    .thumb-list {width:300px; border:1px solid white; display:inline-block; margin:10px; align:center;}
    .thumb-list:hover {opacity:0.8; cursor:pointer;}
    
    #p_price{font-family: "나눔스퀘어"; font-size: 17px; color: black; font-weight: bold;}
    .p-thumb-list {width:300px; border:1px solid white; display:inline-block; margin:10px; align:center;}
    .p-thumb-list:hover {opacity:0.8; cursor:pointer;}
    
    .m-thumb-list {width:300px; border:1px solid white; display:inline-block; margin:10px; align:center;}
    .m-thumb-list:hover {opacity:0.8; cursor:pointer;}
	
	#product_name1{font-family: "타이포_쌍문동"; font-size: 20px;  color: #3c3c3c; margin-top: 80px;}
	#product_name1{width:1100px; min-height:550px; margin-left:auto; margin-right:auto; margin-bottom: 10px;}

 
*{margin:0; padding:0; list-style:none;}
#wrapper{
width:1400px;
margin:50px auto;
height:364.58px;
position:relative;
color:#fff;
text-shadow:rgba(0,0,0,0.1) 2px 2px 0px;
}

#slider-wrap{
width:1400px;
height:364.58px;
position:relative;
overflow:hidden;
}

#slider-wrap ul#slider{
width:100%;
height:100%;

position:absolute;
top:0;
left:0;
}

#slider-wrap ul#slider li{
float:left;
position:relative;
width:1400px;
height:364.58px;
}

#slider-wrap ul#slider li > div{
	position:absolute;
	top:20px;
	left:35px;
}

#slider-wrap ul#slider li > div h3{
	font-size:36px;
	text-transform:uppercase;
}

#slider-wrap ul#slider li > div span{
	font-family: Neucha, Arial, sans serif;
	font-size:21px;
}

#slider-wrap ul#slider li img{
display:block;
width:100%;
  height: 100%;
}


/*btns*/
.btns{
position:absolute;
width:50px;
height:60px;
top:50%;
margin-top:-25px;
line-height:57px;
text-align:center;
cursor:pointer;
background:rgba(0,0,0,0.1);
z-index:100;


-webkit-user-select: none;  
-moz-user-select: none; 
-khtml-user-select: none; 
-ms-user-select: none;

-webkit-transition: all 0.1s ease;
-moz-transition: all 0.1s ease;
-o-transition: all 0.1s ease;
-ms-transition: all 0.1s ease;
transition: all 0.1s ease;
}

.btns:hover{
background:rgba(0,0,0,0.3);
}

#next{right:-50px; border-radius:7px 0px 0px 7px;}
#previous{left:-50px; border-radius:0px 7px 7px 7px;}
#counter{
top: 30px; 
right:35px; 
width:auto;
position:absolute;
}

#slider-wrap.active #next{right:0px;}
#slider-wrap.active #previous{left:0px;}


/*bar*/
#pagination-wrap{
min-width:20px;
margin-top:330px;
margin-left: auto; 
margin-right: auto;
height:15px;
position:relative;
text-align:center;
}

#pagination-wrap ul {
width:100%;
}

#pagination-wrap ul li{
margin: 0 4px;
display: inline-block;
width:5px;
height:5px;
border-radius:50%;
background:#fff;
opacity:0.5;
position:relative;
  top:0;
  
  
}

#pagination-wrap ul li.active{
  width:12px;
  height:12px;
  top:3px;
opacity:1;
box-shadow:rgba(0,0,0,0.1) 1px 1px 0px;
}

/*Header*/
h1, h2{text-shadow:none; text-align:center;}
h1{color: #666; text-transform:uppercase;font-size:36px;}
h2{ color: #7f8c8d; font-family: Neucha, Arial, sans serif; font-size:18px; margin-bottom:30px;} 

/*ANIMATION*/
#slider-wrap ul, #pagination-wrap ul li{
-webkit-transition: all 0.3s cubic-bezier(1,.01,.32,1);
-moz-transition: all 0.3s cubic-bezier(1,.01,.32,1);
-o-transition: all 0.3s cubic-bezier(1,.01,.32,1);
-ms-transition: all 0.3s cubic-bezier(1,.01,.32,1);
transition: all 0.3s cubic-bezier(1,.01,.32,1);
}
 </style>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<%@ include file="menubar.jsp" %>
	
 	<section>
 			<div id="wrapper">
			<div id="slider-wrap">
				<ul id="slider">
					<% for(int i = 0; i < fList.size(); i++){ %>
					<%	BAttachment a = fList.get(i); %>
						<li>
							<% if(i == 0){ %>
							<img src="<%= request.getContextPath()%>/main_uploadFiles/<%= a.getChangeName() %>" onclick="location.href='<%= request.getContextPath() %>/signUpForm.me'">
							<% } else if(i == 1){ %>
							<img src="<%= request.getContextPath()%>/main_uploadFiles/<%= a.getChangeName() %>" onclick="location.href='<%= request.getContextPath() %>/list.pr'">
							<% } else if(i == 2){ %>
							<img src="<%= request.getContextPath()%>/main_uploadFiles/<%= a.getChangeName() %>">
							<% } else {%>
							<img src="<%= request.getContextPath()%>/main_uploadFiles/<%= a.getChangeName() %>">
							<% } %>
						</li>
					<% } %>
				</ul>
          
           <!--controls-->
				<div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
				<div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
				<div id="counter"></div>
          
				<div id="pagination-wrap">
					<ul></ul>
				</div>
          <!--controls-->  
			</div>
		</div>
		
		
		<div class="topRecipe">
			<h2>오늘의 레팩 인기 레시피 TOP3!</h2>
			<div class="reipe-list" align="center"></div>

			<% if(manabtList.isEmpty() || manaftList.isEmpty()) { %>
			등록된 게시물이 없습니다.
			<% } else { %>
			<% for(int i = 0; i<manabtList.size(); i++){ %>
			<% ManagerRecipe m = manabtList.get(i); %>
				<div class="m-thumb-list" align="center">
					<div>
						<input type="hidden" value="<%= m.getM_R_NO() %>">
						<% for(int j = 0; j<manaftList.size(); j++) { %>
						<% MRAttachment mf = manaftList.get(j); %>
						<% if(m.getM_R_NO() == mf.getM_R_NO()) { %>
						<img
							src="<%= request.getContextPath()%>/managerRecipe_uploadFiles/<%= mf.getChangeName() %>"
							width="300px" height="400px">
						<%   } %>
						<% } %>
					</div>
					<p id="topRecipeList">
						<!-- 게시글 들어갈 내용 -->
						[<%= m.getM_R_Title() %>]
					</p>
				</div>
				<% } %>
			<% } %>
		</div>
	
		<div class="topRecipe">
			<h2>최근 업데이트된 식재료</h2>
			<div class="reipe-list" align="center"></div>

		<% if(ptList.isEmpty() || pftList.isEmpty()) { %>
		등록된 게시물이 없습니다.
		<% } else { %>
		<% for(int i = 0; i<ptList.size(); i++){ %>
		<% Product p = ptList.get(i); %>
		<div class="p-thumb-list" align="center">
			<div>
				<input type="hidden" value="<%= p.getpId() %>">
				<% for(int j = 0; j<pftList.size(); j++) { %>
				<% PAttachment a = pftList.get(j); %>
				<% if(p.getpId().equals(a.getpId())) { %>
				<img
					src="<%= request.getContextPath()%>/thumbnail_uploadFiles/<%= a.getChangeName() %>"
					width="300px" height="400px">
				<%   } %>
				<% } %>
			</div>
			<p id="topRecipeList">
				<!-- 게시글 들어갈 내용 -->
				[<%= p.getpName() %>]
			</p>
			<p id="p_price">
				<%= p.getpPrice() %>원
			</p>
		</div>
		<% } %>
		<% } %>
	</div>
	
	
		<div class="topRecipe">
			<h2>금주의 회원 인기 레시피 TOP3!</h2>
			<div class="reipe-list" align="center"></div>

		<% if(btList.isEmpty() || ftList.isEmpty()) { %>
		등록된 게시물이 없습니다.
		<% } else { %>
		<% for(int i = 0; i<btList.size(); i++){ %>
		<% Board b = btList.get(i); %>
		<div class="thumb-list" align="center">
			<div>
				<input type="hidden" value="<%= b.getBoardId() %>">
				<% for(int j = 0; j<ftList.size(); j++) { %>
				<% Attachment a = ftList.get(j); %>
				<% if(b.getBoardId() == a.getBoardId()) { %>
				<img
					src="<%= request.getContextPath()%>/thumbnail_uploadFiles/<%= a.getChangeName() %>"
					width="300px" height="400px">
				<%   } %>
				<% } %>
			</div>
			<p id="topRecipeList">
				<!-- 게시글 들어갈 내용 -->
				[<%= b.getBoardTitle() %>]
			</p>
			<p id="topRecipeWriter">
				작성자 :
				<%= b.getBoardWriter() %>
			</p>
		</div>
		<% } %>
		<% } %>
	</div>
	</section>
	
	<%@ include file="../common/footer.jsp" %>
	<script>
		function memberSignUp(){
		      location.href='<%=request.getContextPath() %>/signUpForm.me';
		}
	</script>
	
	<script>
		function memberLogin(){
			location.href="<%= request.getContextPath() %>/loginform.me";
		}
		
		function memberSignUp(){
		      location.href='<%=request.getContextPath() %>/signUpForm.me';
		}
	</script>
	
	   <script>
   		$(function(){
   			$('.thumb-list').click(function(){
   				var bId = $(this).children().children().eq(0).val();
   				location.href="<%= request.getContextPath() %>/detail.memberR?bId=" +bId;
   			});
   		});
   		
		$(function(){
			$('.p-thumb-list').click(function(){
				var pId = $(this).children().children().eq(0).val();
				location.href='<%= request.getContextPath() %>/detail.pr?pId='+ pId;
			});
		});
		
		$(function(){
			$('.m-thumb-list').click(function(){
				var bId = $(this).children().children().eq(0).val();
				location.href='<%= request.getContextPath() %>/detail.MR?bId='+ bId;
			});
		});
   </script>
    <script>
		var pos = 0;
		var totalSlides = $('#slider-wrap ul li').length;
		var sliderWidth = $('#slider-wrap').width();
	
		$(document).ready(function(){
		$('#slider-wrap ul#slider').width(sliderWidth*totalSlides);
		$('#next').click(function(){
		slideRight();
		});
		$('#previous').click(function(){
		slideLeft();
		});
	
		var autoSlider = setInterval(slideRight, 3500);
		$.each($('#slider-wrap ul li'), function() { 
	
		   var li = document.createElement('li');
		   $('#pagination-wrap ul').append(li);   
		});
	
		countSlides();
		pagination();
		$('#slider-wrap').hover(
		  function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
		  function(){ $(this).removeClass('active'); 
		  autoSlider = setInterval(slideRight, 3500); }
		);
	
		});//DOCUMENT READY
	
		function slideLeft(){
		pos--;
		if(pos==-1){ pos = totalSlides-1; }
		$('#slider-wrap ul#slider').css('left', -(sliderWidth*pos)); 
	
		countSlides();
		pagination();
		}
	
		function slideRight(){
		pos++;
		if(pos==totalSlides){ pos = 0; }
		$('#slider-wrap ul#slider').css('left', -(sliderWidth*pos)); 
	
		countSlides();
		pagination();
		}
	
		function countSlides(){
		$('#counter').html(pos+1 + ' / ' + totalSlides);
		}
	
		function pagination(){
		$('#pagination-wrap ul li').removeClass('active');
		$('#pagination-wrap ul li:eq('+pos+')').addClass('active');
		}

</script>
</body>
</html>