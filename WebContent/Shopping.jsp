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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/responsive.css">
	<!-- For Resposive Device -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- For IE -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body >
<!--  This layout is designed by w w w . t e m p l a t e m o . c o m  -->

    <div id="templatemo_topsection_title" style="margin-left: 400px; height: 43px; color: #fff">小可爱 &amp; Cat</div>
    <div  class="templatemo_topmenu">
      <ul style="margin-right: 290px;">
        <li><a href="index.jsp" >首页</a></li>
        <li><a href="CatShop.jsp" >猫咪</a></li>
        <li><a href="Shopping.jsp" class="current">宠物用品</a></li>
        <li><a href="cart/shopcart.jsp">购物车</a></li>
        <li><a href="login/index.html">登录</a></li>
      </ul>
    </div>
 <p>&nbsp;&nbsp;</p>
  <p>&nbsp;&nbsp;</p>
   <p>&nbsp;&nbsp;</p>
 <div style="background: url(images/templatemo_html_bg.jpg) ">
<div class="main-page-wrapper" >
<!-- Shop ____________________________ -->
			<section class="shop-page" >
				<div class="container" >
					<div class="shop-item-wrapper" >
						<div class="row top-select-and-form-section" >
							<div class="col-lg-3  col-sm-4 col-xs-12">
								<div class="">
									<form action="javascript:findCat()">
										<input id="ctype" type="text" placeholder="请输入宠物种类 ...">
										<button><i class="fa fa-search" aria-hidden="true"></i></button>
									</form>
								</div>
							</div>
							<div class="col-lg-6  col-sm-4  col-xs-12">
								<div class="text-center">
									<p>共<span style="color: red">12</span>条商品信息...</p>
								</div>
							</div>
						
						</div>

						<div class="row">
						<div class="showCat">
						
						<div class="col-md-4 col-xs-6 product-item-width" >
								<div class="shop-item-product-wrapper">
									<div class="shop-item-product">
										<div class="clear-fix">
											<a href="#" class="float-left"><i class="fa fa-heart-o" aria-hidden="true"></i></a>
											<a href="#" class="float-right"><i class="flaticon-shopping-bag"></i></a>
										</div>
										<div class="product">
											<img src="images/shop/img-1.png" alt="image">
										</div>
										<div class="price-and-taitle">
											<h5><a href="shop-details.html">俄罗斯猫</a></h5>
											<span>$18</span>
										</div>
									</div>
								</div>
							</div>
						
						</div>
							
							
						
					
						

						<ul class="shop-page-prev-next-button text-center"  >
							<li class="active" style="padding-top:500px;" ><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
						</ul>
					</div>	<!-- / .shop-item-wrapper -->
				</div>
				</div>
			</section>


</div>
 
<div style="text-align:center;" >Copyright © Your Company Name | Designed by <a href="#" target="_parent" style="color: green">YC-71 TOP.SX</a></div> 
</div>   
</body>
<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
<script type="text/javascript">

$(function(){
	var pageNumber=1;
	$.get('Shopping.action',{op:'Pagetotal'},function(pageTotal){
		$('.row ul').html('');
		var str='';
		for(var i=0;i<pageTotal;i++){
			str+='<li class="'+(i+1)+'"  style="padding-top:500px;  " ><a href="javascript:showShopping('+(pageNumber+i)+')">'+(i+1)+'</a></li>';	
		}
		
		$('.row ul').html(str);
		
	},'json');
	
	
	showShopping(pageNumber);
	
});

function showShopping(data){
	
	pageNumber=data;
	
	
	$.get('Shopping.action',{op:'findPage',pageNumber:pageNumber},function(data){
		showShoppingPage(data);
	},'json');
}

function showShoppingPage(arr){
	$('.showCat').html('');
	var str='';
	for(var i=0;i<arr.length;i++){
		str+='<div class="col-md-4 col-xs-6 product-item-width" >'
			+'<div class="shop-item-product-wrapper" >'
		+'<div class="shop-item-product" >'
			+'<div class="clear-fix">'
				
				+'<a href="javascript:addCart('+arr[i].spid+','+arr[i].spprice+',\''+arr[i].spphoto+'\',\''+arr[i].spname+'\')" class="float-right"><i class="flaticon-shopping-bag"></i></a>'
			+'</div>'
			+'<div class="product">'
			  +'<img src="'+arr[i].spphoto+'" alt="image">'
			+'</div>'
			+'<div class="price-and-taitle">'
				+'<h5><a href="shop-details.html">'+arr[i].spname+'</a></h5>'
				+'<span>$'+arr[i].spprice+'</span>'
			+'</div>'
		+'</div>'
	+'</div>'
+'</div>';
	
	}
	//字符串插入class=“showCat” div标签中
	$('.showCat').html(str);
	$('.text-center p span').html(arr.length);
}

function addCart(spid,spprice,spphoto,spname){
	  
	   //发送ajax请求数据添加购物车表中 购物车永久存储
	   $.post("Shopping.action",{op:'addShopping',spid:spid,sprice:spprice,sphoto:spphoto,sname:spname},function(data){
		   if(1==data){
			   alert('添加成功！！');
		   }else if(2==data){
			   alert('用户未登录');
			   window.location.href="login/index.html";
		   }else if(3==data){
			   alert('库存不足！');
			  
		   }else{
			   alert('添加失败');
		   }
	   });
	}





</script>





</html>