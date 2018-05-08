package cn.shuai.tissot.service;

import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.dto.ProductExecution;
import cn.shuai.tissot.entity.Product;
import cn.shuai.tissot.exception.ProductCloseExcption;
import cn.shuai.tissot.exception.RepeatKillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by shuai on 2017/1/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-dao.xml",
        "classpath:spring-service.xml"})
public class ProductServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Test
    public void getProductList() throws Exception {
        List<Product> list = productService.getProductList();
        logger.info("list={}",list);
    }

    @Test
    public void getProductById() throws Exception {
        long id = 1004;
        Product product = productService.getProductById(id);
        logger.info("product={}",product);
    }

    @Test
    public void exportProductUrl() throws Exception {
        long id = 1000;
        Exposure exposer = productService.exportProductUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void executeProduct() throws Exception {
        long id = 1000;
        long phone = 1234567890;
        String md5 = "2ac2926b326d3fc40b4e370a61f8a7cb";
        ProductExecution execution = productService.executeProduct(id,phone,md5);
        logger.info("execution={}",execution);
    }

    @Test
    public void testExcecute() throws Exception {
        long id = 1004;
        Exposure exposer = productService.exportProductUrl(id);
        if(exposer.isExposed()){
            logger.warn("exposer = {}",exposer);
            long phone = 1234567892;
            String md5 = exposer.getMd5();
            try{
                ProductExecution execution = productService.executeProduct(id,phone,md5);
                logger.info("execution={}",execution);
            }catch (ProductCloseExcption e){
                logger.error(e.getMessage());
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }

        }else{
            logger.warn("exposer = {}",exposer);
        }

    }

}