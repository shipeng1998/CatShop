package com.yc.dao.impl;



import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHelper;
import com.yc.dao.ShoppingCartDAO;
import com.yc.dao.ShoppingDAO;
import com.yc.po.CatPO;
import com.yc.po.ShoppingCartPO;
import com.yc.po.ShoppingPO;


public class ShoppingDAOImpl implements ShoppingDAO {
	 DbHelper db=new DbHelper();
	 ShoppingCartDAO shoppingCartDAO=new ShoppingCartDAOImpl();
	@Override
	public int findByPageTotal() throws Exception {
		String sql="select count(*) from shopping";
		
		return (int)db.getPolymer(sql, null);
	
	}
	@Override
	public List<ShoppingPO> findByPage( Integer pageNum) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from shopping where 1=1 ");
	
		
		sb.append(" order by spid desc");
		//添加分页的参数
		if(null!=pageNum ){
			sb.append(" limit "+(pageNum-1)*4+","+4);
		}
		return db.findMutil(sb.toString(), null, ShoppingPO.class);
	}
	@Override
	public int addCart(ShoppingCartPO po) throws Exception {
		//1.根据fid uid查看该商品是否已经存在 如果存在num+1即可
		//2如果没有数据就插入一条数据
		List<ShoppingCartPO> list=shoppingCartDAO.findByTrem(po);
		List<ShoppingPO> list01=findByTrem(po);
		String sql01="insert into shoppingcart values(null,?,?,1,?,?,?,null)";
		String sql02="update shoppingcart set snum=snum+1 where uid=? and spid=?";
		if(null==list || list.isEmpty()){
			return db.update(sql01, po.getSname(),po.getSprice(),po.getUid(),po.getSphoto(),po.getSpid());
		}
		if(list.get(0).getSnum()<list01.get(0).getSpnum()){
			return db.update(sql02, po.getUid(),po.getSpid());
		}
		return 3;
	}
	@Override
	public List<ShoppingPO> findByTrem(ShoppingCartPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from shopping where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<>();
			if(null!=po.getSpid()){
				sb.append(" and spid=? ");
				params.add(po.getSpid());
			}
			
		}
		
		sb.append(" order by spid desc");
		
		return db.findMutil(sb.toString(), params, ShoppingPO.class);
	}
	@Override
	public int addShopping(ShoppingPO po) throws Exception {
		String sql="insert into shopping values(null,?,?,?,?,?)";
		return db.update(sql, po.getSpname(),po.getSpprice(),po.getSpnum(),po.getSpphoto(),po.getRealprice());
	}
	@Override
	public int findByPageTotal(ShoppingPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from shopping where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getSpid()){
				params.add(po.getSpid());
				sb.append(" and spid=? ");
			}
			if(null!=po.getSpname()){
				params.add(po.getSpname());
				sb.append(" and spname=? ");
			}
		}
		sb.append(" order by spid desc");
		return (int)db.getPolymer(sb.toString(), params);
	}
	@Override
	public List<ShoppingPO> findByPage(ShoppingPO po, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from shopping where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getSpid()){
				params.add(po.getSpid());
				sb.append(" and spid=? ");
			}
			if(null!=po.getSpname()){
				params.add(po.getSpname());
				sb.append(" and spname=? ");
			}
		}
		sb.append(" order by spid desc");
		//添加分页的参数
		if(null!=pageNum && null!=pageSize){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, ShoppingPO.class);
	}

	
}
