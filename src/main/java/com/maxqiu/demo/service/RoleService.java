package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.Role;
import com.maxqiu.demo.mapper.RoleMapper;
import com.maxqiu.demo.request.RolePageRequest;

import cn.hutool.core.util.StrUtil;

/**
 * 角色 服务类
 *
 * @author Max_Qiu
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    public IPage<Role> page(RolePageRequest pageRequest) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(pageRequest.getRoleName()), Role::getRoleName, pageRequest.getRoleName());
        return page(pageRequest.toPage(), wrapper);
    }
}
