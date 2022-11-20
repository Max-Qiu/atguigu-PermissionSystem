package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.RoleMenu;
import com.maxqiu.demo.mapper.RoleMenuMapper;

/**
 * 角色与菜单 服务类
 *
 * @author Max_Qiu
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {

}
