package com.keenoor.toolkit.utils.httpclient;

/**
 * Description: http 响应体结果处理异常
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-21 18:46
 * Revision history:
 * Date         Remark
 * --------------------------------------------------
 */

public class HttpProcessException extends RuntimeException {

    private static final long serialVersionUID = -5742996367266239878L;

    public HttpProcessException() {
        super();
    }

    public HttpProcessException(String message) {
        super(message);
    }

    public HttpProcessException(Throwable cause) {
        super(cause);
    }

    public HttpProcessException(String message, Throwable cause) {
        super(message, cause);
    }

}
