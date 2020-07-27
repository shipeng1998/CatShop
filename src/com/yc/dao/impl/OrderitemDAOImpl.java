package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DbHelper;
import com.yc.dao.OrderitemDAO;
import com.yc.po.OrderPO;
import com.yc.po.OrderitemPO;
import com.yc.po.ShoppingPO;

public class OrderitemDAOImpl implements OrderitemDAO {
	DbHelper db = new DbHelper();
	@Override
	public List<OrderitemPO> findByTrem(String oid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select iid,oid,cid,spid,sprice,snum from catorderitem where 1=1");
		List<Object>params =null;
		
			
			if(null!=oid){
				params = new ArrayList<>();
				sb.append(" and oid =?");
				params.add(oid);
			}
		sb.append(" order by iid desc");
		return db.findMutil(sb.toString(), params, OrderitemPO.class);
	}
	@Override
	public int deleteCid(OrderitemPO po) throws Exception {
		String sql =" delete from cat where cid = ?";
		return db.update(sql, po.getCid());
	}
	@Override
	public int deleteSpid(OrderitemPO po) throws Exception {
		Map<String,Object> map=findSpid(po.getSpid());
		String sql =" update shopping set spnum=spnum-1 where spid=? ";
		String sql01 =" delete from shopping where spid = ? ";
		if(map!=null ){
			if(Integer.valueOf(""+map.get("spid"))>1){
				
				return db.update(sql, po.getSpid());
			}
		}
		return db.update(sql01, po.getSpid());
	}
	@Override
	public Map<String,Object> findSpid(Integer spid) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from shopping where 1=1 ");
		List<Object> params=null;
			if(null!=spid){
				params=new ArrayList<>();
				sb.append(" and spid=? ");
				params.add(spid);
			}
		sb.append(" order by spid desc");
		
		return db.selectSingle(sb.toString(), params);
	}

}
