package cn.shuai.tissot.enums;

/**
 * Created by shuai on 2018/5/12.
 */
public enum ActivityResultEnum {
    SUCCESS("1","请求成功"),
    FAIL("0","请求失败"),
    ERROR_PARAM("-1","参数不正确"),
    NOT_FOUND("-2","没有相应记录"),
    NOT_REGISTER("-3","未注册");

    private String errorCode;
    private String errorMessage;

    ActivityResultEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
