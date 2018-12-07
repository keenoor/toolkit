package com.keenoor.toolkit.utils.httpclient;

import org.apache.http.HttpException;
import org.apache.http.StatusLine;

/**
 * author: chenliuchun
 * Date:    2018/8/6
 * Description: http 非 200 错误码的异常类
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
public class RequestException extends HttpException {

    private StatusLine statusLine;

    public RequestException() {
        super();
    }

    public RequestException(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }
}
