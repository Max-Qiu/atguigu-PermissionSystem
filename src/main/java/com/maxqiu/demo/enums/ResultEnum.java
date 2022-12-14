package com.maxqiu.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回值枚举
 *
 * @author Max_Qiu
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    // 操作成功
    SUCCESS(0, "成功"),
    // 操作失败
    FAIL(1, "失败"),
    // 参数异常
    PARAMETER_ERROR(400, "参数校验异常"),
    // 未登录
    NOT_LOG_IN(403, "未登录"),
    // 服务器异常
    SERVER_ERROR(500, "未知异常"),

    PERMISSION(209, "没有权限"),

    ACCOUNT_ERROR(216, "账号不正确"),

    ;

    private final Integer code;
    private final String msg;
}
