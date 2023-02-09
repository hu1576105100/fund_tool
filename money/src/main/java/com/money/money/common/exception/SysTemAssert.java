package com.money.money.common.exception;

import com.money.money.common.exception.config.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysTemAssert implements BusinessExceptionAssert {

    //账号管理异常
    sameName(1001, "存在相同的名称"),
    sameNo(1002, "存在相同的业务编号"),
    samePhone(1003, "当前系统已存在该手机号"),
    noSmsCode(1004, "手机验证码失效"),
    errorSmsCode(1004, "验证码错误"),
    cannotLogin(1005, "非管理员无法登入"),
    errorPassword(1006, "账号或者密码错误"),
    newPasswordError(1007, "新密码和确认密码不一致"),
    userEmpty(1008, "找不到用户"),
    userDis(1009, "账号被禁用"),
    unPhone(1010, "手机号或者邮箱异常"),
    unEmail(1011, "手机号或者邮箱异常"),
    roleCustomerEmpty(1012, "用户角色不存在"),
    disable(1013, "当前账号已被封禁，请联系管理员解封"),
    ;
    /**
     * 返回码
     */
    private final Integer code;
    /**
     * 返回消息
     */
    private final String message;
}
