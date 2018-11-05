package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;
//产品服务层接口
public interface ProductService {
    //未分页查询所有产品
    List<Product> findAll() throws Exception;
    //添加产品
    void saveProduct(Product product) throws Exception;

    //删除选中
    void deleteSelectedByIds(String idArr) throws Exception;

    //修改状态为开启
    void updateStatusById(String id) throws Exception;

    //修改状态为关闭
    void closseStatusById(String id) throws Exception;

    //根据id查找产品
    Product findProductById(String id) throws Exception;

    //修改产品信息
    void updateProduct(Product product) throws Exception;

    //模糊查询
    List<Product> findProductByName(String name) throws Exception;
}
