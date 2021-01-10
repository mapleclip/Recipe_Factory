<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script src="https://kit.fontawesome.com/735b504274.js"
	crossorigin="anonymous"></script>
<style>
 html, body {height: 100%; margin: 0; padding: 0;}

.frame{
	width: 1000px;
	margin: auto;
}

/* 	header */
.top_banner{
	display: inline-block;
	vertical-align: middle;
	width: 100%; height: 45px;
	background-color: white; color: white;
	border: none;
	font-family: "나눔고딕";
	text-align: center;
}

.top_logo_area{
	display: inline-block;
	width: 158px;
	height: 77px;
	margin-left: 421px;
	margin-top: 10px;
}

#top_logo{
	width: 158px;
	height: 77px;
}

a{
	color: gray;
	text-decoration: none;
}

.top_menu{
	margin-top: 10px;
	float: right;
	width: 300px;
	height: 12px;
	top: 70px;
	font-family: "나눔고딕";
	font-size: 12px;
	font-weight: bold;
	word-spacing: 10px;
}

.top_menu_btn{
	padding: 1px;
	border: 0;
	font-family: "나눔고딕";
	font-size: 11.5px;
	font-weight: bold;
	color: gray; background-color: white;
}
.top_menu_btn:hover{
	cursor: pointer;
	color: black;
}


li { list-style: none; }
#category {
	display: inline-block;
    width: 650px;
}

#category:after {
    content: "";
    display: block;
    clear: both;
}

.main_menu {
    margin: 5px;
    margin-top: 20px;
}
.main_menu:hover {
 cursor: default;
}
.main_menu>li {
    position: relative;
    float: left;
    width: 150px;
    height: 30px;
    box-sizing: border-box;
    line-height: 30px;
    text-align: center;
}

.main_menu>li:hover .sub_menu {
    /*display: block;*/
}

.main_menu>li a {
    display: block;
    transition: all 0.3s;
}

.main_menu>li a:hover {
    background: #589284;
    color: #fff;
}

.sub_menu {
	margin: 0;
    padding: 0;
    display: none;
    position: absolute;
    top: 30px;
    left: 0;
    z-index: 1;
    width: 100%;
    background: #eee;
    opacity: 0.8;
}

#search{
	float: right;
	width: 301px;
	height: 44px;
	background-color: #F5F5F5;
	border-radius: 22px
}

#search{

	float: right;
	width: 250px;
	height: 30px;
	background-color: #F5F5F5;
	border-radius: 22px;
	border-color: #F5F5F5;
	border-style: hidden;
}

#submit{
	float: right;
	height: 33px;
	width: 50px;
	border-radius: 22px;
	border-style: hidden;
	border-color: #589284;
	background-color: #f5f5f5;
	
}
.wrap_search{
	margin-top: 20px;
	width: 320px;
	border-style: solid;
	border-color: #F5F5F5;
	float : right;
	border-radius: 22px;
	background-color: #f5f5f5;
}
.searchPro{
	font-family : FontAwesome;
}
input:focus {
	outline:none;
}
button:focus {
	outline: none;
}
</style>
</head>
<body>
	<header>
		<div class="top_banner"></div>
		<div class="frame top_area">
			<div class="top_logo_area">
				<a href="<%= request.getContextPath() %>">
					<img id="top_logo" src="./images/logo1.png">
				</a>
			</div>
			<div class="top_menu">
				<% if(loginUser == null){ %>
					<input type="button" class="top_menu_btn" id="loginBtn" value="로그인" onclick="memberLogin();">
					<input type="button" class="top_menu_btn" id="signBtn" value="회원가입" onclick="memberSignUp();">
					<input type="button" class="top_menu_btn" id="cartBtn" value="장바구니" onclick="onlyMember();">
					<input type="button" class="top_menu_btn" id="orderBtn" value="주문조회" onclick="onlyMember();">
					<input type="button" class="top_menu_btn" id="myPageBtn" value="마이페이지" onclick="onlyMember();">
				<% } else if(loginUser != null && loginUser.getMembername().equals("운영자")) { %>
					<input type="button" class="top_menu_btn" id="loginBtn" value="로그아웃" onclick="logout();">
					<input type="button" class="top_menu_btn" id="adminBtn" value="관리자페이지" onclick="adminPage();">
				<% } else if(loginUser != null && !loginUser.getMembername().equals("운영자")){ %>
					<input type="button" class="top_menu_btn" id="loginBtn" value="로그아웃" onclick="logout();">
					<input type="button" class="top_menu_btn" id="cartBtn" value="장바구니" onclick="memberCart();">
					<input type="button" class="top_menu_btn" id="orderBtn" value="주문조회" onclick="location.href='<%= request.getContextPath() %>/orderList.me'">
					<input type="button" class="top_menu_btn" id="myPageBtn" value="마이페이지" onclick="location.href='<%= request.getContextPath() %>/myPage.me'">
				<% } %>
		    </div>
		</div>
	</header>
	<nav>
		<div class="frame nav-area">
			<div id="category">
	        	<ul class="main_menu">
	            	<li><a>전체 카테고리</a>
	            		<ul class="sub_menu">
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=10">과일</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=20">채소</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=30">정육</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=40">수산</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=50">유제품</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=60">간식</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=70">곡물</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=80">양념</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=90">가공</a></li>
	            			<li><a href="<%= request.getContextPath() %>/category.pr?cateId=100">기타</a></li>
	            		</ul>
	            	</li>
	                <li><a href="<%= request.getContextPath() %>/list.managerR">레팩 레시피</a></li>
	                <li><a href="<%= request.getContextPath() %>/list.pr">식재료 마켓</a></li>
	                <li><a href="<%= request.getContextPath() %>/list.memberR">회원 레시피</a></li>
	               
	               
	            </ul>
			</div>
			<!-- 검색창 -->
				<div class="wrap_search">
			<form action="searchProduct.sp" method="get">
					<button type="submit" id="submit" class="searchPro"><i class="fas fa-search"></i></button>
					<input type="text" id="search" name = "searchcontent"placeholder="  상품을 검색해주세요">
			</form>
				</div>
		</div>
	</nav>
	<script>


	$(document).ready(function(){
	    $(".main_menu>li").mouseover(function(){
	    	$(this).css("background-color", "#589284").children("a").css("color", "white");
	        $(this).children(".sub_menu").stop().slideDown(200);
	    });
	    $(".main_menu>li").mouseleave(function(){
	    	$(this).css("background-color", "white").children("a").css("color", "gray");
	        $(this).children(".sub_menu").stop().slideUp(200);
	    });
	});



	function memberLogin(){
		location.href="<%= request.getContextPath() %>/loginform.me";
	}
	
	function memberSignUp(){
	      location.href='<%=request.getContextPath() %>/signUpForm.me';
	}
	function logout(){
	      location.href='<%=request.getContextPath() %>/logout.me';
	}
	
	function onlyMember(){
		alert('회원만 이용이 가능합니다. 로그인 후 이용해주세요');
	}
	
	function memberCart(){
		 location.href='<%=request.getContextPath() %>/cart.me';
	}
	function adminPage(){
		location.href='<%=request.getContextPath() %>/main.ad';
	}
	</script>
</body>
</html>