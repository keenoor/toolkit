package com.keenoor.toolkit.utils.httpclient;

/**
 * Description: http 连接时候出现的异常
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-21 18:46
 * Revision history:
 * Date         Remark
 * --------------------------------------------------
 */

public class HttpConnException extends RuntimeException {

    private static final long serialVersionUID = -4650455694650348030L;

    public HttpConnException() {
        super();
    }

    public HttpConnException(String message) {
        super(message);
    }

    public HttpConnException(Throwable cause) {
        super(cause);
    }

    public HttpConnException(String message, Throwable cause) {
        super(message, cause);
    }

}
