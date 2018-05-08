package cn.shuai.tissot.service.impl;

import cn.shuai.tissot.dao.ProductDao;
import cn.shuai.tissot.dao.SuccessRecordDao;
import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.dto.ProductExecution;
import cn.shuai.tissot.entity.Product;
import cn.shuai.tissot.entity.SuccessRecord;
import cn.shuai.tissot.enums.ProductStatEnum;
import cn.shuai.tissot.exception.ProductCloseExcption;
import cn.shuai.tissot.exception.ProductException;
import cn.shuai.tissot.exception.RepeatKillException;
import cn.shuai.tissot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


/**
 * Created by shuai on 2017/1/2.
 */
@Service
public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入service依赖
    @Autowired // @Resource, @InJect
    private ProductDao productDao;

    @Autowired
    private SuccessRecordDao successRecordDao;

    private final String salt = "suibianxieyixie123!@#$%^&*()";

    public List<Product> getProductList() {
        return productDao.queryAll(0,10);
    }

    public Product getProductById(long productId) {
        return productDao.queryById(productId);
    }

    public Exposure exportProductUrl(long productId) {
        Product product = productDao.queryById(productId);
        if(product == null){
            return new Exposure(false,productId);
        }
        Date startTime = product.getStartTime();
        Date endTime = product.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime()
                ||nowTime.getTime() > endTime.getTime()){
            return new Exposure(false,productId,nowTime.getTime()
                    ,startTime.getTime(),endTime.getTime());
        }
        String md5 = getMd5(productId);
        return new Exposure(true,md5,productId);
    }


    /**
     * 使用注解控制事务方法的优点：
     * 1：
     * 2：保证事务方法执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求
     * 3：
     *
     * mysql 行级锁
     */
    @Transactional
    public ProductExecution executeProduct(long productId, long userPhone, String md5) throws ProductException, RepeatKillException, ProductCloseExcption {
        if(md5 == null || !md5.equals(getMd5(productId))){
            throw new ProductException("data has been rewrite!!!");
        }
        //减库存，记录
        Date nowTime = new Date();
        try {
            int updateCount = productDao.reduceNumber(productId, nowTime);
            if (updateCount <= 0) {
                throw new ProductCloseExcption("activity han been closed");
            } else {
                int insertCont = successRecordDao.insertSuccessOperate(productId, userPhone);
                if (insertCont <= 0) {
                    throw new RepeatKillException("can not repeat kill");
                } else {
                    SuccessRecord successRecord = successRecordDao.queryByIdWithProduct(productId, userPhone);
                    return new ProductExecution(productId, ProductStatEnum.SUCCSS, successRecord);
                }
            }
        }catch (ProductCloseExcption e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译期期异常转化为运行期异常。
            throw new ProductException("inner error:"+e.getMessage());
        }
//        return null;
    }

    private String getMd5(long productId){
        String base = productId + "/" +salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
