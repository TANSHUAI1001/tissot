package cn.shuai.tissot.dto;

import cn.shuai.tissot.enums.ActivityResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by shuai on 2018/5/12.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResult<T> {
    private boolean success;

    private String errorCode;

    private String errorMessage;

    private T data;

    public ActivityResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ActivityResult(boolean success, String errorCode, String errorMessage) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ActivityResult(boolean success, ActivityResultEnum re) {
        this.success = success;
        this.errorCode = re.getErrorCode();
        this.errorMessage = re.getErrorMessage();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
