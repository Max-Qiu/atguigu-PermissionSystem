package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.mapper.UserMapper;
import com.maxqiu.demo.request.UserPageRequest;

import cn.hutool.core.util.StrUtil;

/**
 * 用户 服务类
 *
 * @author Max_Qiu
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public IPage<User> page(UserPageRequest pageRequest) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.and(StrUtil.isNotBlank(pageRequest.getKeyword()), w -> w.like(User::getUsername, pageRequest.getKeyword()).or()
            .like(User::getName, pageRequest.getKeyword()).or().like(User::getPhone, pageRequest.getKeyword()));
        wrapper.ge(pageRequest.getCreateTimeBegin() != null, User::getCreateTime, pageRequest.getCreateTimeBegin());
        wrapper.le(pageRequest.getCreateTimeEnd() != null, User::getCreateTime, pageRequest.getCreateTimeEnd());
        wrapper.orderByDesc(User::getId);
        return page(pageRequest.toPage(), wrapper);
    }

    public boolean updateStatus(Integer id, Boolean status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        return user.updateById();
    }
}
