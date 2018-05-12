package cn.shuai.tissot.dao;

import cn.shuai.tissot.entity.SuccessRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by shuai on 2016/12/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SuccessRecordDaoTest {
    @Resource
    private SuccessRecordDao successRecordDao;

    @Test
    public void insertSuccessOperate() throws Exception {
        int result = successRecordDao.insertSuccessOperate(1000L,1234567890);
        System.out.println(result);
    }

    @Test
    public void queryByIdWithActivity() throws Exception {
        SuccessRecord record = successRecordDao.queryByIdWithActivity(1000L,1234567890);
        System.out.println(record);
    }

}