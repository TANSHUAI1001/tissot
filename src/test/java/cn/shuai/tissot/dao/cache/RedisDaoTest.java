package cn.shuai.tissot.dao.cache;

import cn.shuai.tissot.dao.ActivityDao;
import cn.shuai.tissot.entity.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class RedisDaoTest {

    @Resource
    private RedisDao redisDao;

    @Resource
    private ActivityDao activityDao;

    private long id = 1001;

    @Test
    public void getSeckill() throws Exception {
        Activity activity = redisDao.getSeckill(id);
        if(activity == null){
            activity = activityDao.queryById(id);
            if(activity != null){
                String result = redisDao.putSeckill(activity);
                System.out.println(result);
                Activity buff = redisDao.getSeckill(id);
                System.out.println(buff);
            }
        }
    }

    @Test
    public void putSeckill() throws Exception {
    }

}