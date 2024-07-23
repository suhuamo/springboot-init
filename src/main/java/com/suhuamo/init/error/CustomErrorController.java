package com.suhuamo.init.error;

import com.suhuamo.init.common.ResponseResult;
import com.suhuamo.init.constant.HttpConstant;
import com.suhuamo.init.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author suhuamo
 * @date 2023-12-31
 * 自定义-请求错误时进入的控制器
 */
@Controller
@RequestMapping("/error")
@Slf4j
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    /**
     *  处理浏览器的页面请求
     *  加上了异常日志记录
     * @param request 请求
     * @param response 响应
     * @return ModelAndView
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.getErrorAttributeOptions()));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        if(model.get(HttpConstant.TRACE_TEXT) != null) {
            log.error("页面请求发生错误，错误原因为:" + model.get(HttpConstant.TRACE_TEXT).toString());
        } else {
            log.error("页面请求发生错误，错误原因为:...没有请求目标内容");
        }
        return modelAndView != null ? modelAndView : new ModelAndView(HttpConstant.ERROR_TEXT, model);
    }

    /**
     *  处理ajax请求
     *  修改返回类型： BaseResponse
     *  加上日常处理日志
     * @param request 请求
     * @return BaseResponse
     */
    @RequestMapping
    @ResponseBody
    public ResponseResult error(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        log.error("输入的错误路径：{}", requestURL.toString());
        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return ResponseResult.error(CodeEnum.NO_CONTENT.getCode(), CodeEnum.NO_CONTENT.getDesc());
        }
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions());
        String code = body.get(HttpConstant.STATUS_TEXT).toString();
        String message = body.get(HttpConstant.MESSAGE_TEXT).toString();
        if(body.get(HttpConstant.TRACE_TEXT) != null) {
            log.error("ajax请求发生错误，错误原因为:" + body.get(HttpConstant.TRACE_TEXT).toString());
        } else {
            log.error("ajax请求发生错误，错误原因为:...没有请求目标内容");
        }
        return ResponseResult.error(Integer.parseInt(code), message);
    }

    /**
     *  返回异常通知选项
     * @return ErrorAttributeOptions
     */
    protected ErrorAttributeOptions getErrorAttributeOptions() {
        return  ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.STACK_TRACE,
                ErrorAttributeOptions.Include.EXCEPTION);
    }
}