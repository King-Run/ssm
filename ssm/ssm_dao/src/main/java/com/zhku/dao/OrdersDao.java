package com.zhku.dao;

import com.zhku.pojo.Member;
import com.zhku.pojo.Orders;
import com.zhku.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.zhku.dao.ProductDao.findById"))    })
    List<Orders> findAll();

    @Select(("select * from orders where id = #{id}"))
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.zhku.dao.ProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select = "com.zhku.dao.MemberDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.zhku.dao.TravellerDao.findByOrdersId")) })
    Orders findById(int id);
}
