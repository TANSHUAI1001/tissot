package cn.shuai.tissot.dao;

import cn.shuai.tissot.entity.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Created by shuai on 2016/12/31.
 */
//配置spring和junit整合，让junit启东市加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ActivityDaoTest {
    //注入dao实现类依赖
    @Resource
    private ActivityDao activityDao;

    @Test
    public void reduceNumber() throws Exception {
        Date date = new Date();
        int result = activityDao.reduceNumber(1000L,date);
        System.out.println(result);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Activity activity = activityDao.queryById(id);
        System.out.println(activity.getName());
        System.out.print(activity);
    }

    @Test
    public void queryAll() throws Exception {
        List<Activity> activities = activityDao.queryAll(0,100);
        System.out.println(activities.size());
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

}