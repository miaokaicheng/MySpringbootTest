package com.mm.dto;

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
    private int id;
    private String userName;
    private int sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
