package com.maxqiu.demo.filter;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson2.JSON;
import com.maxqiu.demo.common.ResponseUtil;
import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.enums.ResultEnum;

/**
 * 认证解析过滤器
 *
 * @author Max_Qiu
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 如果是登录接口，直接放行
        if ("/login".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, Result.other(ResultEnum.PERMISSION));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            String username = redisTemplate.opsForValue().get(token);
            if (StringUtils.hasText(username)) {
                String authoritiesString = redisTemplate.opsForValue().get(username);
                List<GrantedAuthority> sets = JSON.parseArray(authoritiesString, GrantedAuthority.class);
                return new UsernamePasswordAuthenticationToken(username, null, sets);
            }
        }
        return null;
    }
}
