package com.keenoor.toolkit.utils.model;

import java.io.Serializable;

/**
 * Author:      chenliuchun
 * Date:        2018/6/5
 * Description: 中间件接口返回参数类
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 * @author clc
 */

public class MwResult<T> implements Serializable {

    private static final long serialVersionUID = -4179443440883499092L;
    private String code;
    private T detail;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MwResult{" +
                "code='" + code + '\'' +
                ", detail=" + detail +
                '}';
    }
}
