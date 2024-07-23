package com.suhuamo.init.enums;

import lombok.Getter;

/**
 * @author suhuamo
 * @date 2024-04-22
 * @slogan 不待春风慢，我以明月宴群山。
 * @description 性别的枚举类
 */
@Getter
public enum SexEnum {
    MAN((byte) 0, "男"),
    WOMAN((byte) 1, "女");
    /**
     * 类型
     */
    private final Byte type;
    /**
     * 描述
     */
    private final String desc;

    SexEnum(Byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据类型获取枚举,当无该类型时，返回null
     *
     * @param type
     * @return SexEnum
     */
    public static SexEnum getByType(Integer type) {
        for (SexEnum itemEnum : SexEnum.values()) {
            if (itemEnum.getType().equals(type)) {
                return itemEnum;
            }
        }
        return null;
    }
}
