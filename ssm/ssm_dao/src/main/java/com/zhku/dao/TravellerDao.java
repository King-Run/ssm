package com.zhku.dao;

import com.zhku.pojo.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findByOrdersId(int id);
}
