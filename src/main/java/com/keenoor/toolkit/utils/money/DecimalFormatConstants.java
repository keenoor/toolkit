package com.keenoor.toolkit.utils.money;

/**
 * 金额格式_枚举类
 *
 * @author xwy
 *         2011-04-01
 */
public enum DecimalFormatConstants {
    /**
     * 整型：1234567->1,234,567
     */
    INTEGER_NUMBER("#,##0"),
    /**
     * 金额：1234567.89->1,234,567.89
     */
    MONEY("#,##0.00"),
    /**
     * 数值：1234567.89->1234567.89
     */
    NUMBER("0.00"),
    /**
     * 百分比：1234567.89->123456789%
     */
    PER_NUMBER("0.000%"),
    /**
     * 正常：1234567.89->1234567.89
     */
    NORMAL_NUMBER("0"),
    /**
     * 个位补零：9->09
     */
    TOW_00_NUMBER("00");


    /**
     * 数值格式
     */
    private String format;

    /**
     * 构造函数
     *
     * @param format 格式字符串
     */
    DecimalFormatConstants(String format) {
        this.format = format;
    }

    /**
     * 返回字符串格式
     *
     * @return 字符串格式
     */
    public String value() {
        return format;
    }

}
