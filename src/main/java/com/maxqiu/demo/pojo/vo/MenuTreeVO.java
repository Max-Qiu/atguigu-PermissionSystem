package com.maxqiu.demo.pojo.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.maxqiu.demo.entity.Menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MenuTreeVO {
    /**
     * 菜单ID
     */
    private Integer id;

    /**
     * 所属上级
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(0:目录,1:菜单,2:按钮)
     */
    private Integer type;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 子集合
     */
    private List<MenuTreeVO> children;

    public MenuTreeVO(Menu entity, List<MenuTreeVO> children) {
        this.setId(entity.getId());
        this.setParentId(entity.getParentId());
        this.setName(entity.getName());
        this.setType(entity.getType());
        this.setPath(entity.getPath());
        this.setComponent(entity.getComponent());
        this.setPerms(entity.getPerms());
        this.setIcon(entity.getIcon());
        this.setSortValue(entity.getSortValue());
        this.setStatus(entity.getStatus());
        this.setDeleted(entity.getDeleted());
        this.setCreateTime(entity.getCreateTime());
        this.setUpdateTime(entity.getUpdateTime());
        this.setChildren(children);
    }
}
