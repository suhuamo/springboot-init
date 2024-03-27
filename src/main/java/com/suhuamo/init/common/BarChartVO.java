package com.suhuamo.init.common;

import lombok.Data;

import java.util.List;

/**
 * 柱状图图表VO
 */
@Data
public class BarChartVO {
    /**
     * x轴数据，一般显示文字
     */
    private List<String> x;
    /**
     * y轴数据，一般显示数值
     */
    private List<Integer> y;
}
