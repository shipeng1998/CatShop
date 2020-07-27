<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../css/icon.css"/>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
ul,li{
   list-style:none; 
}

</style>
</head>
<body class="easyui-layout">
        <div data-options="region:'north'" style="height:150px"></div>
        <div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
        <div data-options="region:'west',split:true" title="导航菜单" style="width:200px;">
             <div class="easyui-accordion" data-options="fit:true">
              <div title="猫咪管理">
             <ul>
             <li><a class="easyui-linkbutton btn"  data-options="plain:true" href="addCat.html">添加猫咪</a></li>
             <li><a class="easyui-linkbutton btn"  data-options="plain:true" href="showCat.html">浏览猫咪</a></li>
             </ul>
             </div>
             <div title="猫用品管理">
             <ul>
             <li><a class="easyui-linkbutton btn"  data-options="plain:true" href="addShopping.html">添加猫用品</a></li>
             <li><a class="easyui-linkbutton btn" data-options="plain:true" href="showShopping.html">浏览猫用品</a></li>
             </ul>
             </div>
             <div title="订单管理">
               <ul>
             <li><a class="easyui-linkbutton btn" data-options="plain:true" href="showOrder.html">浏览订单</a></li>
             <li><a class="easyui-linkbutton btn" data-options="plain:true" href="updateOrder.html">处理订单</a></li>
             </ul>
            </div>
        
           </div>
        </div>
        <div data-options="region:'center'">
        <div id="show_center" class="easyui-tabs"  data-options="fit:true"></div>
        
        </div>
</body>
<script type="text/javascript">
$(function(){
	//默认加载首页选项卡
	$('#show_center').tabs('add',{
		
		title:'yc',
		href:'info.html',
		fit:true,
		tools:[{
			iconCls:'icon-mini-fefresh',
			handler:function(){
				alert('refresh')
			}			
		}]
	
	});
	
});


$('.btn').click(function(){
	var href=$(this).attr('href');
	var name=$(this).html();//获取文本内容
	
	if($('#show_center').tabs('exists',name)){//该选项卡已存在 则选择 否则新建
		$('#show_center').tabs('select',name);
	}else{
		$('#show_center').tabs('add',{
			title:name,
			href:href,
			fit:true,
			tools:[{
				iconCls:'icon-clear',
				handler:function(){
					//关闭选项卡
					$('#show_center').tabs('close',name);
				}			
			}]
		});
	}
	
	return false;
});


</script>


</html>