package com.maxqiu.demo.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户分页查询
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserPageRequest extends BasePageRequest {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 创建时间（开始）
     */
    private LocalDateTime createTimeBegin;
    /**
     * 创建时间（结束）
     */
    private LocalDateTime createTimeEnd;

    private Long roleId;
    private Long postId;
    private Long deptId;
}
