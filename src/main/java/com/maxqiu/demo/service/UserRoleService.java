package com.maxqiu.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.UserRole;
import com.maxqiu.demo.mapper.UserRoleMapper;
import com.maxqiu.demo.request.UserRoleFormRequest;

/**
 * 用户与角色 服务类
 *
 * @author Max_Qiu
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {
    /**
     * 根据用户ID查询关联关系
     */
    public List<UserRole> listByUserId(Integer userId) {
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserRole::getUserId, userId);
        return list(wrapper);
    }

    /**
     * 提交权限
     */
    public boolean post(UserRoleFormRequest formRequest) {
        // 根据用户id删除之前分配角色
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserRole::getUserId, formRequest.getUserId());
        remove(wrapper);
        // 获取所有角色id，添加角色用户关系表
        List<UserRole> list = new ArrayList<>();
        for (Integer roleId : formRequest.getRoleIdList()) {
            UserRole userRole = new UserRole();
            userRole.setUserId(formRequest.getUserId());
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        return saveBatch(list);
    }
}
