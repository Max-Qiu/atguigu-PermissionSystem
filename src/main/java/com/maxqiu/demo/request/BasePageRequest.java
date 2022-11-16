package com.maxqiu.demo.request;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页查询基类
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BasePageRequest {
    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    public <T> IPage<T> toPage() {
        return new Page<>(this.pageNum, this.pageSize);
    }
}
