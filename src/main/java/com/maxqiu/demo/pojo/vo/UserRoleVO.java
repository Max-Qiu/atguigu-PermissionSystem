package com.maxqiu.demo.pojo.vo;

import java.util.ArrayList;
import java.util.List;

import com.maxqiu.demo.entity.Role;
import com.maxqiu.demo.entity.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户角色
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRoleVO {
    private List<RoleInfoVO> allRoles;
    private List<Integer> userRoleIds;

    public UserRoleVO(List<Role> roles, List<UserRole> userRolesList) {
        this.allRoles = roles.stream().map(RoleInfoVO::new).toList();
        // 从userRoles集合获取所有角色id
        List<Integer> userRoleIds = new ArrayList<>();
        for (UserRole userRole : userRolesList) {
            Integer roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        this.userRoleIds = userRoleIds;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    private static class RoleInfoVO {
        /**
         * 角色ID
         */
        private Long id;

        /**
         * 角色名称
         */
        private String roleName;

        public RoleInfoVO(Role role) {
            this.setId(role.getId());
            this.setRoleName(role.getRoleName());
        }
    }
}
