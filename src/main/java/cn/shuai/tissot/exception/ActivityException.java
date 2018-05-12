package cn.shuai.tissot.exception;

/**
 * Created by shuai on 2016/12/31.
 */
public class ActivityException extends RuntimeException{
    public ActivityException(String message) {
        super(message);
    }

    public ActivityException(String message, Throwable cause) {
        super(message, cause);
    }
}
