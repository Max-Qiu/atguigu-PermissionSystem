package com.maxqiu.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.LogLogin;
import com.maxqiu.demo.request.LogLoginPageRequest;
import com.maxqiu.demo.service.LogLoginService;

/**
 * 系统访问记录 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/log-login")
public class LogLoginController {
    @Resource
    private LogLoginService logLoginService;

    // 条件分页查询登录日志
    @GetMapping("page")
    public Result<?> page(LogLoginPageRequest pageRequest) {
        IPage<LogLogin> pageModel = logLoginService.page(pageRequest);
        return Result.success(pageModel);
    }
}
