package com.yc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	            
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public void toPrintJson(HttpServletResponse response,Integer i) throws IOException{
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		out.println(gson.toJson(i));
		
	}
	
	
	public void toPrintJson(HttpServletResponse response,String str) throws IOException{
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		out.println(gson.toJson(str));
		
	}
	
	/**
	 * 输出Object 集合
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public void toPrintJson(HttpServletResponse response,Object obj) throws IOException{
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		out.println(gson.toJson(obj));
		
	}
	/**
	 * easyui 中分页的数据格式
	 * @param response
	 * @param obj
	 * @param total
	 * @throws IOException
	 */
	public void toPrintJson(HttpServletResponse response,Object obj ,Integer total) throws IOException{
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"total\":"+total+",");
		sb.append("\"rows\":"+gson.toJson(obj));
		sb.append("}");
		out.println(sb.toString());
		
	}
	
	/**
	 * 解析请求对象  ，将其数据封装到 po对象中
	 * @param request
	 * @param cls
	 * @return
	 */
	public <T> T parseRequest(HttpServletRequest request ,Class<T> cls){
		T t=null;
		//获取cls字段
		Field [] fields=cls.getDeclaredFields();
		Method [] methods=cls.getDeclaredMethods();
		//根据反射创建实例对象
		try {
			t=cls.newInstance();
			//循环该对象的所有字段
			for(Field f:fields){
				String fname=f.getName();
				//根据当前这个名称从请求对象中取值
				String value=request.getParameter(fname);
				if(null==value || "".equals(value)){
					continue;
				}
				//循环方法 找到与之匹配的set方法 进行赋值
				for(Method m:methods){
					if(("set"+fname).equalsIgnoreCase(m.getName())){
						//获取set方法的形参数据类型 set仅有一个形参
						String typeName=m.getParameterTypes()[0].getName();
						if("java.lang.Integer".equals(typeName)){
							m.invoke(t, Integer.parseInt(value));
						}else if("java.lang.Double".equals(typeName)){
							m.invoke(t, Double.parseDouble(value));
						}else if("java.lang.Float".equals(typeName)){
							m.invoke(t, Float.parseFloat(value));
						}else if("java.lang.Long".equals(typeName)){
							m.invoke(t, Long.parseLong(value));
						}else{
							m.invoke(t, value);
						
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return t;
	}
	

}
