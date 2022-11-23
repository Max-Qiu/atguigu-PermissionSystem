package com.maxqiu.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.entity.RoleMenu;
import com.maxqiu.demo.pojo.vo.MenuTreeVO;
import com.maxqiu.demo.request.RoleMenuFormRequest;
import com.maxqiu.demo.service.MenuService;
import com.maxqiu.demo.service.RoleMenuService;

/**
 * 角色与菜单 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/role-menu")
public class RoleMenuController {
    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private MenuService menuService;

    // 根据角色分配菜单
    @GetMapping("/get/{roleId}")
    public Result<List<MenuTreeVO>> get(@PathVariable Integer roleId) {
        // 获取拥有的菜单
        List<RoleMenu> roleMenus = roleMenuService.listByRoleId(roleId);
        // 转为set
        Set<Integer> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());

        // 获取所有菜单可用的菜单
        List<Menu> menuList = menuService.listEnabled();

        return Result.success(listToTreeVO(0, menuList, menuIds));
    }

    /**
     * 递归查找所有菜单的子菜单
     */
    private List<MenuTreeVO> listToTreeVO(Integer parentId, List<Menu> all, Set<Integer> menuIds) {
        return all.stream()
            // 根据父ID过滤
            .filter(category -> category.getParentId().equals(parentId))
            // 转换为VO
            .map(e -> new MenuTreeVO(e, listToTreeVO(e.getId(), all, menuIds), menuIds))
            // 收集
            .collect(Collectors.toList());
    }

    @PostMapping("/post")
    public Result<?> post(@RequestBody RoleMenuFormRequest formRequest) {
        return Result.byFlag(roleMenuService.post(formRequest));
    }
}
