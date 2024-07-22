package com.suhuamo.init.exception;

import com.suhuamo.init.enums.CodeEnum;
import lombok.Getter;

/**
 * @author suhuamo
 * @date 2023-12-31
 * 自定义异常
 */
@Getter
public class CustomException extends RuntimeException  {

    /**
     * 异常code码
     */
    private final CodeEnum codeEnum;
    /**
     * 异常信息
     */
    private final String message;
    /**
     * 错误码
     */
    private final Integer code;

    public CustomException(CodeEnum codeEnum, String message) {
        this.codeEnum = codeEnum;
        this.message = message;
        this.code = codeEnum.getCode();
    }

    public CustomException(String message) {
        this.code = CodeEnum.SERVER_ERROR.getCode();
        this.message = message;
        this.codeEnum = CodeEnum.SERVER_ERROR;
    }

    public CustomException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
        this.message = codeEnum.getDesc();
        this.code = codeEnum.getCode();
    }

    /**
     * 系统级别异常
     * @param message
     * @return CustomException
     * @version 1.0
     * @author suhuamo
     */
    public static CustomException ServerException(String message) {
        return new CustomException(CodeEnum.SERVER_ERROR, message);
    }

    /**
     * 前端接口请求基本异常
     * @param message
     * @return CustomException
     * @version 1.0
     * @author suhuamo
     */
    public static CustomException QueryException(String message) {
        return new CustomException(CodeEnum.PARAM_ERROR, message);
    }
}
