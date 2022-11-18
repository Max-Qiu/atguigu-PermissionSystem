package com.maxqiu.demo.pojo.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.maxqiu.demo.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserVO extends Model<UserVO> {
    /**
     * 会员id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 岗位id
     */
    private Long postId;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（1：正常 0：停用）
     */
    private Boolean status;

    /**
     * 是否删除 0:否 1:是
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public UserVO(User entity) {
        this.setId(entity.getId());
        this.setUsername(entity.getUsername());
        this.setName(entity.getName());
        this.setPhone(entity.getPhone());
        this.setHeadUrl(entity.getHeadUrl());
        this.setDeptId(entity.getDeptId());
        this.setPostId(entity.getPostId());
        this.setDescription(entity.getDescription());
        this.setStatus(entity.getStatus());
        this.setDeleted(entity.getDeleted());
        this.setCreateTime(entity.getCreateTime());
        this.setUpdateTime(entity.getUpdateTime());
    }
}
