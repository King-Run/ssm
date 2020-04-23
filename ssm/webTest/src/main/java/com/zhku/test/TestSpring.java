package com.zhku.test;


import com.zhku.pojo.Product;
import com.zhku.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestSpring {
    @Test
    public void testSpring() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ProductService productService = ac.getBean("productService", ProductService.class);
//        List<Product> products = productService.FindAll(page, size);
//        for (Product product:products
//             ) {
//            System.out.println(product);
//
//        }
            }
}
