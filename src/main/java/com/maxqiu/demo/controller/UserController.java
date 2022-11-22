package com.maxqiu.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.pojo.vo.PageVO;
import com.maxqiu.demo.pojo.vo.UserVO;
import com.maxqiu.demo.request.UserFormRequest;
import com.maxqiu.demo.request.UserPageRequest;
import com.maxqiu.demo.service.UserService;

/**
 * 用户 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/page")
    public Result<PageVO<UserVO>> list(UserPageRequest pageRequest) {
        IPage<User> page = userService.page(pageRequest);
        List<UserVO> collect = page.getRecords().stream().map(UserVO::new).collect(Collectors.toList());
        return Result.success(new PageVO<>(page, collect));
    }

    @GetMapping("detail/{id}")
    public Result<UserVO> getUser(@PathVariable String id) {
        User user = userService.getById(id);
        return Result.success(new UserVO(user));
    }

    @PostMapping("create")
    public Result<?> create(@RequestBody UserFormRequest formRequest) {
        return Result.byFlag(userService.create(formRequest));
    }

    @PutMapping("update")
    public Result<?> update(@RequestBody UserFormRequest formRequest) {
        return Result.byFlag(userService.updateById(formRequest));
    }

    @PutMapping("status/{id}/{status}")
    public Result<?> status(@PathVariable Integer id, @PathVariable Boolean status) {
        return Result.byFlag(userService.updateStatus(id, status));
    }

    @DeleteMapping("delete/{id}")
    public Result<?> delete(@PathVariable String id) {
        return Result.byFlag(userService.removeById(id));
    }
}
