package cn.shuai.tissot.exception;

/**
 * Created by shuai on 2016/12/31.
 */

/**
 * 秒杀关闭异常
 */
public class ActivityCloseException extends ActivityException {
    public ActivityCloseException(String message) {
        super(message);
    }

    public ActivityCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
