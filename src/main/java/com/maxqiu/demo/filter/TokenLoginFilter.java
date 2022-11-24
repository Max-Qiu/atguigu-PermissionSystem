package com.maxqiu.demo.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxqiu.demo.common.IpUtil;
import com.maxqiu.demo.common.ResponseUtil;
import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.enums.ResultEnum;
import com.maxqiu.demo.request.LoginFormRequest;
import com.maxqiu.demo.service.LogLoginService;

import cn.hutool.core.lang.UUID;

/**
 * Token
 *
 * @author Max_Qiu
 */
@Component
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private LogLoginService logLoginService;

    // 构造
    public TokenLoginFilter() {
        // 指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    // 获取用户名和密码，认证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginFormRequest loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginFormRequest.class);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 认证成功调用
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        // 获取认证对象
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();

        // 保存权限数据
        redisTemplate.opsForValue().set(user.getUsername(), JSON.toJSONString(user.getAuthorities()));

        // 生成token
        String token = UUID.fastUUID().toString();
        redisTemplate.opsForValue().set(token, user.getUsername(), 10, TimeUnit.MINUTES);

        // 记录登录日志
        logLoginService.recordLogLogin(user.getUsername(), 1, IpUtil.getIpAddress(request), "登录成功");
        // 返回
        ResponseUtil.out(response, Result.success(token));
    }

    // 认证失败
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        if (e.getCause() instanceof RuntimeException) {
            ResponseUtil.out(response, Result.other(ResultEnum.PERMISSION));
        } else {
            ResponseUtil.out(response, Result.other(ResultEnum.ACCOUNT_ERROR));
        }
    }
}
