package com.maxqiu.demo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色分页查询
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RolePageRequest extends BasePageRequest {
    /**
     * 角色名称
     */
    private String roleName;
}
