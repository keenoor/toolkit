package com.keenoor.toolkit.utils.money;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Author:      chenliuchun
 * Date:        2017/11/13
 * Description: 格式化金融相关数字
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */

public class MoneyFormatUtil {

    /**
     * 保留2位小数小数
     *
     * @param value
     * @return
     */
    public static String floatStringDefault(double value) {
        return floatString(value, 2);
    }

    public static String floatStringDefault(String value) {
        return floatString(value, 2);
    }

    /**
     * 保留指定位小数小数
     *
     * @param value
     * @param scale
     * @return
     */
    public static String floatString(float value, int scale) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, RoundingMode.HALF_UP); // 默认四舍五入
        return bd.toString();
    }

    public static String floatString(double value, int scale) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.toString();
    }

    public static String floatString(String value, int scale) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * 将小数转化成百分比
     *
     * @param p
     * @return
     */
    public static String percent(String p) {
        if (StringUtils.isEmpty(p)){
            return "%";
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        return nf.format(new BigDecimal(p));
    }

    /**
     * 获取两个数字的百分比
     *
     * @param p1
     * @param p2
     * @param scale
     * @return
     */
    public static String percent2Num(double p1, double p2, int scale) {
        String str;
        double p3 = MathUtil.divide(p1, p2);
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(scale);
        str = nf.format(p3);
        return str;
    }
}
