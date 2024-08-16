package com.ioomex.common.app.exception;


import com.ioomex.common.app.common.ErrorCode;

import static com.ioomex.common.app.common.ErrorCode.PARAMS_ERROR;

/**
 * 自定义异常类
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;


    public BusinessException(String message) {
        super(message);
        this.code = PARAMS_ERROR.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
