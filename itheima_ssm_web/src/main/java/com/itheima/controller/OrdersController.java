package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                          @RequestParam(name = "size",required = true,defaultValue = "4")Integer size,
                          Model model) throws Exception {
        //调用service层的查询所有方法
        List<Orders> ordersList = ordersService.findAll(page,size);

        PageInfo pageInfo = new PageInfo(ordersList);

        model.addAttribute("pageInfo",pageInfo);

        return "orders-list";
    }

    @RequestMapping("/findOrdersById")
    public String findById(@RequestParam(name = "id",required = true)String id, Model model) throws Exception {
        //调用service层的方法
        Orders orders =  ordersService.findOrdersById(id);
        //System.out.println(orders);
        //将数据存入model中，传到页面
        model.addAttribute("orders",orders);
        return "orders-show";
    }

    @RequestMapping("/changeOrderById")
    public String changeOrderById(String id,Model model) throws Exception {
        //调用service层的方法
        Orders orders =  ordersService.findOrdersById(id);
        //将数据存入model中，传到页面
        model.addAttribute("order",orders);
        return "orders-update";
    }

    @RequestMapping("/deleteOrdersByIds")
    public String deleteOrdersByIds(String ids) throws Exception{
        System.out.println(ids);
        String[] idList = ids.split(",");
        for (String id : idList) {

            ordersService.deleteOrderById(id);
        }
        System.out.println("删除完成");
        return "redirect:/orders/findAll";
    }

    @RequestMapping("/updateOrdersById")
    public String updateOrdersById(Orders order){
        System.out.println(order);
        if (order.getPayType()==null || (order.getOrderStatus()!=1&&order.getOrderStatus()!=0)){
           return "redirect:/orders/findAll";
        }
        ordersService.updateOrder(order);
        System.out.println("saaaaaaaa");
            return "redirect:/orders/findAll";
    }
}
