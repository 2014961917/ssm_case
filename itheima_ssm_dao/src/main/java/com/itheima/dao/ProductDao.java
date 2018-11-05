package com.itheima.dao;

import com.itheima.domain.Product;

import java.util.List;

public interface ProductDao {
    //为分页查询所有产品
    List<Product> findAll() throws Exception;

    //添加产品
    void saveProduct(Product product) throws Exception;

    //根据id删除
    void deleteById(String id) throws Exception;

    //根据id查询
    Product findProductById(String id) throws Exception;

    //修改状态为开启
    void updateStatus(Product product) throws Exception;

    //修改状态为关闭
    void updateStatusToClose(Product product) throws Exception;

    //修改产品信息
    void updateProduct(Product product) throws Exception;

    List<Product> findProductByName(String name);
}
