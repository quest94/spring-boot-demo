package com.quec1994.config.controllerAdvice.exception;

/**
 * <P>ClassName: CommonException</P>
 * <P>Description: 自定义控制层抛出异常</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
public class CommonException extends RuntimeException {
    private String code;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String code, String message) {
        super(message);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
