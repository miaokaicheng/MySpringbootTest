package com.mm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description 用户类
 * @Author MKC
 * @Date 2021/12/28
 */
@Data
public class User {
    @ApiModelProperty(value = "用户ID" , example = "1")
    private Long id;
    @ApiModelProperty(value = "用户名" , example = "姓名")
    private String userName;
    @ApiModelProperty(value = "用户性别" , allowableValues = "0,1")
    private int sex;
    @ApiModelProperty(value = "用户出生日期" , example = "1998-02-05 13:30:41")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
