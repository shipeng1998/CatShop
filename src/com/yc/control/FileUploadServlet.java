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

import com.yc.dao.CatDAO;
import com.yc.dao.impl.CatDAOImpl;
import com.yc.po.CatPO;
import com.yc.util.StringUtil;
@WebServlet("/CatFile.action")
public class FileUploadServlet extends BaseServlet {
      CatDAO dao=new CatDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			doCatFileUpload(request,response);
		
		
	}

	private void doCatFileUpload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String cname=null;
		String ctype=null;
		String cprice=null;
		String realprice=null;
		String csex=null;
		String cappearance=null;
		String cage=null;
		String cphoto=null;
		
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
						if("cname".equals(name)){
							cname=item.getString("UTF-8");
						}else if("ctype".equals(name)){
							ctype=item.getString("UTF-8");
						}else if("realprice".equals(name)){
							realprice=item.getString("UTF-8");
						}else if("cprice".equals(name)){
							cprice=item.getString("UTF-8");
						}else if("csex".equals(name)){
							csex=item.getString("UTF-8");
						}else if("cappearance".equals(name)){
							cappearance=item.getString("UTF-8");
						}else if("cage".equals(name)){
							cage=item.getString("UTF-8");
						}
					}else{
						//文件
						System.out.println(item.getName());
						System.out.println("文件类型："+item.getContentType());
						System.out.println("文件大小："+item.getSize());
						//图片永久存在服务器中 独立文件项目存放图片
						String path=this.getServletContext().getRealPath("/")+"../photo/";
						//文件名称冲突
						String fileName=System.currentTimeMillis()+item.getName();
						File file=new File(path,fileName);
						//将图片数据写入到file文件
						item.write(file);
						cphoto="../photo/"+file.getName();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将所有数据封装到po对象中
		CatPO po=new CatPO();
		po.setCphoto(cphoto);
		po.setCname(cname);
		po.setCtype(ctype);
		po.setCsex(csex);
		po.setCappearance(cappearance);
		
		if(StringUtil.isNotNull(cprice)){
			po.setCprice(Double.parseDouble(cprice));
		}
		if(StringUtil.isNotNull(realprice)){
			po.setRealprice(Double.parseDouble(realprice));
		}
		if(StringUtil.isNotNull(cage)){
			po.setCage(Integer.parseInt(cage));
		}
		
		//调用dao 方法
		try {
			int i=dao.addCat(po);
			toPrintJson(response, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
