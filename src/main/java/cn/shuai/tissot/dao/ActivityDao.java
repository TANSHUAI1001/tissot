package cn.shuai.tissot.dao;

import cn.shuai.tissot.entity.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by shuai on 2016/12/29.
 */
public interface ActivityDao {
    /**
     * 减库存
     * @param activityId
     * @param operateTime
     * @return 如果影响行数 >= 1,表示影响的行数，等于0表示失败
     */
    int reduceNumber(@Param("activityId") long activityId, @Param("operateTime") Date operateTime);

    Activity queryById(long activityId);

    /**
     * 根据偏移量查询列表
     * @param offset
     * @param limit
     * @return
     */
    List<Activity> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
