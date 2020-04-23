package com.zhku.controller;

import com.github.pagehelper.PageInfo;
import com.zhku.pojo.Product;
import com.zhku.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
     private ProductService productService;

    /**
     * 查询所有产品
     * @param model
     * @return
     * @throws Exception
     */

    /*
    没有分页的方法
    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception {
        System.out.println("整合springmvc成功。。");
        List<Product> products = productService.FindAll();
        for (Product product:products
        ) {
            System.out.println(product);

        }
        model.addAttribute("productList",products);
        return "product-list";
    }
     */
    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "4") int size) throws Exception {
        System.out.println("控制层：分页查询所有的产品");
        List<Product> products = productService.FindAll(page,size);
        PageInfo pageInfo = new PageInfo(products);
        model.addAttribute("pageInfo", pageInfo);
        return "product-list";
    }


    @RequestMapping("/saveProduct")
    public String saveProduct(Product product) throws Exception{
        System.out.println("控制层：新增产品信息");
        System.out.println(product);
        productService.saveProduct(product);
        return "redirect:findAll";
    }
}
