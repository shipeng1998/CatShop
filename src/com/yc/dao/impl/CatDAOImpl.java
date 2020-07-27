package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHelper;
import com.yc.dao.CatDAO;
import com.yc.po.CatPO;



public class CatDAOImpl implements CatDAO {
    DbHelper db=new DbHelper();
	@Override
	public List<CatPO> findBytrem(CatPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select cid,cname,ctype,cprice,csex,cappearance,cage,cphoto,realprice from cat where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			if(null!=po.getCtype()){
				
				sb.append(" and ctype like '%"+po.getCtype()+"%' ");
			}
			
		}
		sb.append(" order by cid desc");
		return db.findMutil(sb.toString(), params, CatPO.class);
	}
	@Override
	public int addCat(CatPO po) throws Exception {
		String sql="insert into cat values(null,?,?,?,?,?,?,?,?)";
		return db.update(sql, po.getCname(),po.getCtype(),po.getCprice(),po.getCsex(),po.getCappearance(),po.getCage(),po.getCphoto(),po.getRealprice());
	}
	@Override
	public int findByPageTotal(CatPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from cat where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getCid()){
				params.add(po.getCid());
				sb.append(" and cid=? ");
			}
			if(null!=po.getCtype()){
				params.add(po.getCtype());
				sb.append(" and ctype=? ");
			}
		}
		sb.append(" order by cid desc");
		return (int)db.getPolymer(sb.toString(), params);
	}
	@Override
	public List<CatPO> findByPage(CatPO po, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from cat where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getCid()){
				params.add(po.getCid());
				sb.append(" and cid=? ");
			}
			if(null!=po.getCtype()){
				params.add(po.getCtype());
				sb.append(" and ctype=? ");
			}
		}
		sb.append(" order by cid desc");
		//添加分页的参数
		if(null!=pageNum && null!=pageSize){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, CatPO.class);
	}

}
