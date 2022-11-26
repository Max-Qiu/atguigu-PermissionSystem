package com.maxqiu.demo.pojo.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.enums.MenuTypeEnum;

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
     * 类型
     */
    private MenuTypeEnum type;

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
     * 是否启用
     */
    private Boolean enable;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 子集合
     */
    private List<MenuTreeVO> children;

    /**
     * 是否选中
     */
    private Boolean select;

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
        this.setEnable(entity.getEnable());
        this.setCreateTime(entity.getCreateTime());
        this.setChildren(children);
    }

    public MenuTreeVO(Menu entity, List<MenuTreeVO> children, Set<Integer> menuIds) {
        this(entity, children);
        this.select = menuIds.contains(entity.getId());
    }
}
