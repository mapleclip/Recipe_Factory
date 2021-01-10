<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, faqboard.model.vo.Faqboard"%>
<%
	ArrayList<Faqboard> list = (ArrayList<Faqboard>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>FAQ</title>
<script src="https://kit.fontawesome.com/735b504274.js" crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<style>
/* 	section */
.section-area {
	width: 1000px;
	margin: auto;
	margin-top: 50px;
	margin-bottom: 50px;
}

* {
	margin: 0;
	padding: 0;
}

ul li {
	list-style-type: none;
}

.sdb_title {
	padding: 7px 0 20px;
	line-height: 1.5;
}

.sdb_list_menu>li {
	border: 1px solid #dcdbde;
	padding: 13px 13px;
	text-align: left;
	font-size: 13px;
	margin-right: 0;
	width: 130px;
}

table {
	border-collapse: collapse;
}

ul>i {
	text-align: right;
}

caption {
	display: none;
}

a {
	text-decoration: none;
	color: inherit;
}

.board_list_title {
	font-size: large;
	margin-top: 10px;
	margin-bottom: 30px;
	margin-left : 45px;
}

.board_page_wrap {
	width: 100%;
	margin-top: 100px;
	display: flex;
}

.sidebar {
	width: 100px;
	margin-right: 50px;
}

.board_right {
	flex: 1;
	margin-bottom: 100px;
	margin
}

.board_list {
	text-align: center;
}

.board_list {
	width: 100%;
	border-top: 1px solid #589284;
}

.board_list tr {
	border-bottom: 1px solid #ccc;
}

.board_list th, .board_list td {
	padding: 10px;
	font-size: 14px;
}

.board_list td {
	text-align: center;
}

.board_list .tit {
	text-align: left;
}

.board_list .tit:hover {
	text-decoration: underline;
}

.board_page_wrap .paging {
	margin-top: 20px;
	font-size: 0;
	text-align: center;
}

.board_page_wrap .paging a {
	display: inline-block;
	padding: 5px 10px;
	margin-left: 10px;
	font-size: 12px;
	border-radius: 100px;
}

.board_page_wrap .paging a:first-child {
	margin-left: 0;
}

.board_page_wrap .paging a.bt {
	border: 1px solid #eee;
	background: #eee;
}

.board_page_wrap .paging a.num {
	border: 1px solid green;
	font-weight: 600;
	color: green;
}

.board_page_wrap .paging a.num.on {
	background: green;
	color: #fff;
}

.qna_write {
	margin-top: 5%;
	margin-left: 16%;
	padding: 3px 13px;
	background-color: #589284;
	color: white;
	font-size: x-small;
}

.icon_right {
	display: inline-block;
	float: right;
}

li:hover {
	cursor: pointer;
}
.outline {
	width: 1000px;
	min-height: 500px;
	margin-left: auto;
	margin-right: auto;
}
.qboard_title{
	width: 80%;
}


  
 
.faq_subtitle{
	padding-left: 11px;
    font-size: 14px;
    color: #999;
    line-height: 20px;
    letter-spacing: -.3px;
    vertical-align: 1px;
    font-family: noto sans;
    font-weight: 400;
}
* {
  
  box-sizing: border-box;
}
body {
  font-family: 'Montserrat', sans-serif;
 
}
h1 {
  text-align: center;
  margin: 2rem 0;
  font-size: 2.5rem;
}

.accordion {
  width: 90%;
  max-width: 1000px;
  margin: 2rem auto;
}
.accordion-item {
  background-color: #fff;
  color: #111;
  margin: 1rem 0;
  border-radius: 0.5rem;
  box-shadow: 0 2px 5px 0 rgba(0,0,0,0.25);
}
.accordion-item-header {
  padding: 0.5rem 3rem 0.5rem 1rem;
  min-height: 3.5rem;
  line-height: 1.25rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  position: relative;
  cursor: pointer;
}
.accordion-item-header::after {
  content: "\002B";
  font-size: 2rem;
  position: absolute;
  right: 1rem;
}
.accordion-item-header.active::after {
  content: "\2212";
}
.accordion-item-body {
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
}
.accordion-item-body-content {
  padding: 1rem;
  line-height: 1.5rem;
  border-top: 1px solid;
  border-image: linear-gradient(to right, transparent, #34495e, transparent) 1;
}

.on{
	color: #589284;
	background-color: rgba(219, 216, 216, 0.267);
}
.writeNoBtn {
	background-color: #589284;
	font-family: 'Montserrat', sans-serif;
	padding: 3px 13px;
	color: white;
	margin-left: 13px;
	font-size: x-small;
	text-align: right;
}
</style>
</head>

<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="outline">
		
			<div class="board_page_wrap">
				<div class="sidebar">
					<h2 class="sdb_title">고객센터</h2>
					<div class="inner_sdb">
						<ul class="sdb_list_menu">
							<li onclick="noticeboard();">공지사항
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="fre_board();" class="on"><b>자주하는 질문</b>
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="qboard();">1:1 문의
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
							<li onclick="rec_product();">상품제안
								<div class="icon_right">
									<i class="fas fa-angle-right"></i>
								</div>
							</li>
						</ul>
					</div>
				</div>

				<div class="board_right">
					<div class="board_list_title">
						<b>자주하는 질문 
							<span class="faq_subtitle"> 
								고객님들께서 가장 자주하시는 질문을 모두 모았습니다. 
							</span>
						</b>
					</div>
					<div class="accordion">
					
					<% for(int i =0; i<list.size(); i++){ %>
						<% Faqboard fb = list.get(i); %>
						
						<div class="accordion-item">
						<input type="hidden" id="p_id" name="p_id" value="<%= fb.getFboard_no() %>">
							<% if(loginUser != null && loginUser.getMemberid().equals("admin")){ %>
								<div class="accordion-item-header"><%= fb.getFboard_title() %><button id="deleteNoBtn" class="writeNoBtn">삭제</button></div>
							<% }else{ %>
								<div class="accordion-item-header"><%= fb.getFboard_title() %></div>
							<% } %>
							<div class="accordion-item-body">
								<div class="accordion-item-body-content"><%= fb.getFboard_content() %></div>
							</div>
						</div>
						
					<% } %>	
						
						
					</div>
				</div>
			</div>
			<% if (loginUser != null && loginUser.getMemberid().equals("admin")) { %>
				<input type="button" class="qna_write" value="글쓰기" onclick="write_faq();">
			<% } %>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script>
		$('.writeNoBtn').click(function(){
	      let bool = confirm("정말 삭제하시겠습니까?");
	      var pid = $(this).parent().parent().children('#p_id').val();
	      var url = "<%=request.getContextPath()%>/delete.fbn?fbn=" + pid;
	      console.log(url);
	      if(bool){
	         location.href=url;
	      }
	   });
	
// 		let acc = document.getElementsByClassName("accordion");
// 		let i;
	
// 		for (i = 0; i < acc.length; i++) {
// 			acc[i].addEventListener("click", function () {
// 				this.classList.toggle("active");
	
// 				let panel = this.nextElementSibling;
// 				if (panel.style.display === "block") {
// 					panel.style.display = "none";
// 				} else {
// 					panel.style.display = "block";
// 				}
// 			});
// 		}
		function write_faq(){
			location.href='<%=request.getContextPath() %>/GofboardWriteForm.fb';
		}
		function fre_board(){
			location.href="<%=request.getContextPath()%>/FAQListServlet.fa";
		}
		function qboard(){
			location.href="<%= request.getContextPath() %>/QboardListServlet.qb";
		}
		function rec_product(){
			console.log("넘어갑니다~~");
			location.href="<%= request.getContextPath()%>/RecProListServlet.rp";
		}
		const accordionItemHeaders = document.querySelectorAll(".accordion-item-header");

		accordionItemHeaders.forEach(accordionItemHeader => {
		  accordionItemHeader.addEventListener("click", event => {
		    
		 accordionItemHeader.classList.toggle("active");
		    const accordionItemBody = accordionItemHeader.nextElementSibling;
		    if(accordionItemHeader.classList.contains("active")) {
		      accordionItemBody.style.maxHeight = accordionItemBody.scrollHeight + "px";
		    }
		    else {
		      accordionItemBody.style.maxHeight = 0;
		    }
		    
		  });
		});
	</script>
</body>

</html>