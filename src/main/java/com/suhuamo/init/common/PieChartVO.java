package com.suhuamo.init.common;

import lombok.Data;

/**
 * 柱状图图表VO类
 */
@Data
public class PieChartVO {
    /**
     * x轴数据，一般显示文字
     */
    private String x;
    /**
     * y轴数据，一般显示数值
     */
    private Integer y;
}
