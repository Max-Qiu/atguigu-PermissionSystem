package com.maxqiu.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
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

    @PostMapping("/doAssign")
    public Result<?> doAssign() {
        // menuService.doAssign(assginMenuVo);
        return Result.success();
    }

    // 根据角色分配菜单
    @GetMapping("/toAssign/{roleId}")
    public Result<?> toAssign(@PathVariable String roleId) {
        // List<SysMenu> list = menuService.findMenuByRoleId(roleId);
        return Result.success();
    }
}
