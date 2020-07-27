package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHelper;
import com.yc.dao.ShoppingCartDAO;
import com.yc.dao.OrderDAO;
import com.yc.po.ShoppingCartPO;
import com.yc.po.OrderPO;
import com.yc.util.StringUtil;

public class OrderDAOImpl implements OrderDAO{

	ShoppingCartDAO cartDAO = new ShoppingCartDAOImpl();
	DbHelper db = new DbHelper();
	
	@Override
	public int genOrder(String sids, Integer uid, OrderPO po) throws Exception {
		//字符串分割
		String [] infos =sids.split(",");
		List<Integer> sidsList = new ArrayList<Integer>();
		for(String sid:infos){
			if(StringUtil.isNotNull(sid)){
				sidsList.add(Integer.parseInt(sid));
			}
		}
		List<ShoppingCartPO> list =cartDAO.findByTrem(sidsList);
		double totalPrice =0;
		//计算总价格
		for(ShoppingCartPO cart:list){
			totalPrice+=cart.getSnum()*cart.getSprice();
		}
		//生成订单编号
		String oid =StringUtil.genOrderId();
		String sql01="insert into catorder (oid,uid,address,tel,ordertime,status,uname,totalprice) values(?,?,?,?,?,1,?,?)";
		String sql02="insert into catorderitem values(null,?,?,?,?,?)";
		String sql03="delete from shoppingcart where sid = ? ";
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();
		List<Object> param01 = new ArrayList<Object>();
		List<Object> param02 = null;
		List<Object> param03 = null;
		//生成订单的sql
		po.setOid(oid);
		po.setUid(uid);
		param01.add(oid);
		param01.add(uid);
		param01.add(po.getAddress());
		param01.add(po.getTel());
		param01.add(po.getOrdertime());
		param01.add(po.getUname());
		param01.add(totalPrice);
		System.out.println(po.getOrdertime());
		//添加
		sqls.add(sql01);
		params.add(param01);
		for(ShoppingCartPO cart:list){
			param02 = new ArrayList<Object>();
			param03 = new ArrayList<Object>();
			//订单详情表
			param02.add(oid);
			param02.add(cart.getCid());
			param02.add(cart.getSpid());
			param02.add(cart.getSprice());
			param02.add(cart.getSnum());
			//添加
			params.add(param02);
			sqls.add(sql02);
			
			//删除购物车表
			param03.add(cart.getSid());
			
			//添加
			params.add(param03);
			sqls.add(sql03);
		}
		return db.update(sqls, params);
	}

	@Override
	public List<OrderPO> findByTrem(OrderPO po) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select oid,uid,address,tel,ordertime,status,uname,totalprice from catorder where 1=1");
		List<Object>params =null;
		if(null!=po){
			params = new ArrayList<>();
			if(null!=po.getOid()){
				sb.append(" and oid =?");
				params.add(po.getOid());
			}
			if(null!=po.getUid()){
				sb.append(" and uid =?");
				params.add(po.getUid());
			}
		}
		sb.append(" order by oid desc");
		return db.findMutil(sb.toString(), params, OrderPO.class);
	}
	

}
