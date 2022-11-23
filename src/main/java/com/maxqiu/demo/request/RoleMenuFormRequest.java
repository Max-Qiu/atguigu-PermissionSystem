package com.maxqiu.demo.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色菜单 表单提交
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleMenuFormRequest {
    private Integer roleId;

    private List<Integer> menuIdList;
}
