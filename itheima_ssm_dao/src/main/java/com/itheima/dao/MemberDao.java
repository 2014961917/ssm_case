package com.itheima.dao;

import com.itheima.domain.Member;

public interface MemberDao {
    //根据id查询
    Member findMemberById(String id) throws Exception;
}
