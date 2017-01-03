package cn.shuai.tissot.dao;

import cn.shuai.tissot.entity.SuccessRecord;
import org.apache.ibatis.annotations.Param;

/**
 * Created by shuai on 2016/12/29.
 */
public interface SuccessRecordDao {
    /**
     * 插入操作明细
     * @param productId
     * @param userPhone
     * @return
     */
    int insertSuccessOperate(@Param("productId") long productId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询
     * @param porductId
     * @return
     */
    SuccessRecord queryByIdWithProduct(@Param("productId") long porductId,@Param("userPhone")long userPhone);
}
