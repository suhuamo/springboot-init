package com.suhuamo.init.common;

import com.suhuamo.init.constant.MysqlConstant;
import lombok.Data;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 分页参数限制
 */
@Data
public class PageProperties {
    /**
     * 当前页号
     */
    private long pageNum = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = MysqlConstant.SORT_ORDER_ASC;
}
