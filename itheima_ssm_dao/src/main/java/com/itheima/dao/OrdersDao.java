package com.itheima.dao;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersDao {
    //查询所有订单
    List<Orders> findAll() throws Exception;

    //根据id查询
    Orders findOrdersById(String id) throws Exception;

    //先删除中间表，外键
    void deleteMidTableById(String id);

    //删除主表，根据id删除
    void deleteOrderById(String id) throws Exception;

    void updateOrder(Orders order);
}
