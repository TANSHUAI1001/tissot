package cn.shuai.tissot.dao;

import cn.shuai.tissot.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by shuai on 2016/12/29.
 */
public interface ProductDao {
    /**
     * 减库存
     * @param productId
     * @param operateTime
     * @return 如果影响行数 >= 1,表示影响的行数，等于0表示失败
     */
    int reduceNumber(@Param("productId") long productId, @Param("operateTime") Date operateTime);

    Product queryById(long productId);

    /**
     * 根据偏移量查询列表
     * @param offset
     * @param limit
     * @return
     */
    List<Product> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
