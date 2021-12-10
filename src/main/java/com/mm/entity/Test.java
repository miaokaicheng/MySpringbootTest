package com.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@TableName("t_test")
public class Test implements Serializable {

    @TableId(type = IdType.AUTO, value = "id")
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "type")
    private int type;

    @TableField(value = "num")
    private int num;

    @TableField(value = "content")
    private String content;

    @TableField(value = "mark")
    private String mark;
}
