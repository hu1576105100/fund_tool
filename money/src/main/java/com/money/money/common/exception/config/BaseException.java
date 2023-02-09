package com.money.money.common.exception.config;


public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2919388158789884410L;

    private Integer code;

    private final String message;

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static void error(Integer code,String message){
        throw new BaseException(message,code);
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

}
