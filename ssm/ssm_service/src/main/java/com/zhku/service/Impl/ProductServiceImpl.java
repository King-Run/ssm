package com.zhku.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhku.dao.ProductDao;
import com.zhku.pojo.Product;
import com.zhku.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> FindAll(int page, int size) throws Exception {
        System.out.println("业务层方法：查询所有商品");

        //        pageNum:是从第几页开始
//        pageSize：是每页显示多少条
//        分页的代码必须放在方法的上面
        PageHelper.startPage(page, size);
        return productDao.FindAll();
    }

    @Override
    public void saveProduct(Product product) {
        System.out.println("业务层方法：新增产品信息");
        productDao.saveProduct(product);
    }
}
