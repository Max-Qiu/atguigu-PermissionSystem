package com.maxqiu.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.filter.TokenAuthenticationFilter;
import com.maxqiu.demo.filter.TokenLoginFilter;
import com.maxqiu.demo.service.MenuService;
import com.maxqiu.demo.service.UserService;

/**
 * 安全配置
 *
 * @author Max_Qiu
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解功能，默认禁用注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserService sysUserService;

    @Resource
    private MenuService sysMenuService;

    /**
     * 指定密码的加密方式
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用户详细信息
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = sysUserService.getByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            if (!user.getEnable()) {
                throw new RuntimeException("用户被禁用了");
            }
            // 根据userid查询操作权限数据
            List<Menu> userPermsList = sysMenuService.listByUserId(user.getId());
            Set<String> collect = userPermsList.stream().filter(e -> e.getType() == 2).map(Menu::getPerms).collect(Collectors.toSet());
            // 转换security要求格式数据
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String perm : collect) {
                authorities.add(new SimpleGrantedAuthority(perm.trim()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        };
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Resource
    private TokenLoginFilter tokenLoginFilter;

    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护

        // 关闭csrf
        http.csrf().disable();

        // 开启跨域以便前端调用接口
        http.cors();

        // 这里意思是其它所有接口需要认证才能访问
        http.authorizeRequests().anyRequest().authenticated();

        // TokenAuthenticationFilter放到UsernamePasswordAuthenticationFilter的前面，这样做就是为了除了登录的时候去查询数据库外，其他时候都用token进行认证。
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).addFilter(tokenLoginFilter);

        // 禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定UserDetailService和加密器
        auth.userDetailsService(this.userDetailsService()).passwordEncoder(bCryptPasswordEncoder);
    }
}
