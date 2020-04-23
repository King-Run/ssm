package com.zhku.dao;

import com.zhku.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    /**
     * 根据Id查询商品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    public Product findById(Integer id);

    /**
     * 查询所有商品信息
     * @return
     */
    @Select("select * from product")
    public List<Product> FindAll() throws Exception;

    /**
     * 新增产品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void saveProduct(Product product);
}
