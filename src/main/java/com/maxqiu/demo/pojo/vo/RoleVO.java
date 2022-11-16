package com.maxqiu.demo.pojo.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maxqiu.demo.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleVO {
    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除标记
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public RoleVO(Role entity) {
        this.setId(entity.getId());
        this.setRoleName(entity.getRoleName());
        this.setRoleCode(entity.getRoleCode());
        this.setDescription(entity.getDescription());
        this.setDeleted(entity.getDeleted());
        this.setCreateTime(entity.getCreateTime());
        this.setUpdateTime(entity.getUpdateTime());
    }
}
