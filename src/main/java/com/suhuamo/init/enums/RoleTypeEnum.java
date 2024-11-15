package com.suhuamo.init.enums;

/**
 * @author suhuamo
 * @date 2024-04-08
 * @description
 * 角色类型枚举类
 */
public enum RoleTypeEnum {
    // admin-管理员；user-普通用户
    ADMIN((byte) 0, "admin"),
    USER((byte) 1, "user");
    /**
     * 类型
     */
    private final Byte type;
    /**
     * 描述
     */
    private final String desc;

    RoleTypeEnum(Byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Byte getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据类型获取枚举,当无该类型时，返回null
     *
     * @param type 类型
     * @return RoleTypeEnum
     */
    public static RoleTypeEnum getByType(Byte type) {
        for (RoleTypeEnum itemEnum : RoleTypeEnum.values()) {
            if (itemEnum.getType().equals(type)) {
                return itemEnum;
            }
        }
        return null;
    }

    /**
     * 根据描述获取枚举,当无该类型时，返回null
     *
     * @param desc 描述
     * @return RoleTypeEnum
     */
    public static RoleTypeEnum getByDesc(String desc) {
        for (RoleTypeEnum itemEnum : RoleTypeEnum.values()) {
            if (itemEnum.getDesc().equals(desc)) {
                return itemEnum;
            }
        }
        return null;
    }
}
