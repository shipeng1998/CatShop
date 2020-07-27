package com.yc.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yc.dao.ShoppingDAO;
import com.yc.dao.impl.ShoppingDAOImpl;
import com.yc.po.ShoppingPO;
import com.yc.util.StringUtil;
@WebServlet("/ShoppingFile.action")
public class FileUploadServlet01 extends BaseServlet {
      ShoppingDAO dao=new ShoppingDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			doShoppingFileUpload(request,response);
		
		
	}

	private void doShoppingFileUpload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String spname=null;
		String spprice=null;
		String spnum=null;
		String realprice=null;
		String spphoto=null;
		
		
		try {
			//创建磁盘文件项工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//获取文件上传处理器
			ServletFileUpload upload=new ServletFileUpload(factory);
			//解析请求对象
			List<FileItem> list=upload.parseRequest(request);
			if(null!=list){
				for(FileItem item:list){
					if(item.isFormField()){
						String name=item.getFieldName();
						//普通表单元素
						if("spname".equals(name)){
							spname=item.getString("UTF-8");
						}else if("spprice".equals(name)){
							spprice=item.getString("UTF-8");
						}else if("realprice".equals(name)){
							realprice=item.getString("UTF-8");
						}else if("spnum".equals(name)){
							spnum=item.getString("UTF-8");
						}
					}else{
						//文件
						System.out.println(item.getName());
						System.out.println("文件类型："+item.getContentType());
						System.out.println("文件大小："+item.getSize());
						//图片永久存在服务器中 独立文件项目存放图片
						String path=this.getServletContext().getRealPath("/")+"../shoppingPhoto/";
						//文件名称冲突
						String fileName=System.currentTimeMillis()+item.getName();
						File file=new File(path,fileName);
						//将图片数据写入到file文件
						item.write(file);
						spphoto="../shoppingPhoto/"+file.getName();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将所有数据封装到po对象中
		ShoppingPO po=new ShoppingPO();
		po.setSpname(spname);
		po.setSpphoto(spphoto);
		
		
		if(StringUtil.isNotNull(spprice)){
			po.setSpprice(Double.parseDouble(spprice));
		}
		if(StringUtil.isNotNull(realprice)){
			po.setRealprice(Double.parseDouble(realprice));
		}
		if(StringUtil.isNotNull(spnum)){
			po.setSpnum(Integer.parseInt(spnum));
		}
		
		//调用dao 方法
		try {
			int i=dao.addShopping(po);
			toPrintJson(response, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

