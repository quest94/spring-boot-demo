package org.quest94.demo.composites.config.advice.exception;

/**
 * <P>ClassName: CommonException
 * <P>Description: 自定义控制层抛出异常
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
