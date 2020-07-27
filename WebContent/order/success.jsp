<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="animals and pets, free website templates, CSS templates, free download" />
<meta name="description" content="Free CSS Template, free website template for animals and pets websites" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<title>猫咪之家</title>
</head>
<body>
<div id="templatemo_container">
  <div id="templatemo_topsection">
    <div id="templatemo_topsection_title">小可爱 &amp; Cat</div>
    <div class="templatemo_topmenu">
      <ul>
        <li><a href="../index.jsp" >首页</a></li>
        <li><a href="../CatShop.jsp" >宠物商城</a></li>
        <li><a href="../Shopping.jsp">宠物用品</a></li>
        <li><a href="../cart/shopcart.jsp" class="current" >购物车</a></li>
        <li><a href="login/index.html">登录</a></li>
      </ul>
    </div>
  </div>
  
</div>
<div class="car">
  <div class="cont">
   <div class="chenggong">
    <h3>下单成功</h3>
    <div class="zhifu">
     您选择的支付方式是 <strong style="color:red">支付宝</strong><br />
     
    </div><!--zhifu/-->
   </div><!--chenggong/-->
  </div><!--cont/-->
 </div><!--car/-->
<%String outTradeNo=request.getParameter("outTradeNo"); %>
<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
<script type="text/javascript">
$(function(){
	$.get('../delete.action',{outTradeNo:'<%=outTradeNo%>'},function(data){
		if(data==1){
			
		}
	});
});

</script>
</body>
</html>