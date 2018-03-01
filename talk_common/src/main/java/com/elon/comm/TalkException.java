package com.elon.comm;

/**
 * 2017/6/22 14:24.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class TalkException extends RuntimeException {

    public TalkException(String errCode, String msg, String command) {
        super(errCode + "#" + msg + "|" + command);
    }

}
