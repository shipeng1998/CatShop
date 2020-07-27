package com.yc.dao;

import java.util.List;

import com.yc.po.AdminPO;

public interface AdminDAO {
     public int addAdmin(AdminPO po) throws Exception;
     
     
     public List<AdminPO> findByTrem(AdminPO admin) throws Exception;
     
     /**
      * 管理员登录
      * @param admin
      * @return
      * @throws Exception
      */
     public AdminPO login(AdminPO admin) throws Exception;
}
