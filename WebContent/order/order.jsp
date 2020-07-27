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
  <div class="car">
  <div class="cont">
   <div class="carImg"><img src="images/car2.jpg" width="961" height="27" /></div>
   
   <h4 class="orderTitle" >支付方式</h4>
   <ul class="zhiList">
   <li>网银支付-借记卡</li>
   <li>网银支付-信用卡</li>
   
   <li>支付宝-余额支付</li>
   <div class="clears"></div>
  </ul><!--zhiList/-->
  <div class="zhifufangshi">
   <ul class="yinhang">
    <li><input type="radio" /><img src="images/yin1.gif" /></li>
    <li><input type="radio" /><img src="images/yin2.gif" /></li>
    <li><input type="radio" /><img src="images/yin3.gif" /></li>
    <li><input type="radio" /><img src="images/yin4.gif" /></li>
    <li><input type="radio" /><img src="images/yin5.gif" /></li>
    <li><input type="radio" /><img src="images/yin6.gif" /></li>
    <li><input type="radio" /><img src="images/yin7.gif" /></li>
    <li><input type="radio" /><img src="images/yin8.gif" /></li>
    <li><input type="radio" /><img src="images/yin9.gif" /></li>
    <li><input type="radio" /><img src="images/yin1.gif" /></li>
    <li><input type="radio" /><img src="images/yin2.gif" /></li>
    <li><input type="radio" /><img src="images/yin3.gif" /></li>
    <li><input type="radio" /><img src="images/yin4.gif" /></li>
    <li><input type="radio" /><img src="images/yin5.gif" /></li>
    <li><input type="radio" /><img src="images/yin6.gif" /></li>
    <li><input type="radio" /><img src="images/yin7.gif" /></li>
    <li><input type="radio" /><img src="images/yin8.gif" /></li>
    <div class="clears"></div>
   </ul>
  </div><!--zhzhifufangshii/-->
  <div class="zhifufangshi">
   <ul class="yinhang">
    <li><input type="radio" /><img src="images/yin7.gif" /></li>
    <li><input type="radio" /><img src="images/yin8.gif" /></li>
    <li><input type="radio" /><img src="images/yin9.gif" /></li>
    <li><input type="radio" /><img src="images/yin1.gif" /></li>
    <li><input type="radio" /><img src="images/yin2.gif" /></li>
    <li><input type="radio" /><img src="images/yin3.gif" /></li>
    <li><input type="radio" /><img src="images/yin4.gif" /></li>
    <li><input type="radio" /><img src="images/yin5.gif" /></li>
    <li><input type="radio" /><img src="images/yin6.gif" /></li>
    <li><input type="radio" /><img src="images/yin7.gif" /></li>
    <li><input type="radio" /><img src="images/yin8.gif" /></li>
    <div class="clears"></div>
   </ul>
  </div><!--zhzhifufangshii/-->
  
  <div class="zhifufangshi">
   <ul class="yinhang">
    <li><input type="radio" /><img src="images/zhifubao.jpg" /></li>
    <div class="clear"></div>
   </ul>
  </div><!--zhzhifufangshii/-->
  <h4 class="orderTitle">购物清单</h4>
  <table class="orderList">
    <tr>
     <th width="20"></th>
     <th width="430">商品</th>
     <th width="135">单价</th>
     <th width="150">数量</th>
     <th width="149">总金额</th>
    </tr>
    
    
    <tr>
     <td><input type="checkbox" /></td>
     <td>
      <dl>
       <dt><a href="#"><img src="images/phone.png" width="85" height="85" /></a></dt>
       <dd>地址<br /><span class="red">有货：</span></dd>
       <div class="clears"></div>
      </dl>
     </td>
     <td><strong class="red">￥70.20</strong></td>
     <td>
     <div class="jia_jian">
      <div class="jia_jian">
       <dd style="margin-left:70px;margin-top:-23px;font-size:12px;font-weight:bold">1</dd>
     </div>
     </div>
     </td>
     <td><strong class="red">￥70.20</strong></td>
    </tr>
    
   
   </table><!--orderList/-->
   <table class="zongjia" align="right">
   
    
    <tr>
     <td colspan="2" style="height:50px;">
      <a href="javascript:zfb()"><img src="images/tijao.png" width="142" height="32" /></a>
     </td>
    </tr>
   </table><!--zongjia/-->
   <div class="clears"></div>
  </div><!--cont/-->
 </div><!--car/-->
  
</div>
</body>
<script type="text/javascript">
$(function(){
	showorder();
});
function showorder(){
	<% String oid=request.getParameter("oid");%>

	$.get('../order.action',{op:'showOrder',oid:<%=oid%>},function(data){
		if(2==data){
			alert("请先登录！");
			window.location.href='../login/index.html';
		}else if(null==data || data.length==0){
			alert('没有订单哟');
			//window.location.href='../CatShop.jsp';
		}else{
		
			showOrderTable(data);
		}
		
	},'json');
}
function showOrderTable(arr){
	$('.orderList').html('');
	var str01=' <tr>'
	     +'<th width="430">商品编号</th>'
	     +' <th width="135">时间</th>'
	     +'<th width="150">收货人</th>'
	     +'<th width="149">总金额</th>'
	    +'</tr>';
	    
	var str="";
	var totalprice=0;
	for(var i=0;i<arr.length;i++){
		totalprice+=arr[i].sprice*arr[i].snum;
		str+='<tr>'
				+'<tr>'
				+' <td>'
				+'<strong class="red" id="oid">'+arr[i].oid+'</strong>'
				+'</td>'
				+'<td><strong class="red" >'+arr[i].ordertime+'</strong></td>'
				+'<td>'
				+'	<div class="jia_jian">'
				+'	<div class="jia_jian">'
				+' 	<dd style="margin-left:70px;margin-top:-23px;font-size:12px;font-weight:bold">'+arr[i].uname+'</dd>'
				+'	</div>'
				+'	</div>'
				+'	</td>'
				+'	<td><strong class="red" style="color:red" id="totalprice">'+arr[i].totalprice+'</strong></td>'
				+'	</tr>'
		   +' </tr>'	  
	}
	$('.orderList').html(str01+str);
	
	
}
function zfb(){
	var oid=$('#oid').html();
	var totalprice=$('#totalprice').html();
		 window.location.href="../trade_precreate.jsp?outTradeNo="+oid+"&subject='catshop'&totalAmount="+totalprice+"";
}


</script>
</html>