package com.money.money.common.exception.config;

public interface BusinessExceptionAssert extends IResponseEnum {

    /**
     * 如果条件为真，则抛出异常
     */
    default void exception(Boolean decide) {
        if (decide) {
            throw new BaseException(this.getMessage(),this.getCode());
        }
    }

    /**
     * 如果条件为真，则抛出异常
     */
    default void exception(Boolean decide,String message) {
        if (decide) {
            throw new BaseException(message,this.getCode());
        }
    }

    default void exception() {
        throw new BaseException(this.getMessage(),this.getCode());
    }
}
