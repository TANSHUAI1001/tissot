package cn.shuai.tissot.enums;

/**
 * Created by shuai on 2017/1/2.
 */
public enum ActivityStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据重写");

    private int state;
    private String stateInfo;

    ActivityStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ActivityStatEnum stateOf(int index){
        for (ActivityStatEnum state: values()) {
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
