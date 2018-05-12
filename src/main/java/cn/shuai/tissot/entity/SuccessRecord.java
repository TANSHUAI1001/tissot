package cn.shuai.tissot.entity;

import java.util.Date;

/**
 * Created by shuai on 2016/12/29.
 */
public class SuccessRecord {
    private long activityId;
    private long userPhone;
    private short state;
    private Date createTime;

    //多对一
    private Activity activity;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "SuccessRecord{" +
                "activityId=" + activityId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", activity=" + activity +
                '}';
    }
}
