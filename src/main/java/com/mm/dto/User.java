package com.mm.dto;

import lombok.Data;

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
    private Date birthday;
}
