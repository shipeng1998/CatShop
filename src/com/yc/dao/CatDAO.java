package com.yc.dao;

import java.util.List;

import com.yc.po.CatPO;


public interface CatDAO {

	  /**
	   * 查看猫信息
	   * @param po
	   * @return
	   * @throws Exception
	   */
    public List<CatPO> findBytrem(CatPO po) throws Exception;
    
    /**
	   * 添加猫信息
	   * @param po
	   * @return
	   * @throws Exception
	   */
  public int addCat(CatPO po) throws Exception;
  /**
   * 查看猫总数 后台用
   * @return
   * @throws Exception
   */
  public int findByPageTotal(CatPO po) throws Exception;
  
  /**
   * 猫分页查看 后台用
   * @param po
   * @param pageNum
   * @param pageSize
   * @return
   * @throws Exception
   */
  public List<CatPO> findByPage(CatPO po, Integer pageNum, Integer pageSize) throws Exception;
    

}
