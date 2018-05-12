package cn.shuai.tissot.dto;

import cn.shuai.tissot.entity.SuccessRecord;
import cn.shuai.tissot.enums.ActivityStatEnum;

/**
 * Created by shuai on 2016/12/31.
 */
public class ActivityExecution {

    private long activityId;

    private int state;

    private String stateInfo;

    private SuccessRecord successRecord;

    public ActivityExecution(long activityId, ActivityStatEnum stateEnum, SuccessRecord successRecord) {
        this.activityId = activityId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successRecord = successRecord;
    }

    public ActivityExecution(long activityId, ActivityStatEnum stateEnum) {
        this.activityId = activityId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessRecord getSuccessRecord() {
        return successRecord;
    }

    public void setSuccessRecord(SuccessRecord successRecord) {
        this.successRecord = successRecord;
    }

    @Override
    public String toString() {
        return "ActivityExecution{" +
                "activityId=" + activityId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successRecord=" + successRecord +
                '}';
    }
}
