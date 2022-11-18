package com.maxqiu.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
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

    @GetMapping("toAssign/{userId}")
    public Result<?> toAssign(@PathVariable String userId) {
        // Map<String,Object> roleMap = userRoleService.getRolesByUserId(userId);
        return Result.success();
    }

    @PostMapping("doAssign")
    public Result<?> doAssign() {
        // sysRoleService.doAssign(assginRoleVo);
        return Result.success();
    }
}
