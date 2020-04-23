package com.zhku.service;

import com.zhku.pojo.Orders;

import java.util.List;

public interface OrdersService {

    /**
     * 查询所有订单
     * @return
     */
    public List<Orders> findAll(int page, int size);

    public Orders findById(int id);
}
