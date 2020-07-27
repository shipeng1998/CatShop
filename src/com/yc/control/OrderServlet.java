package com.yc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.OrderDAO;
import com.yc.dao.impl.OrderDAOImpl;
import com.yc.po.ShoppingCartPO;
import com.yc.po.OrderPO;
import com.yc.po.UserPO;
import com.yc.util.LogUtil;
import com.yc.util.StringUtil;


@WebServlet("/order.action")
public class OrderServlet extends BaseServlet{
	OrderDAO dao = new OrderDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String op = request.getParameter("op");
		try{
			if("genOrder".equals(op)){
				doGenOrder(request,response);
			}else if("showOrder".equals(op)){
				doShowOrder(request,response);
			}
		}catch(Exception e){
			LogUtil.logger.debug(e.getMessage());
			e.printStackTrace();
		}
	}

	private void doGenOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// 从页面取值
		
		String sids =request.getParameter("sids");
		//12,13
		
		if(!StringUtil.isNotNull(sids)){
			toPrintJson(response,0);
			return;
		}
		UserPO user = (UserPO) request.getSession().getAttribute("user");
		OrderPO po =parseRequest(request, OrderPO.class);
		//生成订单
		int i =dao.genOrder(sids, user.getUid(),po);
		System.out.println(po.getOid());
		toPrintJson(response,po.getOid());
	}

	private void doShowOrder(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户编号
		UserPO user = (UserPO) request.getSession().getAttribute("user");
		if(null==user){
			toPrintJson(response, 2);
			return;
		}
		String oid=request.getParameter("oid");
		OrderPO po = new OrderPO();
		po.setUid(user.getUid());
		po.setOid(oid);
		try {
			List<OrderPO> list=dao.findByTrem(po);
			toPrintJson(response,list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
