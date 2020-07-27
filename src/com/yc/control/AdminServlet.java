package com.yc.control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.AdminDAO;
import com.yc.dao.impl.AdminDAOImpl;
import com.yc.po.AdminPO;
import com.yc.util.LogUtil;
@WebServlet("/admin.action")
public class AdminServlet extends BaseServlet {
       AdminDAO dao=new AdminDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		
		try{
			if("login".equals(op)){
				doLogin(request,response);
			}
			
		}catch(Exception e){
			LogUtil.logger.debug(new Date()+e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminPO po=parseRequest(request, AdminPO.class);
		AdminPO admin=dao.login(po);
		if(null==admin){
			toPrintJson(response, 0);
		}else{
			//登录成功
			request.getSession().setAttribute("admin",admin);
			toPrintJson(response, 1);
		}
		
	}

}
