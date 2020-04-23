package com.zhku.controller;


import com.github.pagehelper.PageInfo;
import com.zhku.pojo.Orders;
import com.zhku.service.OrdersService;
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

//    查询所有订单 ---未分页
//    @RequestMapping("/findAll")
//    public String findALL(Model model){
//        System.out.println("控制层：查询所有订单");
//        List<Orders> orders = ordersService.findAll();
//        model.addAttribute("ordersList",orders);
//        return "orders-list";
//    }
    @RequestMapping("/findAll")
    public  String findAll(Model model, @RequestParam(name = "page",required = true,defaultValue = "1") int page, @RequestParam(name = "size",required = true,defaultValue = "4") int size){
        System.out.println("控制层：分页查询所有订单");
        List<Orders> orders = ordersService.findAll(page,size);
//        PageInfo其实就是一个分页Bean
        PageInfo pageInfo = new PageInfo(orders);
        model.addAttribute("pageInfo",pageInfo);
        return "orders-page-list";
    }


    @RequestMapping("/findById")
    public String findById(@RequestParam(name="id",required = true) int id,Model model){
        System.out.println("控制层：查看订单详情");
        Orders orders = ordersService.findById(id);
        System.out.println(orders);
        model.addAttribute("orders",orders);
        return "orders-show";
    }
}
