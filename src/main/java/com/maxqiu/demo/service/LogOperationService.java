package com.maxqiu.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maxqiu.demo.entity.LogOperation;
import com.maxqiu.demo.mapper.LogOperationMapper;
import com.maxqiu.demo.request.LogOperationPageRequest;

/**
 * 操作日志记录 服务类
 *
 * @author Max_Qiu
 */
@Service
public class LogOperationService extends ServiceImpl<LogOperationMapper, LogOperation> {
    /**
     * 分页查询
     */
    public IPage<LogOperation> page(LogOperationPageRequest pageRequest) {
        LambdaQueryWrapper<LogOperation> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.hasText(pageRequest.getTitle()), LogOperation::getTitle, pageRequest.getTitle());
        wrapper.like(StringUtils.hasText(pageRequest.getOperationName()), LogOperation::getOperName, pageRequest.getOperationName());
        wrapper.ge(pageRequest.getCreateTimeBegin() != null, LogOperation::getCreateTime, pageRequest.getCreateTimeBegin());
        wrapper.le(pageRequest.getCreateTimeEnd() != null, LogOperation::getCreateTime, pageRequest.getCreateTimeEnd());
        return page(pageRequest.toPage(), wrapper);
    }
}
