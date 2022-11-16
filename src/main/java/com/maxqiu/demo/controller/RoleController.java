package com.maxqiu.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.maxqiu.demo.common.Result;
import com.maxqiu.demo.entity.Role;
import com.maxqiu.demo.pojo.vo.PageVO;
import com.maxqiu.demo.pojo.vo.RoleVO;
import com.maxqiu.demo.request.RolePageRequest;
import com.maxqiu.demo.service.RoleService;

/**
 * 角色 前端控制器
 *
 * @author Max_Qiu
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 查询所有记录
     */
    @GetMapping("list")
    public Result<List<RoleVO>> findAllRole() {
        List<Role> list = roleService.list();
        List<RoleVO> collect = list.stream().map(RoleVO::new).collect(Collectors.toList());
        return Result.success(collect);
    }

    /**
     * 条件分页查询
     */
    @GetMapping("page")
    public Result<PageVO<RoleVO>> findPageQueryRole(@Validated RolePageRequest pageRequest) {
        IPage<Role> page = roleService.page(pageRequest);
        List<RoleVO> collect = page.getRecords().stream().map(RoleVO::new).collect(Collectors.toList());
        return Result.success(new PageVO<>(page, collect));
    }

    /**
     * 根据id查询
     */
    @GetMapping("get/{id}")
    public Result<RoleVO> findRoleById(@PathVariable Long id) {
        return Result.success(new RoleVO(roleService.getById(id)));
    }

    /**
     * 添加
     */
    @PostMapping("create")
    public Result<?> create(@RequestBody Role role) {
        return Result.byFlag(roleService.save(role));
    }

    /**
     * 修改
     */
    @PostMapping("update")
    public Result<?> update(@RequestBody Role role) {
        return Result.byFlag(roleService.updateById(role));
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return Result.byFlag(roleService.removeById(id));
    }

    /**
     * 批量删除
     */
    @DeleteMapping("delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        return Result.byFlag(roleService.removeByIds(ids));
    }
}
