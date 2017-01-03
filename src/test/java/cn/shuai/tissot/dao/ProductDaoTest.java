package cn.shuai.tissot.dao;

import cn.shuai.tissot.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shuai on 2016/12/31.
 */
//配置spring和junit整合，让junit启东市加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ProductDaoTest {
    //注入dao实现类依赖
    @Resource
    private ProductDao productDao;

    @Test
    public void reduceNumber() throws Exception {
        Date date = new Date();
        int result = productDao.reduceNumber(1000L,date);
        System.out.println(result);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Product product = productDao.queryById(id);
        System.out.println(product.getName());
        System.out.print(product);
    }

    @Test
    public void queryAll() throws Exception {
        List<Product> products = productDao.queryAll(0,100);
        System.out.println(products.size());
        for (Product product:products) {
            System.out.println(product);
        }
    }

}