package com.maxqiu.demo.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 操作日志分页查询
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LogOperationPageRequest extends BasePageRequest {
    private String title;
    private String operationName;

    private LocalDateTime createTimeBegin;
    private LocalDateTime createTimeEnd;
}
