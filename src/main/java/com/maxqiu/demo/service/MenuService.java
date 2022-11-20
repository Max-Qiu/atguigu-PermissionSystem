package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.mapper.MenuMapper;

/**
 * 菜单 服务类
 *
 * @author Max_Qiu
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {}
