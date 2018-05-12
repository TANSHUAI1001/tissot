package cn.shuai.tissot.exception;

/**
 * Created by shuai on 2016/12/31.
 */

/**
 * 重复秒杀异常（运行期异常）
 */
public class RepeatKillException extends ActivityException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
