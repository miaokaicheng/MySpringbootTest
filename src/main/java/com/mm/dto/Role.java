package com.mm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 角色类
 * @Author MKC
 * @Date 2022/1/13
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -227437593919820521L;
    @ApiModelProperty(value = "主键ID" , example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名称" , example = "admin")
    private String name;
    @ApiModelProperty(value = "角色描述" , example = "超级管理员")
    private String memo;
}
