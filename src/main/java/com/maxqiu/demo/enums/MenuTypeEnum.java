package com.maxqiu.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author Max_Qiu
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    CATALOG(0, "目录"),

    MENU(1, "菜单"),

    BUTTON(2, "按钮");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String msg;
}
