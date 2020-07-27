package com.yc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.UserDAO;
import com.yc.dao.impl.UserDAOImpl;
import com.yc.po.UserPO;
import com.yc.util.webUtil;
@WebServlet("/user.action")
public class UserServlet extends BaseServlet {
      UserDAO dao=new UserDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		webUtil.setEncoding(request, response);
		String op=request.getParameter("op");
		if("login".equals(op)){
			doLogin(request, response);
		}else if("register".equals(op)){
			doRegister(request, response);
		}else if("checkName".equals(op)){
			doCheckName(request, response);
		}
	}

	private void doCheckName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserPO po=parseRequest(request, UserPO.class);
		try {
			
			List<UserPO> list=dao.findByterm(po);
			
			PrintWriter out=response.getWriter();
			//out.print(list);
			if(null!=list && list.size()>0){
				out.print(2);//不可用
			}else{
				out.print(1);//可用
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserPO po=parseRequest(request, UserPO.class);
		try {
			int i=dao.addUser(po);
			PrintWriter out=response.getWriter();
			out.print(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserPO po=parseRequest(request, UserPO.class);
		try {
			UserPO user=dao.login(po);
			PrintWriter out=response.getWriter();
			if(null==user){
				out.print(0);
			}else{
				//存储到session
				request.getSession().setAttribute("user",user);
				out.print(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
