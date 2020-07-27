package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHelper;
import com.yc.dao.AdminDAO;
import com.yc.po.AdminPO;

public class AdminDAOImpl implements AdminDAO {
   DbHelper db=new DbHelper();
	public int addAdmin(AdminPO po) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AdminPO> findByTrem(AdminPO admin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminPO login(AdminPO admin) throws Exception {
		String sql="select aid,aname,apwd from admin where aname=? and apwd=? ";
	    List<Object> params=new ArrayList<Object>();
	    params.add(admin.getAname());
	    params.add(admin.getApwd());
	    return db.findSingle(sql, params, AdminPO.class);
	}

}
