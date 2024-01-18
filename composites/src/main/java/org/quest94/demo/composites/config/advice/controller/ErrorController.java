package org.quest94.demo.composites.config.advice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

/**
 * <P>ClassName: ErrorController
 * <P>Description: 错误返回的Controller类
 *
 * @author quest94
 * @version V1.0, 2019/1/28
 **/
@Controller
// @Api(tags = "统一错误返回处理控制器")
@RequestMapping("return")
public class ErrorController {

    @RequestMapping("html")
    // @ApiOperation(value = "返回错误信息显示页面")
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String resultHtml() {
        return "error/error";
    }

    @RequestMapping("json")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    // @ApiOperation(value = "返回json格式的错误信息")
    public ErrorResp resultJson(HttpServletRequest request) {
        ErrorResp errorResp = new ErrorResp();
        errorResp.setCode(119);
        StringBuilder respMsg = new StringBuilder();
        Optional.ofNullable(request.getAttribute("message")).ifPresent(m -> respMsg.append(m.toString()));
        // 有error的话说明是预想之外的错误
        Optional.ofNullable(request.getAttribute("error")).ifPresent(error -> {
            errorResp.setCode(9999);
            respMsg.append("<br/>错误原因：").append(error.toString());
        });
        errorResp.setMsg(respMsg.toString());
        return errorResp;
    }

}
