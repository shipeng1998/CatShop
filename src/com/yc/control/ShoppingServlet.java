package com.yc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.ShoppingDAO;
import com.yc.dao.impl.ShoppingDAOImpl;
import com.yc.po.CatPO;
import com.yc.po.ShoppingCartPO;
import com.yc.po.ShoppingPO;
import com.yc.po.UserPO;
import com.yc.util.StringUtil;
@WebServlet("/Shopping.action")
public class ShoppingServlet extends BaseServlet {
	ShoppingDAO dao=new ShoppingDAOImpl(); 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("findPage".equals(op)){
			doFindPage(request, response);
		}else if("Pagetotal".equals(op)){
			doPagetotal(request, response);
		}else if("addShopping".equals(op)){
			doAddShopping(request, response);
		}else if("backfindPage".equals(op)){
			doBackFindPage(request, response);
		}
	}

	private void doBackFindPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ShoppingPO po=parseRequest(request, ShoppingPO.class);
		String pageStr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		
		try {
			int total=dao.findByPageTotal(po);
			List<ShoppingPO> list=null;
			if(StringUtil.isNotNull(rowStr) && StringUtil.isNotNull(pageStr) ){
				list=dao.findByPage(po, Integer.parseInt(pageStr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.findByPage(po, null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doAddShopping(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ShoppingCartPO po=parseRequest(request, ShoppingCartPO.class);
		//获取用户编号
		UserPO user=(UserPO) request.getSession().getAttribute("user");
		if(null==user){
			toPrintJson(response, 2);
			return;
		}
		//获取编号存储到购物车对象中
		po.setUid(user.getUid());
		try {
			int i=dao.addCart(po);
			if(i==3){
				toPrintJson(response, 3);
			}else{
				toPrintJson(response, 1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doPagetotal(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//定义分页参数
	     int pageRows=4;
	      int count;
		try {
			count = dao.findByPageTotal();
			 //计算总页数
		      int pageTotal =count/pageRows;
		      if(count%pageRows>0){
		    	  pageTotal++;
		      }
		      
		      toPrintJson(response, pageTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
	}

	private void doFindPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pageNumber=request.getParameter("pageNumber");
		
		try {
			List<ShoppingPO> list=dao.findByPage(Integer.valueOf(pageNumber) );
			toPrintJson(response, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
