package com.mm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author MKC
 * @Date 2022/1/13
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 7160557680614732403L;
    @ApiModelProperty(value = "主键ID" , example = "1")
    private Integer id;
    @ApiModelProperty(value = "url地址" , example = "url地址")
    private String url;
    @ApiModelProperty(value = "url描述" , example = "url描述")
    private String name;
}
