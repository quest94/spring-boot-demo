package com.quec1994.common.controllerAdvice.Controller;

import com.quec1994.common.controllerAdvice.Controller.bean.CommonResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <P>ClassName: ErrorController</P>
 * <P>Description: 错误返回的Controller类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@Controller
public class ErrorController {

    @RequestMapping("returnHtml")
    public String resultHtml() {
        return "error/error";
    }

    @RequestMapping("returnJson")
    @ResponseBody
//    @ResponseStatus(code = HttpStatus.SEE_OTHER)
    public CommonResp resultJson(HttpServletRequest request) {
        int respCode = 999;
        StringBuilder respMsg = new StringBuilder();
        respMsg.append(request.getAttribute("message").toString());
        Object error = request.getAttribute("error");
        if (error != null) {
            respCode = 9999;
            respMsg.append("<br/>错误原因：").append(error.toString());
        }
        return new CommonResp(respCode, respMsg.toString());
    }

}
