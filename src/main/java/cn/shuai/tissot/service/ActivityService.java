package cn.shuai.tissot.service;

import cn.shuai.tissot.dto.ActivityExecution;
import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.entity.Activity;
import cn.shuai.tissot.exception.ActivityCloseException;
import cn.shuai.tissot.exception.ActivityException;
import cn.shuai.tissot.exception.RepeatKillException;

import java.util.List;

/**
 * Created by shuai on 2016/12/31.
 */
public interface ActivityService {
    List<Activity> getActivityList();

    Activity getActivityById(long activityId);

    Exposure exportActivityUrl(long activityId);

    ActivityExecution executeActivity(long activityId, long userPhone, String md5)
            throws ActivityException,RepeatKillException,ActivityCloseException;
}
