package com.yc.dao;

import java.util.List;
import java.util.Map;

import com.yc.po.OrderitemPO;


public interface OrderitemDAO {
	
	/**
	 * 查看订单详情表
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public List<OrderitemPO> findByTrem(String oid)throws Exception;
	
	/**
	 * 根据cid删除猫
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int deleteCid(OrderitemPO po)throws Exception;
	
	/**
	 * 根据spid减少猫用品库存
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int deleteSpid(OrderitemPO po)throws Exception;
	
	
	public Map<String,Object> findSpid(Integer spid)throws Exception;

}
