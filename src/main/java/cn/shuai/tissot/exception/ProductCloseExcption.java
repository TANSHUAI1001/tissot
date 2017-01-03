package cn.shuai.tissot.exception;

/**
 * Created by shuai on 2016/12/31.
 */

/**
 * 秒杀关闭异常
 */
public class ProductCloseExcption extends ProductException{
    public ProductCloseExcption(String message) {
        super(message);
    }

    public ProductCloseExcption(String message, Throwable cause) {
        super(message, cause);
    }
}
