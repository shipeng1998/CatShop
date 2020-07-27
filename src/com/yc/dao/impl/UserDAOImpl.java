package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHelper;
import com.yc.dao.UserDAO;
import com.yc.po.UserPO;



public class UserDAOImpl implements UserDAO {
	 DbHelper db=new DbHelper();
	@Override
	public int addUser(UserPO po) throws Exception {
		String sql="insert into user values(null,?,?) ";
		return db.update(sql, po.getUname(),po.getUpwd());
	}

	@Override
	public UserPO login(UserPO po) throws Exception {
		String sql="select uid,uname,upwd from user where uname=? and upwd=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getUname());
		params.add(po.getUpwd());
		return db.findSingle(sql, params, UserPO.class);
	}

	@Override
	public List<UserPO> findByterm(UserPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append(" select uid,uname,upwd from user where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getUid()){
				sb.append(" and uid=? ");
				params.add(po.getUid());
			}
			if(null!=po.getUname()){
				sb.append(" and uname=? ");
				params.add(po.getUname());
			}
			
			
		}
            return db.findMutil(sb.toString(), params, UserPO.class);
	}

}
