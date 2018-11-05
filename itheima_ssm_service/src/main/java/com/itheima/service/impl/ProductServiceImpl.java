package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//产品服务层实现类
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 未分页查询所有产品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    /**
     * 保存产品
     * @param product
     * @throws Exception
     */
    @Override
    public void saveProduct(Product product) throws Exception {
        //获取状态码
        String productStatusStr = product.getProductStatusStr();

        if (productStatusStr.equals("开启")){
            product.setProductStatus(1);
        }else{
            product.setProductStatus(0);
        }
        product.setId(UUID.randomUUID().toString().replaceAll("-",""));
        System.out.println(product);
        productDao.saveProduct(product);
    }

    @Override
    public void deleteSelectedByIds(String idArr) throws Exception {
        String[] ids = idArr.split(",");
        for (String id : ids) {
            productDao.deleteById(id);
        }
    }

    @Override
    public void updateStatusById(String id) throws Exception {

            Product product = productDao.findProductById(id);
            System.out.println(product);

            //获取状态码
            Integer status = product.getProductStatus();
            //判断状态是否为0
            if (status == 0){
                //为0的话将状态码修改为1
                product.setProductStatus(1);

                /*product.setProductStatusStr("开启");
                System.out.println("open"+product);*/
                productDao.updateStatus(product);

        }

    }

    @Override
    public void closseStatusById(String id) throws Exception {

            Product product = productDao.findProductById(id);
            System.out.println(product);

            //获取状态码
            Integer status = product.getProductStatus();
            //判断状态是否为0
            if (status == 1){
                //为1的话将状态码修改为0
                product.setProductStatus(0);

                /*product.setProductStatusStr("开启");
                System.out.println("open"+product);*/

                productDao.updateStatusToClose(product);
            }
        }



    @Override
    public Product findProductById(String id) throws Exception {
        Product product = productDao.findProductById(id);
        return product;
    }

    @Override
    public void updateProduct(Product product) throws Exception {

        String productStatusStr = product.getProductStatusStr();

        if (productStatusStr.equals("开启")){
            product.setProductStatus(1);
        }else{
            product.setProductStatus(0);
        }

        productDao.updateProduct(product);
    }

    @Override
    public List<Product> findProductByName(String name) throws Exception {
        return productDao.findProductByName(name);

    }
}
