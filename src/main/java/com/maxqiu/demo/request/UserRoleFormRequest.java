package com.maxqiu.demo.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户-权限 表单提交
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRoleFormRequest {
    private Integer userId;

    private List<Integer> roleIdList;
}
