package com.quec1994.common.controllerAdvice.exception;

/**
 * <P>ClassName: ServiceException</P>
 * <P>Description: 自定义业务层抛出异常</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
public class ServiceException extends BusinessException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
