package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(int page,int size) throws Exception;

    //根据id查询订单详情

    Orders findOrdersById(String id) throws Exception;

    void deleteOrderById(String id) throws Exception;

    void updateOrder(Orders order);
}
