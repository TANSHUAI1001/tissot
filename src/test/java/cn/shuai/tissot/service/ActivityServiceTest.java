package cn.shuai.tissot.service;

import cn.shuai.tissot.dto.ActivityExecution;
import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.entity.Activity;
import cn.shuai.tissot.exception.ActivityCloseException;
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
public class ActivityServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ActivityService activityService;

    @Test
    public void getActivityList() throws Exception {
        List<Activity> list = activityService.getActivityList();
        logger.info("list={}",list);
    }

    @Test
    public void getActivityById() throws Exception {
        long id = 1004;
        Activity activity = activityService.getActivityById(id);
        logger.info("activity={}", activity);
    }

    @Test
    public void exportActivityUrl() throws Exception {
        long id = 1000;
        Exposure exposure = activityService.exportActivityUrl(id);
        logger.info("exposure={}",exposure);
    }

    @Test
    public void executeActivity() throws Exception {
        long id = 1000;
        long phone = 1234567890;
        String md5 = "2ac2926b326d3fc40b4e370a61f8a7cb";
        ActivityExecution execution = activityService.executeActivity(id,phone,md5);
        logger.info("execution={}",execution);
    }

    @Test
    public void testExecute() throws Exception {
        long id = 1004;
        Exposure exposure = activityService.exportActivityUrl(id);
        if(exposure.isExposed()){
            logger.warn("exposure = {}",exposure);
            long phone = 1234567892;
            String md5 = exposure.getMd5();
            try{
                ActivityExecution execution = activityService.executeActivity(id,phone,md5);
                logger.info("execution={}",execution);
            }catch (ActivityCloseException e){
                logger.error(e.getMessage());
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }

        }else{
            logger.warn("exposure = {}",exposure);
        }

    }

}