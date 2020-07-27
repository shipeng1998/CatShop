package com.yc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.OrderitemDAO;
import com.yc.dao.impl.OrderitemDAOImpl;
import com.yc.po.OrderitemPO;
@WebServlet("/delete.action")
public class deleteServlet extends BaseServlet {
	OrderitemDAO dao = new OrderitemDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("outTradeNo");
		try {
			List<OrderitemPO> list=dao.findByTrem(oid);
			for(OrderitemPO po:list){
			if(po.getSpid()!=0 && po.getSpid()!=null){
				dao.deleteSpid(po);
			}
			
             if(po.getCid()!=0 && po.getCid()!=null){
				dao.deleteCid(po);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}








