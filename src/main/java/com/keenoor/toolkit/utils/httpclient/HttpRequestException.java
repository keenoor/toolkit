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
public class HttpRequestException extends Exception {

    private static final long serialVersionUID = -2806676512612298459L;

    private StatusLine statusLine;

    public HttpRequestException() {
        super();
    }

    public HttpRequestException(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public HttpRequestException(String message) {
        super(message);
    }

    public HttpRequestException(Throwable cause) {
        super(cause);
    }

    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }
}
