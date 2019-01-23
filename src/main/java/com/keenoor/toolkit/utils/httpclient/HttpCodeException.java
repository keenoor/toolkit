package com.keenoor.toolkit.utils.httpclient;

import org.apache.http.StatusLine;

/**
 * author: chenliuchun
 * Date:    2018/8/6
 * Description: http 非 200 错误码的异常类
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
public class HttpCodeException extends Exception {

    private static final long serialVersionUID = -2806676512612298459L;

    private StatusLine statusLine;

    public HttpCodeException() {
        super();
    }

    public HttpCodeException(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public HttpCodeException(String message) {
        super(message);
    }

    public HttpCodeException(Throwable cause) {
        super(cause);
    }

    public HttpCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }
}
