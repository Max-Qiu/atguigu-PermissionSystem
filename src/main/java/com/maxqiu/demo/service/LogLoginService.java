package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.LogLogin;
import com.maxqiu.demo.mapper.LogLoginMapper;
import com.maxqiu.demo.request.LogLoginPageRequest;

/**
 * 系统访问记录 服务类
 *
 * @author Max_Qiu
 */
@Service
public class LogLoginService extends ServiceImpl<LogLoginMapper, LogLogin> {
    /**
     * 分页
     */
    public IPage<LogLogin> page(LogLoginPageRequest pageRequest) {
        LambdaQueryWrapper<LogLogin> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.hasText(pageRequest.getUsername()), LogLogin::getUsername, pageRequest.getUsername());
        wrapper.ge(pageRequest.getCreateTimeBegin() != null, LogLogin::getCreateTime, pageRequest.getCreateTimeBegin());
        wrapper.ge(pageRequest.getCreateTimeEnd() != null, LogLogin::getCreateTime, pageRequest.getCreateTimeEnd());
        return page(pageRequest.toPage(), wrapper);
    }

    public void recordLogLogin(String username, Integer status, String ipaddr, String message) {
        LogLogin logLogin = new LogLogin();
        logLogin.setUsername(username);
        logLogin.setStatus(status);
        logLogin.setIpaddr(ipaddr);
        logLogin.setMsg(message);
        save(logLogin);
    }
}
