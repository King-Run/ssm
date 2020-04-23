package com.zhku.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhku.dao.OrdersDao;
import com.zhku.pojo.Orders;
import com.zhku.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) {

//        pageNum:是从第几页开始
//        pageSize：是每页显示多少条
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(int id) {
        return ordersDao.findById(id);
    }
}
