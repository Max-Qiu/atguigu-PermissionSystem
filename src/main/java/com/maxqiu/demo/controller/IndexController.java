package com.maxqiu.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.enums.ResultEnum;
import com.maxqiu.demo.pojo.vo.InfoVO;
import com.maxqiu.demo.service.MenuService;
import com.maxqiu.demo.service.UserService;

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
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户信息
     */
    @GetMapping("info")
    public Result<InfoVO> info(@RequestHeader("token") String token) {
        // 从token字符串获取用户id
        String username = redisTemplate.opsForValue().get(token);
        if (username == null) {
            return Result.other(ResultEnum.NOT_LOG_IN);
        }
        User user = userService.getByUsername(username);
        // 获取菜单
        List<Menu> list = menuService.listByUserId(user.getId());
        return Result.success(new InfoVO(user, list));
    }
}
