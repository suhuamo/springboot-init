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

    public CustomException(CodeEnum codeEnum, String message) {
        this.codeEnum = codeEnum;
        this.message = message;
    }

    public CustomException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
        this.message = codeEnum.getDesc();
    }
}
