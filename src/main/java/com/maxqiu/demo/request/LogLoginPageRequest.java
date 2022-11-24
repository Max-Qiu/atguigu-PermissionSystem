package com.maxqiu.demo.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LogLoginPageRequest extends BasePageRequest {
    private String username;

    private LocalDateTime createTimeBegin;
    private LocalDateTime createTimeEnd;
}
