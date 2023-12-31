package com.suhuamo.init.constant;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * http相关的常量
 */
public class HttpConstant {
    /**
     * http请求中的字段trace
     */
    public static final String TRACE_TEXT = "trace";
    /**
     * http请求中的字段error
     */
    public static final String ERROR_TEXT = "error";
    /**
     * http请求中的字段status
     */
    public static final String STATUS_TEXT = "status";
    /**
     * http请求中的字段message
     */
    public static final String MESSAGE_TEXT = "message";

    /**
     * 常量类不允许new
     */
    private HttpConstant() {
    }
}
