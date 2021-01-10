<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)request.getAttribute("orderName");
	String email = (String)request.getAttribute("email");
	String phone = (String)request.getAttribute("orderPhone");
	String address = (String)request.getAttribute("orderAddress");
	int totalPrice = (int)request.getAttribute("totalPrice");  
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
    <script>
    $(function(){
	
        var IMP = window.IMP; 
        IMP.init('imp74593327');
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '레시피 팩토리',
            amount : <%=totalPrice%>,
            buyer_email : '<%=email%>',
            buyer_name : '<%=name%>',
            buyer_tel : '<%=phone%>',
            buyer_addr : '<%=address%>',
            m_redirect_url : 'https://www.yourdomain.com/payments/complete'
        }, function(rsp) {
	       	   if ( rsp.success ) {
	       	        var msg = '결제가 완료되었습니다. 감사합니다.';
	       	     	location.href='<%=request.getContextPath()%>/order_paySuccess?msg='+msg;
	       	    } else {
	       	        var msg = '결제에 실패하였습니다.';
	       	        msg += '에러내용 : ' + rsp.error_msg;
	       	    }
	       	    alert(msg);        	
        });
        
    });
    </script>
	
	
</body>
</html>