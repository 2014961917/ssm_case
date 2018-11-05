package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import com.sun.jdi.PathSearchingVirtualMachine;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class USerServiceImpl implements UserService {
    StringBuilder sb = new StringBuilder();

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*//密码加密
        PasswordEncoder encoder = new BCryptPasswordEncoder();*/

        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userInfo.getId()+userInfo.getPassword());

        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==1?true:false,
                true,true,true,getAuthority(userInfo.getRoles()));

        return user;
    }

    /**
     * 获取用户旗下的角色
     * @param roles
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 查找所有
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll(int page, int size) throws Exception {

        PageHelper.startPage(page,size);

        return userDao.findAllUser();
    }

    /**
     * 保存用户
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void saveUser(UserInfo userInfo) throws Exception {
        //给密码设置密文
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        //调用dao层的方法
        userDao.saveUser(userInfo);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUserById(String id) {
        userDao.delUserById(id);
    }

    public UserInfo findUserById(String id){
        return userDao.findUserById(id);
    }

    /**
     * 更新用户信息
     * @param userInfo
     */
    @Override
    public void updateUser(UserInfo userInfo) {
        //给密码设置密文
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userDao.updateUser(userInfo);
    }

    /**
     * 通过用户查询用户和用户权限
     * @param id
     * @return
     */
    @Override
    public UserInfo findUserByIdAndAllRole(String id) {
        return userDao.findUserByIdAndAllRole(id);
    }

}
