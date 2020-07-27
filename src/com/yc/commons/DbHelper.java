package com.yc.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.yc.commons.MyProperties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DbHelper {
	
	private Connection conn ;
	private PreparedStatement pstmt;
	private ResultSet rs;
	//private static String driverName="oracle.jdbc.driver.OracleDriver";
	//private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	//private String user="scott";
	//private String password="a";
	//加载驱动
	static{
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取数据库连接
	public Connection getConn() throws SQLException{
		conn=DriverManager.getConnection
				(MyProperties.getInstance().getProperty("url"),
						MyProperties.getInstance());
		return conn;
	}
	
	//关闭资源
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(null!=rs){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(null!=pstmt){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		if(null!=conn){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回多条记录查询语句select *from table_name;
	 * @throws IOException 
	 */
	
	public List<Map<String,Object>> selectMutil(String sql,List<Object> params) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamsList(pstmt,params);
			//获取结果
			rs=pstmt.executeQuery();
			//根据结果集获取所有结果集中的所有列名
			List<String> columnNames=getAllColumnNames(rs);
			while(rs.next()){
				map=new HashMap<String,Object>();
				//map.put("",rs.getObject(""));
				
				//循环所有列名
				for(String name:columnNames){
					String typeName=null;//值的类型
					Object obj=null;//获取的值
					obj=rs.getObject(name);
					if(null!=obj){
						typeName=obj.getClass().getName();
					}
					if("oracle.sql.BLOB".equals(typeName)){
						//对图片进行处理
					//	BLOB blob=(BLOB)obj;
					//	InputStream in=blob.getBinaryStream();
					//	byte []bt=new byte[(int)blob.length()];
					//	in.read(bt);
					//	map.put(name,bt);//将blob类型值已字节数组的形式存储
					}else{
						map.put(name,obj);
					}
				}
				list.add(map);
			}
		} finally{
			closeAll(conn,pstmt,rs);
		}
		return list;
	}
	/**
	 * 单记录的查询 select*from table_name where id=?
	 * @param sql 查询的sql语句
	 * @param params 传入的参数 集合的参数必须和？的顺序一致
	 * @return
	 */
	
	public Map<String,Object> selectSingle(String sql,List<Object> params) throws Exception{
		Map<String,Object> map=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamsList(pstmt,params);
			//获取结果集
			rs=pstmt.executeQuery();
			//根据结果对象获取所有结果集中的所有列名
			List<String> columnNames=getAllColumnNames(rs);
			if(rs.next()){
				map=new HashMap<String,Object>();
				//map.put("",rs.getObjecct(""));
				
				//循环所有列名
				for(String name:columnNames){
					String typeName=null;//值得类型
					Object obj=null;//获取的值
					obj=rs.getObject(name);
					if(null!=obj){
						typeName=obj.getClass().getName();
					}
					if("oracle.sql.BLOB".equals(typeName)){
						//对图片进行处理
					//	BLOB blob=(BLOB)obj;
				//		InputStream in=blob.getBinaryStream();
				//		byte []bt=new byte[(int)blob.length()];
				//		in.read(bt);
				//		map.put(name,bt);//将blob类型值已字节数组的形式存储
					}else{
						map.put(name,obj);
					}
				}
				
			}
		} finally{
			closeAll(conn,pstmt,rs);
		}
		return map;
	}
	
	//获取查询后的字段名
	public List<String> getAllColumnNames(ResultSet rs) throws SQLException {
		List<String> list=new ArrayList<String>();
		ResultSetMetaData data=rs.getMetaData();
		int count=data.getColumnCount();
		for(int i=1;i<=count;i++){
			String str=data.getColumnName(i);//获取指定列的名称
			//添加列名到List集合中
			list.add(str);
		}
		
		return list;
	}
	
	//将集合设置到预编译对象中
	public void setParamsList(PreparedStatement pstmt, List<Object> params) throws SQLException {
		if(null==params || params.size()<=0){
			return ;
		}
		for(int i=0;i<params.size();i++){
			pstmt.setObject(i+1,params.get(i));
		}
	}
	
	/**
	 * 批处理操作 多个insert update delete 同一事务
	 * @param sqls    多条sql语句
	 * @param params   多条sql语句的参数
	 * @return
	 * @throws SQLException
	 */
	public int update(List<String> sqls,List<List<Object>> params) throws SQLException{
		int result=0;
		try{
			conn=getConn();
			//设置事务手动提交
			conn.setAutoCommit(false);
			//循环sql语句
			if(null==sqls ||sqls.size()<=0){
				return result;
			}
			for(int i=0;i<sqls.size();i++){
				//获取sql语句并创建编译对象
				pstmt=conn.prepareStatement(sqls.get(i));
				//获取对应的sql语句参数集合
				List<Object> param=params.get(i);
				//设置参数
				setParamsList(pstmt,param);
				//执行更新
				result=pstmt.executeUpdate();
				if(result<=0){
					return result;
				}
			}
			//手动提交
			conn.commit();
		}catch(Exception e){
			//设置回滚
			conn.rollback();
			result=0;
		}finally{
			//还原事务状态
			conn.setAutoCommit(true);
			closeAll(conn,pstmt,rs);
		}
		
		return result;
	}

	/**
	 * 更新操作 增删改
	 * 	sql 更新语句
	 * 	perams 传入的参数  不定长的对象数组 传入的参数顺序与？顺序一致
	 * @throws SQLException 
	 * 
	 */
	
	public int update(String sql,Object...params) throws SQLException{
		int result=0;
		try{
			conn=getConn();//获取连接对象
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamsObject(pstmt,params);
			//执行
			result=pstmt.executeUpdate();
		}finally{
			closeAll(conn,pstmt,null);
		}
		return result;
	}
	
	//不定长参数 设置参数 传入的参数顺序和问号的顺序一致
	public void setParamsObject(PreparedStatement pstmt, Object... params) throws SQLException {
		if(null==params ||params.length<=0){
			return ;
		}
		for(int i=0;i<params.length;i++){
			pstmt.setObject(i+1,params[i]);//将数组中的第i个元素的值设置为第i+1个问号
		}
	}
	
	/**
	 * 聚合函数操作select count(*)from table_name
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	
	public double getPolymer(String sql,List<Object> params) throws Exception{
		double result=0;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParamsList(pstmt,params);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getDouble(1);
			}
		}finally{
			closeAll(conn,pstmt,rs);
		}
		
		return result;
	}
	
	
	
	
	
	
	/**
	 * 放回一行记录 select * from  table_name where id=?
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T findSingle(String sql,List<Object> params,Class<T> cls) throws Exception{
		
		T t=null;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamsList(pstmt,params);
			//获取结果集
			rs=pstmt.executeQuery();
			//通过反射获取实体类中给的所有方法
			Method [] methods=cls.getDeclaredMethods();
			List<String> columnNames=getAllColumnNames(rs);
			Object obj=null;
			if(rs.next()){
				//创建对象，通过反射
				t=cls.newInstance();//默认调用对象的无参数的构造函数
				//循环列
				for(String name: columnNames){
					obj=rs.getObject(name);//获取值
					//循环方法 set+name   setUname
					for(Method m:methods){
						if(("set"+name).equalsIgnoreCase(m.getName())){
							//set方法的形参类型进行判断  set方法的形参的数据类型
							String typeName=m.getParameterTypes()[0].getName();
							if("java.lang.Integer".equals(typeName)){
								m.invoke(t, rs.getInt(name));//传入的参数类型必须和底层的一样
							}else if("java.lang.Double".equals(typeName)){
								m.invoke(t, rs.getDouble(name));//传入的参数类型必须和底层的一样
							}else if("java.lang.Float".equals(typeName)){
								m.invoke(t, rs.getFloat(name));//传入的参数类型必须和底层的一样
							}else if("java.lang.Long".equals(typeName)){
								m.invoke(t, rs.getLong(name));//传入的参数类型必须和底层的一样
							}else {
								m.invoke(t, rs.getString(name));//传入的参数类型必须和底层的一样
							}
						}
					}
				}
			}
		}finally{
			closeAll(conn,pstmt,rs);
		}
		return t;
	}
	
	
	public <T> List<T> findMutil(String sql,List<Object> params ,Class<T> cls) throws Exception{
		List<T> list =new ArrayList<T>();
		T t=null;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamsList(pstmt,params);
			//获取结果集
			rs=pstmt.executeQuery();
			//通过反射获取实体类中给的所有方法
			Method [] methods=cls.getDeclaredMethods();
			List<String> columnNames=getAllColumnNames(rs);
			Object obj=null;
			while(rs.next()){
				//创建对象，通过反射
				t=cls.newInstance();//默认调用对象的无参数的构造函数
				//循环列
				for(String name: columnNames){
					obj=rs.getObject(name);//获取值
					//循环方法 set+name   setUname
					for(Method m:methods){
						if(("set"+name).equalsIgnoreCase(m.getName())){
							//set方法的形参类型进行判断  set方法的形参的数据类型
							String typeName=m.getParameterTypes()[0].getName();
							if("java.lang.Integer".equals(typeName)){
								m.invoke(t, rs.getInt(name));//传入的参数类型必须和底层的一样
							}else if("java.lang.Double".equals(typeName)){
								m.invoke(t, rs.getDouble(name));//传入的参数类型必须和底层的一样
							}else if("java.lang.Float".equals(typeName)){
								m.invoke(t, rs.getFloat(name));//传入的参数类型必须和底层的一样
							}else if("java.lang.Long".equals(typeName)){
								m.invoke(t, rs.getLong(name));//传入的参数类型必须和底层的一样
							}else {
								m.invoke(t, rs.getString(name));//传入的参数类型必须和底层的一样
							}
						}
					}
				}
				//将对象添加到List集合中
				list.add(t);
			}
		}finally{
			closeAll(conn,pstmt,rs);
		}
		return list;
	}
	
	
	
	
	
	
	
	
}	

