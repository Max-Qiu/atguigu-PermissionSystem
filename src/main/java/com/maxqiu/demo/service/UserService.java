package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.mapper.UserMapper;
import com.maxqiu.demo.request.UserFormRequest;
import com.maxqiu.demo.request.UserPageRequest;

import cn.hutool.core.util.StrUtil;

/**
 * 用户 服务类
 *
 * @author Max_Qiu
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    /**
     * 分页查找
     */
    public IPage<User> page(UserPageRequest pageRequest) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.and(StrUtil.isNotBlank(pageRequest.getKeyword()), w -> w.like(User::getUsername, pageRequest.getKeyword()).or()
            .like(User::getName, pageRequest.getKeyword()).or().like(User::getPhone, pageRequest.getKeyword()));
        wrapper.ge(pageRequest.getCreateTimeBegin() != null, User::getCreateTime, pageRequest.getCreateTimeBegin());
        wrapper.le(pageRequest.getCreateTimeEnd() != null, User::getCreateTime, pageRequest.getCreateTimeEnd());
        wrapper.orderByDesc(User::getId);
        return page(pageRequest.toPage(), wrapper);
    }

    /**
     * 根据用户名查找当前用户
     */
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }

    /**
     * 新增
     */
    public Boolean create(UserFormRequest formRequest) {
        User user = new User();
        user.setUsername(formRequest.getUsername());
        // TODO 把输入密码进行加密 MD5
        // String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(formRequest.getPassword());
        user.setName(formRequest.getName());
        user.setPhone(formRequest.getPhone());
        return save(user);
    }

    /**
     * 更新
     */
    public boolean updateById(UserFormRequest formRequest) {
        User user = new User();
        user.setId(formRequest.getId());
        user.setUsername(formRequest.getUsername());
        // TODO 把输入密码进行加密 MD5
        user.setPassword(formRequest.getPassword());
        user.setName(formRequest.getName());
        user.setPhone(formRequest.getPhone());
        return updateById(user);
    }

    /**
     * 更新状态
     */
    public boolean updateStatus(Integer id, Boolean status) {
        User user = new User();
        user.setId(id);
        user.setEnable(status);
        return user.updateById();
    }
}
