package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //使用分页插件
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findOrdersById(String id) throws Exception {
        //调用dao层的方法查询详情
        return ordersDao.findOrdersById(id);

    }

    @Override
    public void deleteOrderById(String id) throws Exception {
        //先删除中间表的外键，在删除主表的
        ordersDao.deleteMidTableById(id);
        ordersDao.deleteOrderById(id);
    }

    @Override
    public void updateOrder(Orders order) {
        ordersDao.updateOrder(order);
    }
}
