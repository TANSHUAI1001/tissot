package cn.shuai.tissot.exception;

/**
 * Created by shuai on 2016/12/31.
 */
public class ProductException extends RuntimeException{
    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
