package cn.shuai.tissot.dto;

/**
 * Created by shuai on 2016/12/31.
 */

/**
 * 暴露秒杀地址DTO
 */
public class Exposure {

    private boolean exposed;

    private String md5;

    private long activityId;
    //系统当前时间（毫秒）
    private long now;

    private long start;

    private long end;

    public Exposure(boolean exposed, String md5, long activityId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.activityId = activityId;
    }

    public Exposure(boolean exposed, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposure(boolean exposed, long activityId, long now, long start, long end) {
        this.exposed = exposed;
        this.activityId = activityId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposure(boolean exposed, long activityId) {
        this.exposed = exposed;
        this.activityId = activityId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposure{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", activityId=" + activityId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
