package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHelper;
import com.yc.dao.ShoppingCartDAO;
import com.yc.po.CatPO;
import com.yc.po.ShoppingCartPO;



public class ShoppingCartDAOImpl implements ShoppingCartDAO {
	DbHelper db=new DbHelper();
	@Override
	public int addCart(ShoppingCartPO po) throws Exception {
		//1.根据fid uid查看该商品是否已经存在 如果存在num+1即可
				//2如果没有数据就插入一条数据
				List<ShoppingCartPO> list=findByTrem(po);
				String sql01="insert into shoppingcart values(null,?,?,1,?,?,null,?)";
				
				if(null==list || list.isEmpty()){
					return db.update(sql01, po.getSname(),po.getSprice(),po.getUid(),po.getSphoto(),po.getCid());
				}
				return 3;
	}
	@Override
	public List<ShoppingCartPO> findByTrem(ShoppingCartPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select sid,sname,sprice,snum,uid,sphoto,spid,cid from shoppingcart where 1=1 ");
				
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<>();
			if(null!=po.getCid()){
				sb.append(" and cid=? ");
				params.add(po.getCid());
			}
			if(null!=po.getUid()){
				sb.append(" and uid=? ");
				params.add(po.getUid());
			}
			if(null!=po.getSpid()){
				sb.append(" and spid=? ");
				params.add(po.getSpid());
			}
			
		}
		sb.append(" order by sid desc ");
		return db.findMutil(sb.toString(), params, ShoppingCartPO.class);
	}
	@Override
	public int deleteAll(ShoppingCartPO po) throws Exception {
		String sql =" delete from shoppingcart where uid = ?";
		return db.update(sql, po.getUid());
	}
	@Override
	public int deleteSid(ShoppingCartPO po) throws Exception {
		String sql =" delete from shoppingcart where sid = ?";
		return db.update(sql, po.getSid());
	}
	
	@Override
	public List<ShoppingCartPO> findByTrem(List<Integer> sids) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sid,sname,sprice,snum,uid,sphoto,spid,cid "
				+ " from shoppingcart where 1=1 ");
		List<Object> params =null;
		if(null!=sids && sids.size()>0){
			params = new ArrayList<>();
			sb.append(" and sid in (");
			for(int i =0;i<sids.size();i++){
				if(i==sids.size()-1){
					sb.append(" ?");
				}else{
					sb.append(" ?,");
				}
				params.add(sids.get(i));
			}
			sb.append(")");
		}
		
		return db.findMutil(sb.toString(), params, ShoppingCartPO.class);
	}

}
