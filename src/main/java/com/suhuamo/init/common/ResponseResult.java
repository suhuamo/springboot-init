package com.suhuamo.init.common;

import com.suhuamo.init.enums.CodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author suhuamo
 * @date 2023-12-31
 * 自定义的请求返回类型
 */
@Data
public class ResponseResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息说明
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 构造函数
     * 
     * @param codeEnum
     * @return null
     */
    private ResponseResult(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
    }

    /**
     * 构造函数
     * 
     * @param codeEnum
     * @param data
     * @return null
     */
    private ResponseResult(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
        this.data = data;
    }

    /**
     * 构造函数
     * 
     * @param code
     * @param message
     * @return null
     */
    private ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     * 
     * @param code
     * @param message
     * @param data
     * @return null
     */
    private ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:"请求成功",data:null}
     * 
     * @param
     * @return BaseResponse
     */
    public static ResponseResult ok() {
        return new ResponseResult(CodeEnum.SUCCESS);
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:"请求成功",data:{data}}
     * 
     * @param data
     * @return BaseResponse
     */
    public static ResponseResult ok(Object data) {
        return new ResponseResult(CodeEnum.SUCCESS, data);
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:{message},data:{data}}
     * 
     * @param message
     * @param data
     * @return BaseResponse
     */
    public static ResponseResult ok(String message, Object data) {
        return new ResponseResult(CodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 返回请求失败的构造函数，默认{code:999,message:抱歉，系统繁忙，请稍后重试！,data:null}
     * 
     * @param
     * @return BaseResponse
     */
    public static ResponseResult error() {
        return new ResponseResult(CodeEnum.SERVER_ERROR);
    }

    /**
     * 返回请求失败的构造函数，默认{code:{codeEnum.code},message:{codeEnum.desc},data:null}
     * 
     * @param codeEnum
     * @return BaseResponse
     */
    public static ResponseResult error(CodeEnum codeEnum) {
        return new ResponseResult(codeEnum);
    }

    /**
     * 返回请求失败的构造函数，默认{code:{code},message:{message},data:null}
     * 
     * @param
     * @return BaseResponse
     */
    public static ResponseResult error(Integer code, String message) {
        return new ResponseResult(code, message);
    }
}
