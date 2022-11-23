package com.maxqiu.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.RoleMenu;
import com.maxqiu.demo.mapper.RoleMenuMapper;
import com.maxqiu.demo.request.RoleMenuFormRequest;

/**
 * 角色与菜单 服务类
 *
 * @author Max_Qiu
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {
    /**
     * 根据角色查找拥有的权限
     */
    public List<RoleMenu> listByRoleId(Integer roleId) {
        // 根据角色id查询 角色分配过的菜单列表
        LambdaQueryWrapper<RoleMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        return list(wrapper);
    }

    /**
     * 授权
     */
    public boolean post(RoleMenuFormRequest formRequest) {
        // 根据角色id删除菜单权限
        LambdaQueryWrapper<RoleMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RoleMenu::getRoleId, formRequest.getRoleId());
        remove(wrapper);
        // 遍历菜单id列表，一个一个进行添加
        List<RoleMenu> list = new ArrayList<>();
        for (Integer menuId : formRequest.getMenuIdList()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(formRequest.getRoleId());
            list.add(roleMenu);
        }
        return saveBatch(list);
    }
}
