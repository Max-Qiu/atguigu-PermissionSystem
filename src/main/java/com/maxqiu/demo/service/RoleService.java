package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.Role;
import com.maxqiu.demo.mapper.RoleMapper;
import com.maxqiu.demo.request.RoleFormRequest;
import com.maxqiu.demo.request.RolePageRequest;

import cn.hutool.core.util.StrUtil;

/**
 * 角色 服务类
 *
 * @author Max_Qiu
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    /**
     * 分页查询
     */
    public IPage<Role> page(RolePageRequest pageRequest) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(pageRequest.getRoleName()), Role::getRoleName, pageRequest.getRoleName());
        return page(pageRequest.toPage(), wrapper);
    }

    /**
     * 新增
     */
    public boolean create(RoleFormRequest formRequest) {
        Role role = new Role();
        role.setRoleName(formRequest.getRoleName());
        role.setRoleCode(formRequest.getRoleCode());
        return save(role);
    }

    /**
     * 修改
     */
    public boolean updateById(RoleFormRequest formRequest) {
        Role role = new Role();
        role.setId(formRequest.getId());
        role.setRoleName(formRequest.getRoleName());
        role.setRoleCode(formRequest.getRoleCode());
        return save(role);
    }
}
