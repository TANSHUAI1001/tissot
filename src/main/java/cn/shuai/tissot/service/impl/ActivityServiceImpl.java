package cn.shuai.tissot.service.impl;

import cn.shuai.tissot.dao.ActivityDao;
import cn.shuai.tissot.dao.SuccessRecordDao;
import cn.shuai.tissot.dto.ActivityExecution;
import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.entity.Activity;
import cn.shuai.tissot.entity.SuccessRecord;
import cn.shuai.tissot.enums.ActivityStatEnum;
import cn.shuai.tissot.exception.ActivityCloseException;
import cn.shuai.tissot.exception.ActivityException;
import cn.shuai.tissot.exception.RepeatKillException;
import cn.shuai.tissot.service.ActivityService;
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
public class ActivityServiceImpl implements ActivityService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入service依赖
    @Autowired // @Resource, @InJect
    private ActivityDao activityDao;

    @Autowired
    private SuccessRecordDao successRecordDao;

    private final String salt = "suibianxieyixie123!@#$%^&*()";

    public List<Activity> getActivityList() {
        return activityDao.queryAll(0,10);
    }

    public Activity getActivityById(long activityId) {
        return activityDao.queryById(activityId);
    }

    public Exposure exportActivityUrl(long activityId) {
        Activity activity = activityDao.queryById(activityId);
        if(activity == null){
            return new Exposure(false,activityId);
        }
        Date startTime = activity.getStartTime();
        Date endTime = activity.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime()
                ||nowTime.getTime() > endTime.getTime()){
            return new Exposure(false,activityId,nowTime.getTime()
                    ,startTime.getTime(),endTime.getTime());
        }
        String md5 = getMd5(activityId);
        return new Exposure(true,md5,activityId);
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
    public ActivityExecution executeActivity(long activityId, long userPhone, String md5) throws ActivityException, RepeatKillException, ActivityCloseException {
        if(md5 == null || !md5.equals(getMd5(activityId))){
            throw new ActivityException("data has been rewrite!!!");
        }
        //减库存，记录
        Date nowTime = new Date();
        try {
            int updateCount = activityDao.reduceNumber(activityId, nowTime);
            if (updateCount <= 0) {
                throw new ActivityCloseException("activity han been closed");
            } else {
                int insertCont = successRecordDao.insertSuccessOperate(activityId, userPhone);
                if (insertCont <= 0) {
                    throw new RepeatKillException("can not repeat kill");
                } else {
                    SuccessRecord successRecord = successRecordDao.queryByIdWithActivity(activityId, userPhone);
                    return new ActivityExecution(activityId, ActivityStatEnum.SUCCESS, successRecord);
                }
            }
        }catch (ActivityCloseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译期期异常转化为运行期异常。
            throw new ActivityException("inner error:"+e.getMessage());
        }
//        return null;
    }

    private String getMd5(long activityId){
        String base = activityId + "/" +salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
