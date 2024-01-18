package org.quest94.demo.composites.config.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.config.advice.exception.CommonException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * <P>ClassName: CommonExceptionHandler
 * <P>Description: 自定义异常处理类
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@ControllerAdvice
@Slf4j
public class ControllerAdviceHandler {

    /**
     * 给ModelMap设置通用值
     *
     * @param request 浏览器请求
     * @param model   页面传参模型
     * @author qyz, 2018/8/25 16:12
     **/
    @ModelAttribute
    public void setGlobalAttribute(HttpServletRequest request, ModelMap model) {
        model.addAttribute("basePath", request.getContextPath());
        model.addAttribute("userName", "USER-NAME");
    }

    /**
     * 拦截并处理自定义异常类型
     *
     * @param ex 发生的异常
     * @return 根据访问方式跳转控制器路径
     * @author V1.0, quec1994, 2019/1/28 20:02
     **/
    @ExceptionHandler(CommonException.class)
    public String exceptionHandler(HttpServletRequest request, CommonException ex) {
        return jump(request, ex.getMessage(), null);
    }

    /**
     * 拦截并处理转换http消息时发生的异常
     *
     * @param ex 发生的异常
     * @return 根据访问方式跳转控制器路径
     * @author V1.0, quec1994, 2019/1/28 20:02
     **/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String httpMsgExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException ex) {
        String string = "消息转换出错，ip地址：" + request.getRemoteAddr() + "；访问方式：" + request.getMethod() + "；访问地址：" + request.getRequestURI();
        log.error(string, getClass(), ex);
        return jump(request, "消息转换出错", ex.getMessage());
    }

    /**
     * 拦截并处理参数验证失败时的异常
     *
     * @param ex 发生的异常
     * @return 根据访问方式跳转控制器路径
     * @author V1.0, quec1994, 2019/1/28 20:02
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex) {
        return methodArgumentNotValidException(request, ex.getBindingResult());
    }

    /**
     * 拦截并处理参数验证失败时的异常即方法，（参数注解@RequestBody时）参数不正确
     *
     * @param request       请求
     * @param bindingResult 参数验证结果
     * @return 根据访问方式跳转控制器路径
     * @author V1.0, quec1994, 2019/1/29 22:35
     **/
    private String methodArgumentNotValidException(HttpServletRequest request, BindingResult bindingResult) {
        String message;
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            message = fieldError.getDefaultMessage();
        } else {
            message = "参数传入错误，开发人员未设置提示";
        }
        return jump(request, message, null);
    }

    /**
     * 拦截并处理 MethodArgumentNotValidException 类型的异常,即方法（参数注解@RequestBody时）参数不正确
     *
     * @param ex 发生的异常
     * @return 根据访问方式跳转控制器路径
     * @author V1.0, quec1994, 2019/1/29 22:25
     **/
    @ExceptionHandler(BindException.class)
    public String handleBindException(HttpServletRequest request, BindException ex) {
        return methodArgumentNotValidException(request, ex.getBindingResult());
    }

    /**
     * 拦截并处理 Exception 类型的异常
     *
     * @param request 浏览器请求
     * @param ex      运行时抛出的异常
     * @return 根据访问方式跳转控制器路径
     * @author qyz, 2018/8/25 16:13
     * @author V1.0, quec1994, 2019/1/28 21:05
     **/
    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String error;
        log.error(ex.getMessage(), getClass(), ex);
        if (ex instanceof NullPointerException) {
            error = "空指针异常";
        } else if (ex instanceof IOException) {
            error = "文件读写异常";
        } else if (ex instanceof TimeoutException) {
            error = "服务超时";
        } else {
            error = ex.getMessage();
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
        String message = "系统出现了预想之外的错误<br/>如果一直显示这个，请记下出错时间后联系管理员<br/>当前时间：" + now;
        return jump(request, message, error);
    }

    /**
     * 判断请求的返回数据类型
     *
     * @param request 请求
     * @param message 错误提示
     * @param error   错误信息
     * @return 请求的返回数据对应的控制器路径
     * @author V1.0, quec1994, 2019/1/28 21:08
     **/
    private String jump(HttpServletRequest request, String message, String error) {
        request.setAttribute("message", message);
        Optional.ofNullable(error).ifPresent(e -> request.setAttribute("error", e));

        Optional<String> aOptional = Optional.ofNullable(request.getHeader("accept"));
        boolean isAcceptContain = aOptional.filter(u -> u.contains("application/json")).isPresent();

        Optional<String> ctOptional = Optional.ofNullable(request.getHeader("Content-Type"));
        boolean isContentTypeContain = ctOptional.filter(u -> u.contains("application/json")).isPresent();

        Optional<String> xmlrOptional = Optional.ofNullable(request.getHeader("X-Requested-With"));
        boolean isXMLHttpRequest = xmlrOptional.filter(u -> u.contains("XMLHttpRequest")).isPresent();
        // 判断是否是ajax请求
        if (isAcceptContain || isContentTypeContain || isXMLHttpRequest) {
            return "forward:/return/json";
        } else {
            return "forward:/return/html";
        }
    }

}
