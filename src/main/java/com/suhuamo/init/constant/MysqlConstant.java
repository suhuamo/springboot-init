package com.suhuamo.init.constant;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 数据库字段
 */
public class MysqlConstant {
    /**
     * 升序
     */
    public static final String SORT_ORDER_ASC = "ASC";

    /**
     * 降序
     */
    public static final String SORT_ORDER_DESC = " DESC";
    /**
     * 创建时间字段--java参数字段
     */
    public static final String CREATE_TIME_FILED = "createTime";
    /**
     * 更新时间字段--java参数字段
     */
    public static final String UPDATE_TIME_FILED = "updateTime";
    /**
     * 逻辑删除字段--java参数字段
     */
    public static final String DELETE_FLAG_FILED = "deleteFlag";


    /**
     * 常量类不允许new
     */
    private MysqlConstant() {

    }
}
