package com.yc.dao;

import java.util.List;

import com.yc.po.UserPO;

public interface UserDAO {
	/**
	 * 用户注册
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addUser(UserPO po) throws Exception;
	/**
	 * 用户登录
	 * @param po
	 * @return
	 * @throws Exception
	 */
   public UserPO login(UserPO po) throws Exception;
   /**
    * 查看用户信息 根据编号 名称 邮箱
    * @param po
    * @return
    * @throws Exception
    */
   public List<UserPO> findByterm(UserPO po) throws Exception;

}
