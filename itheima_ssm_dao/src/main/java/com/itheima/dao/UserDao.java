package com.itheima.dao;

import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {
    //根据用户名查找用户
   UserInfo findByUsername(String username) throws Exception;

   //查找所有
    List<UserInfo> findAllUser() throws Exception;

    //添加用户
    void saveUser(UserInfo userInfo) throws Exception;

    void delUserById(String id);

    UserInfo findUserById(String id);

    void updateUser(UserInfo userInfo);

    UserInfo findUserByIdAndAllRole(String id);
}
