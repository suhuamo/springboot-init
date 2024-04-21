package com.suhuamo.init.enums;

import lombok.Getter;

/**
 * @author suhuamo
 * @date 2023-12-31
 * 请求的各种状态码
 */
@Getter
public enum CodeEnum {
    SUCCESS(200, "成功"),
    UNAUTHORIZED_NONE(401, "未授权"),
    UNAUTHORIZED_EXPIRE(402, "授权值已经过期，请附带有效期内的token"),
    UNAUTHORIZED_ERROR(403, "授权值错误，请附带正确的token"),
    TIMED_OUT(410, "请求超时"),
    PARAM_ERROR(420, "参数错误"),
    NOT_FOUND_ERROR(421, "找不到数据"),
    OPERATION_ERROR(501, "操作错误"),
    NO_CONTENT(598,"没有内容"),
    SERVER_ERROR(599,"抱歉，系统内错误，请稍后重试！");
    /**
     *  状态码
     */
    private final Integer code;
    /**
     *  状态码的描述
     */
    private final String desc;


    CodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据类型获取枚举,当无该类型时，返回null
     *
     * @param code 状态码
     * @return A
     */
    public static CodeEnum getByType(Integer code) {
        for (CodeEnum itemEnum : CodeEnum.values()) {
            if (itemEnum.getCode().equals(code)) {
                return itemEnum;
            }
        }
        return null;
    }


}
