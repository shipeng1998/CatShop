<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>猫咪之家</title>
<meta name="keywords" content="animals and pets, free website templates, CSS templates, free download" />
<meta name="description" content="Free CSS Template, free website template for animals and pets websites" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/js.js"></script>
</head>
<body>
<!--  This layout is designed by w w w . t e m p l a t e m o . c o m  -->
<div id="templatemo_container">
  <div id="templatemo_topsection">
    <div id="templatemo_topsection_title">小可爱 &amp; Cat</div>
    <div class="templatemo_topmenu">
      <ul>
        <li><a href="../index.jsp" >首页</a></li>
        <li><a href="../CatShop.jsp" >宠物商城</a></li>
        <li><a href="shopcart.jsp" class="current">购物车</a></li>
        <li><a href="../Shopping.jsp">宠物用品</a></li>
        <li><a href="../login/index.html">登录</a></li>
      </ul>
    </div>
  </div>
  <div class="cont">
   <div class="carImg"><img src="images/car1.jpg" width="951" height="27" /></div>
   <h4 class="orderTitle">收货地址</h4>
   <table class="ord">
    <tr>
     <td width="30%">
     
     </td>
     <td width="50%">
     
     </td>
     <td>
       <span class="green add">[添加收货地址]</span> 
     </td>
    </tr>
   </table><!--ord/-->
   <div class="address">
    <div class="addList">
     
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>详细地址:</label>
     <input type="text" id="address1"/>
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>邮政编码:</label>
     <input type="text" />
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>收件人:</label>
     <input type="text" id="name"/>
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>手机号码:</label>
     <input type="text" id="tel"/> 
    </div><!--addList--> 
    <div class="addList2">
     
    </div><!--addList2/-->
   </div><!--address/-->
   <table class="orderList">
    <tr>
     <th width="20"></th>
     <th width="450">商品</th>
     <th width="130">单价</th>
     <th width="130">数量</th>
     <th width="130">总金额</th>
     <th width="105">操作</th>
    </tr>
    
   
   
    <tr>
     <td><input type="checkbox" /></td>
     <td>
      <dl>
       <dt><img src="images/phone.png" width="85" height="85" /></dt>
       <dd>名称<br /></dd>
       <div class="clears"></div>
      </dl>
     </td>
     <td><strong class="red">￥70.20</strong></td>
     <td>
     <div class="jia_jian">
      <img src="images/jian.jpg" width="21" height="25" class="jian" />
      <input type="text" class="shuliang" value="1" />
      <img src="images/jia.jpg" width="21" height="25" class="jia" />
     </div>
     </td>
     <td><strong class="red">￥70.20</strong></td>
     <td><a href="#" class="green">删除</a></td>
    </tr>
    
    
    
    
   </table><!--orderList/-->
   <tr>
     <td colspan="6"><a href="javascript:deleteAll()" class="shanchu"><img src="images/lajio.jpg" /> 全部删除</a></td>
    </tr>
   <div class="zongji">
    
    <a>总计(不含运费)：</a><strong class="red">￥70.42</strong>
   </div><!--zongji/-->
   <div class="jiesuan">
    <a href="../CatShop.jsp" class="jie_1">继续购物&gt;&gt;</a>
    <a href="javascript:genOrder()" class="jie_2">立即结算&gt;&gt;</a>
    <div class="clears"></div>
   </div><!--jiesuan/-->
   <div class="clears"></div>
  </div><!--cont/-->
<% Date d = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
</div>



   
    
  
    
 


 


</body>
<script type="text/javascript">
$(function(){
	shopcart();
});
function shopcart(){
	$.get('../ShoppingCart.action',{op:'shopcart'},function(data){
		if(2==data){
			alert("请先登录！");
			window.location.href='../login/index.html';
		}else if(null==data || data.length==0){
			alert('购物车为空，请浏览商品');
			window.location.href='../CatShop.jsp';
		}else{
		
			showCartTable(data);
		}
		
	},'json');
}


function showCartTable(arr){
	$('.orderList').html('');
	var str01=' <tr>'
	     +'<th width="20"></th>'
	     +'<th width="450">商品</th>'
	     +' <th width="130">单价</th>'
	     +'<th width="130">数量</th>'
	     +'<th width="130">总金额</th>'
	     +'<th width="105">操作</th>'
	    +'</tr>';
	
	    
	var str="";
	var totalprice=0;
	for(var i=0;i<arr.length;i++){
		totalprice+=arr[i].sprice*arr[i].snum;
		str+='<tr>'
		    +'<td><input type="checkbox"  value="'+arr[i].sid+'"/></td>'
		    +'<td>'
		     +'<dl>'
		     +'<dt><img src="../'+arr[i].sphoto+'" width="85" height="85" /></dt>'
		      +' <dd>'+arr[i].sname+'<br /></dd>'
		       +'<div class="clears"></div>'
		      +'</dl>'
		     +'</td>'
		     +'<td><strong class="red">'+arr[i].sprice+'</strong></td>'
		    +'<td>'
		     +'<div class="jia_jian">'
		      +'<img src="images/jian.jpg" width="21" height="25" class="jian" />'
		     +'<input type="text" class="shuliang" value="'+arr[i].snum+'" />'
		      +'<img src="images/jia.jpg" width="21" height="25" class="jia" />'
		     +'</div>'
		     +'</td>'
		     +'<td><strong class="red">'+(arr[i].sprice*arr[i].snum)+'</strong></td>'
		     +'<td><a href="javascript:deleteSid('+arr[i].sid+')" class="green">删除</a></td>'
		   +' </tr>'
		  
		  
	}
	$('.orderList').html(str01+str);
	$('.zongji strong').html(totalprice);
}

function deleteSid(sid){
	
	$.get('../ShoppingCart.action',{op:'deleteSid',sid:sid},function(data){
		if(1==data){
			
			shopcart();
		}else{
			alert('删除失败！'+data);
		}
	},'json')
}

function deleteAll(){
	
	$.get('../ShoppingCart.action',{op:'deleteAll'},function(data){
		if(0==data){
			
			alert('清空失败！'+data);
		}else{
			alert('购物车为空，请浏览商品');
			window.location.href='../CatShop.jsp';
		}
	},'json')
}



//获取选中的商品信息
//购物车选中的数据也要清空
function genOrder(){
	var arr=$('.orderList td:first-child input[type="checkbox"]:checked');
	var address=$("#address1").val();
	var uname=$("#name").val();
	var tel=$("#tel").val();
	var date = new Date();//该对象包含系统时间
	var ordertime = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
	
	
	if(arr.length==0){
		alert('未选中商品');
		return;
	}
	var sids='';
	for(var i=0;i<arr.length;i++){
		sids+=arr[i].value+",";
	}
	$.post('../order.action',{op:'genOrder',sids:sids,address:address,tel:tel,uname:uname,ordertime:ordertime},function(data){
		if(null!=data){
			//跳转到订单页面
			window.location.href="../order/order.jsp?oid="+data+"";
		}else{
			alert('下单失败！')
		}
	});
}
</script>




</html>