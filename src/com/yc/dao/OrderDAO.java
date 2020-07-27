package com.yc.dao;

import java.util.List;


import com.yc.po.OrderPO;

public interface OrderDAO {

	/**
	 * 生成订单
	 * @param cids
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public int genOrder(String sids,Integer uid,OrderPO po)throws Exception;
	
	
	
	/**
	 * 查看订单
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public List<OrderPO> findByTrem(OrderPO po)throws Exception;
}
