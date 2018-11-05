package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 未分页查询所有
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception {
        List<Product> products = productService.findAll();
        model.addAttribute("productList",products);
        return "product-list";
    }

    /**
     * 插入新数据
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveProduct")
    public String saveProduct(Product product) throws Exception {
        System.out.println(product);
        //添加商品
        productService.saveProduct(product);

        return "redirect:/product/findAll";
    }

    /**
     * 删除选中
     * @param idArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteSelected")
    public String deleteSelected(String idArr) throws Exception{
        //删除选中
        System.out.println(idArr);
        productService.deleteSelectedByIds(idArr);

        return "redirect:/product/findAll";
    }

    /**
     * 开启状态
     * @param idArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/openStatusByIds")
    public String openStatusByIds(String idArr) throws Exception{
        String[] ids = idArr.split(",");
        for (String id : ids) {
            productService.updateStatusById(id);
        }
       // productService.updateStatusByIds(idArr);
        //跳转到查询所有
        return "redirect:/product/findAll";
    }


    /**
     * 关闭
     * @param idArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/closeStatusByIds")
    public String closeStatusByIds(String idArr) throws Exception{
        String[] ids = idArr.split(",");
        for (String id : ids) {
            productService.closseStatusById(id);
        }
        //跳转到查询所有
        return "redirect:/product/findAll";
    }

    @RequestMapping("/updateProductById")
    public String updateProductById(String id,Model model) throws Exception {
        //System.out.println(id);
        Product product = productService.findProductById(id);
        System.out.println(product);
        model.addAttribute("product",product);
        //将product存入域中，发送到修改页面
        return "product-update";
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeProduct")
    public String changeProduct(Product product) throws Exception {
        System.out.println(product);
        productService.updateProduct(product);

        return "redirect:/product/findAll";
    }

    @RequestMapping("/findProductByName")
    public String findProductByName(String name,Model model) throws Exception {
        List<Product> list = productService.findProductByName(name);
        model.addAttribute("productList",list);
        return "product-list";
    }
}
