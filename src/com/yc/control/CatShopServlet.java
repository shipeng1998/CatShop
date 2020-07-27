package com.yc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.CatDAO;
import com.yc.dao.impl.CatDAOImpl;
import com.yc.po.CatPO;

import com.yc.util.StringUtil;


@WebServlet("/CatShop.action")
public class CatShopServlet extends BaseServlet {
  CatDAO dao=new CatDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("findAll".equals(op)){
			doFindAll(request, response);
		}else if("findCat".equals(op)){
			doFindCat(request, response);
		}else if("backfindPage".equals(op)){
			doBackFindPage(request, response);
		}
	}

	

	private void doBackFindPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		CatPO po=parseRequest(request, CatPO.class);
		String pageStr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		
		try {
			int total=dao.findByPageTotal(po);
			List<CatPO> list=null;
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



	private void doFindCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	CatPO po=parseRequest(request, CatPO.class);
		try {
			List<CatPO> list=dao.findBytrem(po);
			toPrintJson(response, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doFindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<CatPO> list=dao.findBytrem(null);
			toPrintJson(response, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
