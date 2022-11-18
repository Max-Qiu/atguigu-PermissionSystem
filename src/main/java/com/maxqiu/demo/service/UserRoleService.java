package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.UserRole;
import com.maxqiu.demo.mapper.UserRoleMapper;

/**
 * 用户与角色 服务类
 *
 * @author Max_Qiu
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

}
