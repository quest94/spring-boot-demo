package com.quec1994.common.controllerAdvice.exception;

/**
 * <P>ClassName: BusinessException</P>
 * <P>Description: 自定义控制层抛出异常</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
