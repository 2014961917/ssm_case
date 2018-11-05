package com.itheima.service;

import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll(int page, int size) throws Exception;

    void saveUser(UserInfo userInfo) throws Exception;

    void deleteUserById(String id);

    UserInfo findUserById(String id);

    void updateUser(UserInfo userInfo);

    UserInfo findUserByIdAndAllRole(String id);
}
