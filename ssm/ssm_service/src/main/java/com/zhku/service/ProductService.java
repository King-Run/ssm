package com.zhku.service;

import com.zhku.pojo.Product;

import java.util.List;

public interface ProductService {

    /**
     * 业务层：查询所有
     * @return
     * @throws Exception
     * @param page
     * @param size
     */
    public List<Product> FindAll(int page, int size) throws Exception;

    /**
     * 业务层，新增产品
     * @param product
     */
    public void saveProduct(Product product);
}
