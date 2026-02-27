package com.myaiagent.exception;

/**
 * 自定义业务异常类
 *
 * @author tanghua
 * @date: 2026/02/27/ 19:02
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
