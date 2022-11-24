package com.maxqiu.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.LogOperation;
import com.maxqiu.demo.request.LogOperationPageRequest;
import com.maxqiu.demo.service.LogOperationService;

/**
 * 操作日志记录 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/log-operation")
public class LogOperationController {
    @Resource
    private LogOperationService logOperationService;

    @GetMapping("page")
    public Result<?> index(LogOperationPageRequest pageRequest) {
        IPage<LogOperation> pageModel = logOperationService.page(pageRequest);
        return Result.success(pageModel);
    }
}
