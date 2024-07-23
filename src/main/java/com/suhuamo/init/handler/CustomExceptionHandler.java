package com.suhuamo.init.handler;

import com.suhuamo.init.common.ResponseResult;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author suhuamo
 * @date 2023-12-31
 * 自定义的公共异常处理器
 * 1.声明异常处理器
 * 2.对异常统一处理
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * 全局异常捕获
     *
     * @param request
     * @param response
     * @param e
     * @return ResponseResult
     * @version 1.0
     * @author suhuamo
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult<Object> error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage());
        // 如果是自定义类型
        if (e.getClass() == CustomException.class) {
            //类型转型
            CustomException ce = (CustomException) e;
            return ResponseResult.error(ce.getCode(), ce.getMessage());
            // 非自定义类型
        } else {
            return ResponseResult.error(CodeEnum.SERVER_ERROR.getCode(), e.getMessage());
        }
    }


    /**
     * 接口方法参数校验的全局异常捕获，即@valid等
     *
     * @param request
     * @param e
     * @return ResponseResult<String>
     * @version 1.0
     * @author suhuamo
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<String> handleValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder message = new StringBuilder();
        // 如果有异常信息
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            //拼接每一个异常信息
            for (int i = 0; i < allErrors.size(); i++) {
                message.append(allErrors.get(i).getDefaultMessage());
                //如果还有下一个错误，那么使用 , 拼接
                if (i != allErrors.size() - 1) {
                    message.append(", ");
                //    如果是最后一个错误，那么使用 。 结尾
                } else {
                    message.append("。");
                }
            }
        }
        return ResponseResult.error(CodeEnum.SERVER_ERROR.getCode(), message.toString());
    }
}
