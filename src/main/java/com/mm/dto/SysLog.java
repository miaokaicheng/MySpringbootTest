package com.mm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 日志记录类
 * @Author MKC
 * @Date 2021/12/27
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = -6309732882044872298L;

    private Integer id;
    private String userName;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
