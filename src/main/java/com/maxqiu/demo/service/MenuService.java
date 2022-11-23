package com.maxqiu.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.mapper.MenuMapper;
import com.maxqiu.demo.request.MenuFormRequest;

/**
 * 菜单 服务类
 *
 * @author Max_Qiu
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
    /**
     * 根据用户ID查询拥有的菜单
     */
    public List<Menu> listByUserId(Integer userId) {
        // userid值是1代表超级管理员，查询所有权限数据
        if (userId == 1) {
            LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(Menu::getEnable, true);
            wrapper.orderByAsc(Menu::getSortValue);
            return list(wrapper);
        } else {
            // 如果userid不是1，其他类型用户，查询这个用户权限
            return baseMapper.listUserId(userId);
        }
    }

    /**
     * 新增
     */
    public boolean create(MenuFormRequest formRequest) {
        Menu menu = new Menu();
        menu.setParentId(formRequest.getParentId());
        menu.setName(formRequest.getName());
        menu.setType(formRequest.getType());
        menu.setPath(formRequest.getPath());
        menu.setComponent(formRequest.getComponent());
        menu.setPerms(formRequest.getPerms());
        menu.setIcon(formRequest.getIcon());
        menu.setSortValue(formRequest.getSortValue());
        menu.setEnable(formRequest.getEnable());
        return save(menu);
    }

    /**
     * 修改
     */
    public boolean updateById(MenuFormRequest formRequest) {
        Menu menu = new Menu();
        menu.setId(formRequest.getId());
        menu.setParentId(formRequest.getParentId());
        menu.setName(formRequest.getName());
        menu.setType(formRequest.getType());
        menu.setPath(formRequest.getPath());
        menu.setComponent(formRequest.getComponent());
        menu.setPerms(formRequest.getPerms());
        menu.setIcon(formRequest.getIcon());
        menu.setSortValue(formRequest.getSortValue());
        menu.setEnable(formRequest.getEnable());
        return updateById(menu);
    }

    /**
     * 列出启动的菜单
     */
    public List<Menu> listEnabled() {
        LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Menu::getEnable, true);
        return list(wrapper);
    }
}
