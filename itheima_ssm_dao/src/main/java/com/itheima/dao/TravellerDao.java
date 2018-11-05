package com.itheima.dao;

import com.itheima.domain.Traveller;

import java.util.List;

public interface TravellerDao {
    //根据订单id查询游客
    List<Traveller> findTravellerByOrderId(String orderId);
}
