package com.yc.dao;

import java.util.List;

import com.yc.po.ShoppingCartPO;



public interface ShoppingCartDAO {
	/**
	 * 添加数据到购物车
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addCart(ShoppingCartPO po) throws Exception;
	
	/**
	 * 查询购物车
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartPO> findByTrem(ShoppingCartPO po) throws Exception;
	
	
	public int deleteAll(ShoppingCartPO po)throws Exception;
	public int deleteSid(ShoppingCartPO po)throws Exception;
	public List<ShoppingCartPO> findByTrem(List<Integer> sids)throws Exception;

}
