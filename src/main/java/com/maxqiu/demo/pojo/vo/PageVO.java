package com.maxqiu.demo.pojo.vo;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分页数据
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
public class PageVO<T> {
    /**
     * 对象集合
     */
    private List<T> list;

    /**
     * 页码
     */
    private Long pageNumber;

    /**
     * 页面大小
     */
    private Long pageSize;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总行数
     */
    private Long total;

    public PageVO(IPage<?> page, List<T> list) {
        this.pageNumber = page.getCurrent();
        this.pageSize = page.getSize();
        this.totalPage = page.getPages();
        this.total = page.getTotal();
        this.list = list;
    }
}
