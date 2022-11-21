package com.maxqiu.demo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.pojo.vo.InfoVO;
import com.maxqiu.demo.request.LoginFormRequest;
import com.maxqiu.demo.service.MenuService;
import com.maxqiu.demo.service.UserService;

import cn.hutool.core.lang.UUID;

/**
 * @author Max_Qiu
 */
@RestController
@RequestMapping("")
public class IndexController {
    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    /**
     * 登录
     */
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginFormRequest formRequest) {
        // 根据username查询数据
        User user = userService.getByUsername(formRequest.getUsername());
        // 如果查询为空
        if (user == null) {
            return Result.fail("用户名或密码错误");
        }
        // 判断密码是否一致
        String password = formRequest.getPassword();
        if (!user.getPassword().equals(password)) {
            return Result.fail("用户名或密码错误");
        }
        // 判断用户是否可用
        if (!user.getEnable()) {
            return Result.fail("账户不可用");
        }
        // 生成token并保存
        String token = UUID.fastUUID().toString();
        redisTemplate.opsForValue().set(token, user.getId(), 10, TimeUnit.MINUTES);
        return Result.success(token);
    }

    /**
     * 用户信息
     */
    @GetMapping("info")
    public Result<InfoVO> info(@RequestHeader("token") String token) {
        // 从token字符串获取用户id
        Integer userId = redisTemplate.opsForValue().get(token);
        User user = userService.getById(userId);
        // 获取菜单
        List<Menu> list = menuService.listByUserId(userId);
        return Result.success(new InfoVO(user, list));
    }
}
