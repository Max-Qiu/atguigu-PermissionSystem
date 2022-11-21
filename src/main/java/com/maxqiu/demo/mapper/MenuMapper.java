package com.maxqiu.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maxqiu.demo.entity.Menu;

/**
 * 菜单 Mapper 接口
 *
 * @author Max_Qiu
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户ID查询菜单
     */
    List<Menu> listUserId(@Param("userId") Integer userId);
}
