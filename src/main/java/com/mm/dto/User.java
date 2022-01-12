package com.mm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "用户名" , example = "姓名")
    private String userName;
    @ApiModelProperty(value = "用户性别" , allowableValues = "0,1")
    private int sex;
    @ApiModelProperty(value = "用户出生日期" , example = "1998-02-05 13:30:41")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    /**
     * @Email 好像无法正确解析邮箱格式，测试了1@qqcom，163@等都可以正常通过
     */
    @Pattern(regexp = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$", message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱", example = "111@qq.com")
    private String email;
    @ApiModelProperty(value = "手机号", required = true, example = "18888888888")
    @Pattern(regexp = "^[1][3,5,7,8][0-9]\\d{8}$", message = "手机号码格式错误")
    private String phone;
    @ApiModelProperty(value = "密码" , example = "密码")
    private String password;
    @ApiModelProperty(value = "状态" , example = "1")
    private String status;
}
