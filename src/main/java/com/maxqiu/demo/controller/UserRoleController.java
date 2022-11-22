package com.maxqiu.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.Role;
import com.maxqiu.demo.entity.UserRole;
import com.maxqiu.demo.pojo.vo.UserRoleVO;
import com.maxqiu.demo.request.UserRoleFormRequest;
import com.maxqiu.demo.service.RoleService;
import com.maxqiu.demo.service.UserRoleService;

/**
 * 用户与角色 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/user-role")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @GetMapping("get/{userId}")
    public Result<?> get(@PathVariable Integer userId) {
        // 获取所有角色
        List<Role> roles = roleService.list();
        List<UserRole> userRolesList = userRoleService.listByUserId(userId);
        return Result.success(new UserRoleVO(roles, userRolesList));
    }

    @PostMapping("post")
    public Result<?> post(@RequestBody UserRoleFormRequest formRequest) {
        return Result.success(userRoleService.post(formRequest));
    }
}
