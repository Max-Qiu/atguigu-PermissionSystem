package com.maxqiu.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 操作日志记录
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName("sys_log_operation")
public class LogOperation extends Model<LogOperation> {
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模块标题
     */
    @TableField("title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 方法名称
     */
    @TableField("method")
    private String method;

    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField("operator_type")
    private String operatorType;

    /**
     * 操作人员
     */
    @TableField("oper_name")
    private String operName;

    /**
     * 请求URL
     */
    @TableField("oper_url")
    private String operUrl;

    /**
     * 主机地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 请求参数
     */
    @TableField("oper_param")
    private String operParam;

    /**
     * 返回参数
     */
    @TableField("json_result")
    private String jsonResult;

    /**
     * 操作状态 0正常 1异常
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;

    @TableField("create_time")
    private LocalDateTime createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
