package com.suhuamo.init.generate;

import lombok.Getter;

/**
 * @author suhuamo
 * @slogan 耐心等，太阳总会升起来的。
 * @date 2025/04/21
 * @description 模板类型枚举类
 */
@Getter
public enum TemplateTypeEnum {
    /*
     *  实体类模板只会生成简单的 PO、mapper、service、controller。不会添加任何的新方法
     */
    POJO(0, "实体类模板", "/templates/pojo"),
    /*
     *  标准模板不仅会生成 PO、mapper、service、controller。还会添加增删改查的方法、接口、DTO等。
     * 通常需要在该项目中使用，因为需要依赖一些该项目的类。
     */
    STANDARD(1, "标准模板", "/templates/standard");
    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名称
     */
    private final String name;
    /**
     * 模板路径
     */
    private final String path;

    TemplateTypeEnum(Integer type, String name, String path) {
        this.type = type;
        this.name = name;
        this.path = path;
    }

    /**
     * 根据type获取枚举类对象
     *
     * @param type 类型
     * @return TemplateTypeEnum 枚举类对象
     * @version
     * @author yuanchuncheng
     */
    public static TemplateTypeEnum getEnumByType(Integer type) {
        for (TemplateTypeEnum value : TemplateTypeEnum.values()) {
            // 如果类型匹配成功，那么返回匹配成功的枚举类
            if (value.getType().equals(type)) {
                return value;
            }
        }
        // 如果输入错误，则默认返回实体类模板
        return POJO;
    }

    /**
     * 根据类型获取名称
     *
     * @param type 类型
     * @return String 名称
     * @version
     * @author yuanchuncheng
     */
    public static String getNameByType(Integer type) {
        for (TemplateTypeEnum value : TemplateTypeEnum.values()) {
            // 如果类型匹配成功，那么返回匹配成功的枚举类的名称
            if (value.getType().equals(type)) {
                return value.getName();
            }
        }
        // 如果输入错误，则默认返回实体类模板
        return POJO.name;
    }

    /**
     * 根据名称获取类型
     *
     * @param name 名称
     * @return Integer 类型
     * @version
     * @author yuanchuncheng
     */
    public static Integer getTypeByName(String name) {
        for (TemplateTypeEnum value : TemplateTypeEnum.values()) {
            // 如果名称匹配成功，那么返回匹配成功的枚举类的类型
            if (value.getName().equals(name)) {
                return value.getType();
            }
        }
        // 如果输入错误，则默认返回实体类模板
        return POJO.type;
    }

    /**
     * 根据类型获取名称
     *
     * @param type 类型
     * @return String 名称
     * @version
     * @author yuanchuncheng
     */
    public static String getPathByType(Integer type) {
        for (TemplateTypeEnum value : TemplateTypeEnum.values()) {
            // 如果类型匹配成功，那么返回匹配成功的枚举类的名称
            if (value.getType().equals(type)) {
                return value.getPath();
            }
        }
        // 如果输入错误，则默认返回实体类模板的路径
        return POJO.path;
    }
}
