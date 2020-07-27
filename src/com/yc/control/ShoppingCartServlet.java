package com.yc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.ShoppingCartDAO;
import com.yc.dao.impl.ShoppingCartDAOImpl;
import com.yc.po.ShoppingCartPO;
import com.yc.po.UserPO;
@WebServlet("/ShoppingCart.action")
public class ShoppingCartServlet extends BaseServlet {
    ShoppingCartDAO dao=new ShoppingCartDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("addCart".equals(op)){
			doAddCart(request, response);
		}else if("shopcart".equals(op)){
			doShowCart(request,response);
		}else if("deleteAll".equals(op)){
			doDeleteAll(request, response);
			
		}else if("deleteSid".equals(op)){
			doDeleteSid(request, response);
		}
	}
	
	private void doDeleteAll(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		UserPO user = (UserPO) request.getSession().getAttribute("user");
		ShoppingCartPO po = new ShoppingCartPO();
		
		po.setUid(user.getUid());
		try{
			int i =dao.deleteAll(po);
			toPrintJson(response, i);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void doDeleteSid(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		ShoppingCartPO po =parseRequest(request, ShoppingCartPO.class);
		try {
			
			int i =dao.deleteSid(po);
			toPrintJson(response, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doShowCart(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户编号
		UserPO user = (UserPO) request.getSession().getAttribute("user");
		if(null==user){
			toPrintJson(response, 2);
			return;
		}
		ShoppingCartPO po = new ShoppingCartPO();
		po.setUid(user.getUid());
		try {
			List<ShoppingCartPO> list=dao.findByTrem(po);
			toPrintJson(response,list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAddCart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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

}
