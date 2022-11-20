package com.maxqiu.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.pojo.vo.MenuTreeVO;
import com.maxqiu.demo.service.MenuService;

/**
 * 菜单 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    /**
     * 菜单树
     */
    @GetMapping("tree")
    public Result<List<MenuTreeVO>> tree() {
        return Result.success(listToTreeVO(0, menuService.list()));
    }

    /**
     * 递归查找所有菜单的子菜单
     */
    private List<MenuTreeVO> listToTreeVO(Integer parentId, List<Menu> all) {
        List<MenuTreeVO> collect = all.stream()
            // 根据父ID过滤
            .filter(category -> category.getParentId().equals(parentId))
            // 转换为VO
            .map(e -> new MenuTreeVO(e, listToTreeVO(e.getId(), all)))
            // 收集
            .collect(Collectors.toList());
        if (collect.size() == 0) {
            return null;
        }
        return collect;
    }

    /**
     * 根据id查询
     */
    @GetMapping("detail/{id}")
    public Result<Menu> detail(@PathVariable String id) {
        return Result.success(menuService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("create")
    public Result<?> create(@RequestBody Menu menu) {
        return Result.byFlag(menuService.save(menu));
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<?> update(@RequestBody Menu menu) {
        return Result.byFlag(menuService.updateById(menu));
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<?> delete(@PathVariable String id) {
        // menuService.removeMenuById(id);
        return Result.success();
    }
}
