package com.suhuamo.init.handler;

import com.suhuamo.init.common.ResponseResult;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author suhuamo
 * @date 2023-12-31
 * 自定义的公共异常处理器
 *      1.声明异常处理器
 *      2.对异常统一处理
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        // 如果是自定义类型
        if(e.getClass() == CustomException.class) {
            //类型转型
            CustomException ce = (CustomException) e;
            return ResponseResult.error(ce.getCodeEnum().getCode(), ce.getMessage());
        // 非自定义类型
        }else{
            return ResponseResult.error(CodeEnum.SERVER_ERROR);
        }
    }
}
