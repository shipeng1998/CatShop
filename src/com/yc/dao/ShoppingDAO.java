package com.yc.dao;

import java.util.List;

import com.yc.po.CatPO;
import com.yc.po.ShoppingCartPO;
import com.yc.po.ShoppingPO;


public interface ShoppingDAO {
	/**
	 * 获取总数
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int findByPageTotal() throws Exception;
	
	 /**
     * 分页查询
     * @param po
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<ShoppingPO> findByPage(Integer pageNum) throws Exception;
    
    /**
	 * 添加数据到购物车
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addCart(ShoppingCartPO po) throws Exception;
	

	/**
	 * 查询宠物用品与购物车比较是否大于库存
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingPO> findByTrem(ShoppingCartPO po) throws Exception;
	
	
    /**
	   * 添加宠物用品信息
	   * @param po
	   * @return
	   * @throws Exception
	   */
  public int addShopping(ShoppingPO po) throws Exception;
  
  /**
   * 查看猫用品总数 后台用
   * @return
   * @throws Exception
   */
  public int findByPageTotal(ShoppingPO po) throws Exception;
  
  /**
   * 猫用品分页查看 后台用
   * @param po
   * @param pageNum
   * @param pageSize
   * @return
   * @throws Exception
   */
  public List<ShoppingPO> findByPage(ShoppingPO po, Integer pageNum, Integer pageSize) throws Exception;

}
